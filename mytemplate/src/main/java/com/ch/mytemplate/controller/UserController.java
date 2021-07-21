package com.ch.mytemplate.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.ch.mytemplate.contants.DefContants;
import com.ch.mytemplate.entity.User;
import com.ch.mytemplate.entity.dto.UserDTO;
import com.ch.mytemplate.exception.CustomException;
import com.ch.mytemplate.exception.CustomUnauthorizedException;
import com.ch.mytemplate.service.IUserService;
import com.ch.mytemplate.utils.AesCipherUtil;
import com.ch.mytemplate.utils.JwtUtils;
import com.ch.mytemplate.utils.RedisUtil;
import com.ch.mytemplate.utils.Result;
import io.netty.util.Constant;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author ch
 * @since 2021-06-29
 */
@Slf4j
@ApiModel
@Api(tags = "管理员Controller")
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private IUserService userService;
    @Resource
    private RedisUtil redisUtil;
    @Value("${refreshTokenExpireTime}")
    private String refreshTokenExpireTime;

    @Resource
    private RedisTemplate<String, Object> redisTemplate;


    @ResponseBody
    @GetMapping("/queryUserById")
    public Result<User> queryUserById(@RequestParam Integer id) {
        User user = userService.getById(id);
        redisUtil.set(String.valueOf(id), user);
        return Result.ok(user);
    }

    /**
     * @return null
     * @Author ch
     * @Description //管理员登录接口
     * @Date 2021/6/30 11:20
     * @Param null:
     **/
    @PostMapping("/login")
    @ApiOperation("管理员登录接口")
    public Result<Object> login(@RequestBody UserDTO userDto, HttpServletResponse httpServletResponse) {
        // 查询数据库中的帐号信息
        LambdaQueryWrapper<User> userCondition = Wrappers.lambdaQuery();
        userCondition.eq(User::getAccount, userDto.getAccount());
        User userInfo = userService.getOne(userCondition);
        if (userInfo == null) {
            throw new CustomUnauthorizedException("该帐号不存在(The account does not exist.)");
        }
        // 密码进行AES解密
        String key = AesCipherUtil.deCrypto(userInfo.getPassword());

        // 因为密码加密是以帐号+密码的形式进行加密的，所以解密后的对比是帐号+密码
        if (key.equals(userDto.getAccount() + userDto.getPassword())) {
            // 清除可能存在的Shiro权限信息缓存
            if (redisUtil.hasKey(DefContants.PREFIX_SHIRO_CACHE + userDto.getAccount())) {
                redisUtil.del(DefContants.PREFIX_SHIRO_CACHE + userDto.getAccount());
            }
            // 设置RefreshToken，时间戳为当前时间戳，直接设置即可(不用先删后设，会覆盖已有的RefreshToken)
            String currentTimeMillis = String.valueOf(System.currentTimeMillis());
            System.out.println(currentTimeMillis);
            redisUtil.set(DefContants.PREFIX_SHIRO_REFRESH_TOKEN + userDto.getAccount(), currentTimeMillis, Integer.parseInt(refreshTokenExpireTime));
            // 从Header中Authorization返回AccessToken，时间戳为当前时间戳
            String token = JwtUtils.sign(userDto.getAccount(), currentTimeMillis);
            httpServletResponse.setHeader("Authorization", token);
            httpServletResponse.setHeader("Access-Control-Expose-Headers", "Authorization");
            return Result.ok("登录成功(Login Success.)");
        } else {
            throw new CustomUnauthorizedException("帐号或密码错误(Account or Password Error.)");
        }
    }

    /**
     * 测试登录
     *
     * @param
     * @return
     * @author ch
     * @Date 2021/6/30 11:20
     */
    @GetMapping("/article")
    @ApiOperation("测试登录")
    public Result article() {
        Subject subject = SecurityUtils.getSubject();
        // 登录了返回true
        if (subject.isAuthenticated()) {
            return Result.success(HttpStatus.OK.value(), "您已经登录了(You are already logged in)");
        } else {
            return Result.success(HttpStatus.OK.value(), "你是游客(You are guest)");
        }
    }

    /**
     * 获取用户列表
     *
     * @return
     * @author ch
     * @Date 2021/6/30 11:20
     */
    @GetMapping("/queryAllUser")
    @ApiOperation("查询用户列表")
    @RequiresPermissions(logical = Logical.AND, value = {"user:view"})
    public Result<List<User>> queryAllUser() {
        List<User> userList = userService.queryAllUser();
        if (userList == null || userList.size() < 0) {
            throw new CustomException("查询失败(Query Failure)");
        }
        return Result.Ok("查询成功(Query was successful)", userList);
    }

    /**
     * 获取在线用户(查询Redis中的RefreshToken)
     *
     * @param
     * @return
     * @author ch
     * @Date 2021/6/30 11:20
     */
    @GetMapping("/online")
    @ApiOperation("获取在线用户(查询Redis中的RefreshToken)")
    @RequiresPermissions(logical = Logical.AND, value = {"user:view"})
    public Result online() {
        List<Object> userDtos = new ArrayList<Object>();
        // 查询所有Redis键
        Set<String> keys = redisUtil.keys(DefContants.PREFIX_SHIRO_REFRESH_TOKEN + "*");
        for (String key : keys) {
            if (redisUtil.hasKey(key)) {
                // 根据:分割key，获取最后一个字符(帐号)
                String[] strArray = key.split(":");
                String account = strArray[strArray.length - 1];
                LambdaQueryWrapper<User> queryWrapper = Wrappers.lambdaQuery();
                queryWrapper.select(User::getAccount,User::getUsername)
                        .eq(User::getAccount, account);
                User userInfo = userService.getOne(queryWrapper);
                System.out.println(userInfo);
                UserDTO userDto = new UserDTO();
                // 对象拷贝
                BeanUtils.copyProperties(userInfo,userDto);
                // 设置登录时间
                userDto.setLoginTime(new Date(Long.parseLong(redisUtil.get(key).toString())));
                userDtos.add(userDto);
            }
        }
        if (userDtos == null || userDtos.size() < 0) {
            throw new CustomException("查询失败(Query Failure)");
        }
        return Result.Ok("查询成功(Query was successful)", userDtos);
    }

}