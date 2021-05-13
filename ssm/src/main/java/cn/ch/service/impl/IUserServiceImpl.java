package cn.ch.service.impl;

import cn.ch.mapper.UserMapper;
import cn.ch.entity.User;
import cn.ch.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ch
 * @date 2021/5/13
 */
@Service
public class IUserServiceImpl implements IUserService {

    //注入
    @Autowired
    private UserMapper userMapper;

    /**
     * 用户登录的方法
     */
    @Override
    public User login(String username) {
        return userMapper.login(username);
    }

    public List<User> findAll() {
        return null;
    }

    public User findById(Long id) {
        return null;
    }

    public void create(User user) {

    }

    public void delete(Long id) {

    }

    public void update(User user) {

    }
}
