package com.ch.mytemplate.service.impl;

import com.ch.mytemplate.entity.User;
import com.ch.mytemplate.mapper.UserMapper;
import com.ch.mytemplate.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author ch
 * @since 2021-06-29
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Resource
    private UserMapper userMapper;
    @Override
    public List<User> queryAllUser() {
        return userMapper.queryAllUser();
    }
}
