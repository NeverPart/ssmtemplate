package com.ch.mytemplate.mapper;

import com.ch.mytemplate.entity.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 角色表 Mapper 接口
 * </p>
 *
 * @author ch
 * @since 2021-06-29
 */
public interface RoleMapper extends BaseMapper<Role> {

    List<Role> queryRoleByUserAccount(@Param("account") String account, @Param("username") String username);

}
