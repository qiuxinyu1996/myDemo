package com.qiuxinyu.shiro;

import com.qiuxinyu.entity.User;
import com.qiuxinyu.service.PermService;
import com.qiuxinyu.service.RoleService;
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
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class MyRealm extends AuthorizingRealm {
    private static final String SALT = "salt";

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private PermService permService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();

        // 获取当前用户
        String username = principalCollection.asList().get(0).toString();
        // 添加角色权限
        authorizationInfo.addRoles(roleService.getRolesByUsername(username));
        // 添加资源权限
        authorizationInfo.addStringPermissions(permService.getPermsByUsername(username));

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
                ByteSource.Util.bytes(SALT),
                username);

        return authenticationInfo;
    }
}
