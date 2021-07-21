package com.ch.mytemplate.mapper;

import com.ch.mytemplate.entity.Permission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ch.mytemplate.entity.Role;

import java.util.List;

/**
 * <p>
 * 资源表 Mapper 接口
 * </p>
 *
 * @author ch
 * @since 2021-06-29
 */
public interface PermissionMapper extends BaseMapper<Permission> {
    List<Permission> queryPermissionByRole(Role roleInfo);
}
