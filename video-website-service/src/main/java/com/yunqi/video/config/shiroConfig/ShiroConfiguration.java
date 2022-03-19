package com.yunqi.video.config.shiroConfig;


import org.apache.shiro.mgt.SessionStorageEvaluator;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.mgt.DefaultWebSessionStorageEvaluator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;


/**
 * @author Tony
 */
@Configuration
public class ShiroConfiguration {
    /**
     * 禁用session, 不保存用户登录状态。保证每次请求都重新认证。
     */
    @Bean
    protected SessionStorageEvaluator sessionStorageEvaluator(){
        DefaultWebSessionStorageEvaluator sessionStorageEvaluator = new DefaultWebSessionStorageEvaluator();
        sessionStorageEvaluator.setSessionStorageEnabled(false);
        return sessionStorageEvaluator;
    }
    /**
     * 用于登录验证的realm
     * 和用于Token验证的realm
     */
    @Bean(name = "loginRealm")
    public Realm loginRealm(){
        return new LoginRealm();
    }
    @Bean(name = "jwtRealm")
    public Realm jwtRealm(){
        return new JwtRealm();
    }
    /**
     * 创建安全管理器
     */
    @Bean()
    public DefaultWebSecurityManager securityManager(@Qualifier("loginRealm") Realm loginRealm,@Qualifier("jwtRealm") Realm jwtRealm){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        Collection<Realm> realms = new ArrayList<>();
        realms.add(loginRealm);
        realms.add(jwtRealm);
        securityManager.setRealms(realms);
        return securityManager;
    }
    /**
     * 配置shiroFilter
     * @return
     */
    @Bean(name = "shiroFilterFactoryBean")
    public ShiroFilterFactoryBean shiroFilter(DefaultWebSecurityManager defaultWebSecurityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        //设置安全管理器
        shiroFilterFactoryBean.setSecurityManager(defaultWebSecurityManager);
        //
        Map<String,String> map = new HashMap<>(10);
        map.put("/video/test","anon");
        map.put("/**","anon");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);
        return shiroFilterFactoryBean;
    }
}
