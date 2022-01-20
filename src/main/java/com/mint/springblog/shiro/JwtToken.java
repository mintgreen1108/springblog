package com.mint.springblog.shiro;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * @author: mint
 * @Date: 2022/1/6 14:27
 */
public class JwtToken implements AuthenticationToken {

    private String token;

    public JwtToken(String jwt) {
        this.token = jwt;
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
