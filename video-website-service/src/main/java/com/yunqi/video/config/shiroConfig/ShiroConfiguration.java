package com.yunqi.video.config.shiroConfig;


import org.apache.shiro.mgt.SessionStorageEvaluator;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.mgt.DefaultWebSessionStorageEvaluator;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.*;


/**
 * @author Tony
 */
@Configuration
public class ShiroConfiguration {

    /**
     * cookie对象
     * @return
     */
    public SimpleCookie rememberMeCookie() {
        // 设置cookie名称，对应login.html页面的<input type="checkbox" name="rememberMe"/>
        SimpleCookie cookie = new SimpleCookie("rememberMe");
        // 设置cookie的过期时间，单位为秒，这里为一天
        cookie.setMaxAge(86400);
        return cookie;
    }

    /**
     * cookie管理对象
     * @return
     */
    public CookieRememberMeManager rememberMeManager() {
        CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
        cookieRememberMeManager.setCookie(rememberMeCookie());
        // rememberMe cookie加密的密钥
        cookieRememberMeManager.setCipherKey("ZHANGXIAOHEI_CAT".getBytes());
        return cookieRememberMeManager;
    }
    /**
     * 用于登录验证的realm
     * 和用于Token验证的realm
     */
    @Bean
    public Realm loginRealm(){
        return new LoginRealm();
    }
    /**
     * 创建安全管理器
     */
    @Bean
    public DefaultWebSecurityManager securityManager(){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(loginRealm());
        securityManager.setRememberMeManager(rememberMeManager());
        return securityManager;
    }
    /**
     * 配置shiroFilter
     * @return
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilter(){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        //设置安全管理器
        shiroFilterFactoryBean.setSecurityManager(securityManager());
        //
        Map<String,String> map = new HashMap<>(10);
        map.put("/user/login","anon");
        map.put("/user/logout", "anon");
        map.put("/**","auth");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);
        LinkedHashMap<String, Filter> filterMaps = new LinkedHashMap<String, Filter>();
        filterMaps.put("auth", new MyFilter());
        shiroFilterFactoryBean.setFilters(filterMaps);
        return shiroFilterFactoryBean;
    }
}
