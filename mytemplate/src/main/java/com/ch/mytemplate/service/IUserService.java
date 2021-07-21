package com.ch.mytemplate.service;

import com.ch.mytemplate.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author ch
 * @since 2021-06-29
 */
public interface IUserService extends IService<User> {
    List<User> queryAllUser();
}
