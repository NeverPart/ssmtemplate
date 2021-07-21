package com.ch.mytemplate.service.impl;

import com.ch.mytemplate.entity.Role;
import com.ch.mytemplate.mapper.RoleMapper;
import com.ch.mytemplate.service.IRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author ch
 * @since 2021-06-29
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

    @Resource
    private RoleMapper roleMapper;

    @Override
    public List<Role> queryRoleByUserAccount(String account, String username) {

        return roleMapper.queryRoleByUserAccount(account, username);
    }
}
