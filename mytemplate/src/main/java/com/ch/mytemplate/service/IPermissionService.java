package com.ch.mytemplate.service;

import com.ch.mytemplate.entity.Permission;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ch.mytemplate.entity.Role;

import java.util.List;

/**
 * <p>
 * 资源表 服务类
 * </p>
 *
 * @author ch
 * @since 2021-06-29
 */
public interface IPermissionService extends IService<Permission> {
    List<Permission> queryPermissionByRole(Role roleInfo);
}
