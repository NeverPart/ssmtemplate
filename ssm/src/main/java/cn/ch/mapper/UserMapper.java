package cn.ch.mapper;

import cn.ch.entity.User;

/**
 * @author ch
 * @date 2021/5/13
 */
public interface UserMapper {

    User login(String username);
}
