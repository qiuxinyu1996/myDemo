package com.qiuxinyu.shiro;

import com.qiuxinyu.entity.Role;
import com.qiuxinyu.entity.User;
import com.qiuxinyu.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class MyRealm extends AuthorizingRealm {
    private static final String SALT = "salt";

    @Autowired
    private UserService userService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();

        // 获取当前用户
        String username = principalCollection.asList().get(0).toString();

        // 添加角色权限
        List<String> roles = new ArrayList<>();
        userService.getRolesByUsername(username).forEach(role -> roles.add(role.getRole()));
        authorizationInfo.addRoles(roles);

        // 添加资源权限
        List<String> perms = new ArrayList<>();
        userService.getPermsByUsername(username).forEach(perm -> perms.add(perm.getPerm()));
        authorizationInfo.addStringPermissions(perms);

        log.info("{} ---> {} ---> {}", username, roles, perms);
        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        // 判断该用户是否存在
        String username = authenticationToken.getPrincipal().toString();
        User user = userService.getUserByUsername(username);
        if (user == null) {
            return null;
        }

        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                authenticationToken.getPrincipal(),
                user.getPassword(),
                username);

        return authenticationInfo;
    }
}
