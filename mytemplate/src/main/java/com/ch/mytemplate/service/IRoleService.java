package com.ch.mytemplate.service;

import com.ch.mytemplate.entity.Role;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 角色表 服务类
 * </p>
 *
 * @author ch
 * @since 2021-06-29
 */
public interface IRoleService extends IService<Role> {

    List<Role> queryRoleByUserAccount(String account,String username);

}
