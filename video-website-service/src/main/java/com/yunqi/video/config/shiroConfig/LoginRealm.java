package com.yunqi.video.config.shiroConfig;

import com.yunqi.video.domain.bean.User;
import com.yunqi.video.service.user.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Tony
 */
public class LoginRealm extends AuthorizingRealm {

    @Autowired
    UserService userService;

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
        String principal = (String)authenticationToken.getPrincipal();
        User user = userService.login(principal);
        if(user == null){
            return null;
        }
        Subject subject = SecurityUtils.getSubject();
        subject.getSession().setAttribute("currentUserId",user.getId());
        return new SimpleAuthenticationInfo(authenticationToken.getCredentials(),user.getUserPassword(),this.getName());
    }
}
