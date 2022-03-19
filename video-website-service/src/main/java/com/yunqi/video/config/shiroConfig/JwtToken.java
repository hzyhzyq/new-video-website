package com.yunqi.video.config.shiroConfig;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * @author hzyhz
 */
public class JwtToken implements AuthenticationToken {

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
