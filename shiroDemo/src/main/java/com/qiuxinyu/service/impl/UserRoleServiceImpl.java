package com.qiuxinyu.service.impl;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qiuxinyu.entity.Role;
import com.qiuxinyu.entity.User;
import com.qiuxinyu.entity.relationship.UserRole;
import com.qiuxinyu.mapper.UserRoleMapper;
import com.qiuxinyu.service.RoleService;
import com.qiuxinyu.service.UserRoleService;
import com.qiuxinyu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserRoleServiceImpl extends ServiceImpl<BaseMapper<UserRole>, UserRole> implements UserRoleService {

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Override
    public String distributeRoleToUser(String username, String roleName) {
        User user = userService.getUserByUsername(username);
        Role role = roleService.getRoleByRoleName(roleName);
        if (user == null || role == null) {
            return "bad request param";
        }
        UserRole userRole = new UserRole(UUID.randomUUID().toString(), user.getId(), role.getId());
        if (userRoleMapper.insert(userRole) > 0) {
            return "distribute success";
        }
        return "distribute fail";
    }
}
