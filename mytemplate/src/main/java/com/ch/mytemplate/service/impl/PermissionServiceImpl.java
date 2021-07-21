package com.ch.mytemplate.service.impl;

import com.ch.mytemplate.entity.Permission;
import com.ch.mytemplate.entity.Role;
import com.ch.mytemplate.mapper.PermissionMapper;
import com.ch.mytemplate.service.IPermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 资源表 服务实现类
 * </p>
 *
 * @author ch
 * @since 2021-06-29
 */
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements IPermissionService {

    @Resource
    private PermissionMapper permissionMapper;

    @Override
    public List<Permission> queryPermissionByRole(Role roleInfo) {
        return permissionMapper.queryPermissionByRole(roleInfo);
    }
}
