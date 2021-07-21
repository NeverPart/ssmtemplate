package com.ch.mytemplate.jwt;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * JwtToken
 * @author zch
 * @date 2021年4月8日10:08:54
 */
public class JwtToken implements AuthenticationToken {
    /**
     * Token
     */
    private String token;

    public JwtToken(String token) {
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}
