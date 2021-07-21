package com.ch.mytemplate.entity.dto;

import com.ch.mytemplate.entity.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;


/**
 * @className: UserDto
 * @Auther: ch
 * @Date: 2021/6/30 10:05
 * @Description: TODO
 */
@Data
public class UserDTO extends User {
    /**
     * 帐号
     */
    private String account;

    /**
     * 昵称
     */
    private String password;

    /**
     * 登录时间
     */
    //@Transient
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date loginTime;
}
