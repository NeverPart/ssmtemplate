package cn.ch.service;

import cn.ch.entity.User;

/**
 * @author ch
 * @date 2021/5/13
 */
public interface IUserService extends BaseService<User> {

    User login(String username);
}
