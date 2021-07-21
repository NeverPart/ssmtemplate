package com.ch.mytemplate.service.impl;

import com.ch.mytemplate.entity.UserRole;
import com.ch.mytemplate.mapper.UserRoleMapper;
import com.ch.mytemplate.service.IUserRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户角色表 服务实现类
 * </p>
 *
 * @author ch
 * @since 2021-06-29
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements IUserRoleService {

}
