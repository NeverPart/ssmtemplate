package com.ch.mytemplate.mapper;

import com.ch.mytemplate.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author ch
 * @since 2021-06-29
 */
public interface UserMapper extends BaseMapper<User> {
    List<User> queryAllUser();
}
