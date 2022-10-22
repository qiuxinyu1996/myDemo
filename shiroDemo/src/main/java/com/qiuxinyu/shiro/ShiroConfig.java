package com.qiuxinyu.shiro;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {
    @Autowired
    private MyRealm myRealm;

    @Bean
    public DefaultWebSecurityManager securityManager(MyRealm myRealm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();

        // 设置md5加密
        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher();
        matcher.setHashAlgorithmName("md5");
        matcher.setHashIterations(1024);
        myRealm.setCredentialsMatcher(matcher);

        securityManager.setRealm(myRealm);
        return securityManager;
    }

    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
        ShiroFilterFactoryBean filterFactory = new ShiroFilterFactoryBean();
        filterFactory.setSecurityManager(securityManager);

        Map<String, String> filterMap = new LinkedHashMap<String, String>();
        // 设置登出路径
        filterMap.put("/api/user/logout", "logout");
        // 需要授权
        filterMap.put("/api/user/select", "perms[user:select]");
        filterMap.put("/api/user/add", "perms[user:add]");
        filterMap.put("/api/user/delete", "perms[user:delete]");
        filterMap.put("/api/user/update", "perms[user:update]");
        filterMap.put("/api/user/sleep", "perms[sleep]");
        filterMap.put("/api/user/run", "perms[run]");
        // 需要认证
        filterMap.put("/api/user/index", "authc");
        // 无需认证
        filterMap.put("/api/user/login", "anon");
        filterFactory.setFilterChainDefinitionMap(filterMap);

        filterFactory.setLoginUrl("/index.html");

        return filterFactory;
    }
}
