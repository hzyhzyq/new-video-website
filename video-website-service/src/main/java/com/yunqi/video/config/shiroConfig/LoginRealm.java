package com.yunqi.video.config.shiroConfig;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

/**
 * @author Tony
 */
public class LoginRealm extends AuthorizingRealm {


    @Override
    public boolean supports(AuthenticationToken token) {
        return token != null && UsernamePasswordToken.class.isAssignableFrom(token.getClass());
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        /*ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        String token = attributes.getRequest().getHeader("token");
        TokenUtil.checkToken(token);*/
        return new SimpleAuthenticationInfo("123","123",this.getName());
    }
}
