package com.ch.mytemplate.jwt;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.ch.mytemplate.contants.DefContants;
import com.ch.mytemplate.entity.Permission;
import com.ch.mytemplate.entity.Role;
import com.ch.mytemplate.entity.User;
import com.ch.mytemplate.entity.dto.UserDTO;
import com.ch.mytemplate.service.IPermissionService;
import com.ch.mytemplate.service.IRoleService;
import com.ch.mytemplate.service.IUserService;
import com.ch.mytemplate.utils.JwtUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * 自定义Realm
 *
 * @author dolyw.com
 * @date 2018/8/30 14:10
 */
@Service
public class ShiroRealm extends AuthorizingRealm {

    @Resource
    private IUserService userService;
    @Resource
    private IRoleService roleService;
    @Resource
    private IPermissionService permissionService;
    /**
     * 必须重写此方法，不然Shiro会报错,更改
     */
    @Override
    public boolean supports(AuthenticationToken authenticationToken) {
        return authenticationToken instanceof JwtToken;
    }

    /**
     * 只有当需要检测用户权限的时候才会调用此方法，例如checkRole,checkPermission之类的
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        String account = JwtUtils.getClaim(principalCollection.toString(), DefContants.ACCOUNT);

        // 查询用户角色
        List<Role> roleList = roleService.queryRoleByUserAccount(account,"");
        for (Role roleInfo : roleList) {
            if (roleInfo != null) {
                // 添加角色
                simpleAuthorizationInfo.addRole(roleInfo.getName());
                // 根据用户角色查询权限
                List<Permission> permissionList = permissionService.queryPermissionByRole(roleInfo);
                for (Permission permissionInfo : permissionList) {
                    if (permissionInfo != null) {
                        // 添加权限
                        simpleAuthorizationInfo.addStringPermission(permissionInfo.getPerCode());
                    }
                }
            }
        }
        return simpleAuthorizationInfo;
    }

    /**
     * 默认使用此方法进行用户名正确与否验证，错误抛出异常即可。
     * 认证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        // 获取token,getCredentials()方法在jwtToken中已重写
        String token = (String) authenticationToken.getCredentials();
        // 解密获得account，用于和数据库进行对比
        String account = JwtUtils.getClaim(token, DefContants.ACCOUNT);
        // 帐号为空
        if (StringUtils.isEmpty(account)) {
            throw new AuthenticationException("Token中帐号为空(The account in Token is empty.)");
        }
        // 查询用户是否存在
        UserDTO userDTO = new UserDTO();
        userDTO.setAccount(account);
        LambdaQueryWrapper<User> userCondition = Wrappers.lambdaQuery();
        userCondition.eq(User::getAccount, account);
        User userInfo = userService.getOne(userCondition);
        if (userInfo == null) {
            throw new AuthenticationException("该帐号不存在(The account does not exist.)");
        }
        // 开始认证，要AccessToken认证通过，且Redis中存在RefreshToken，且两个Token时间戳一致
        if (JwtUtils.verify(token)) {
            return new SimpleAuthenticationInfo(token, token, this.getName());
            // 获取Redis中存放的RefreshToken的时间戳

            // 获取AccessToken时间戳，与RefreshToken的时间戳对比
            /*if (JwtUtils.getClaim(token, Constant.CURRENT_TIME_MILLIS).equals(currentTimeMillisRedis)) {
                return new SimpleAuthenticationInfo(token, token, this.getName());
            }*/
        }
        throw new AuthenticationException("Token已过期(Token expired or incorrect.)");
    }
}
