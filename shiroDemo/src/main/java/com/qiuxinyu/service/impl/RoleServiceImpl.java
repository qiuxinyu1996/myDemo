package com.qiuxinyu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qiuxinyu.entity.Role;
import com.qiuxinyu.entity.User;
import com.qiuxinyu.entity.relationship.UserRole;
import com.qiuxinyu.mapper.RoleMapper;
import com.qiuxinyu.mapper.UserRoleMapper;
import com.qiuxinyu.service.RoleService;
import com.qiuxinyu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class RoleServiceImpl extends ServiceImpl<BaseMapper<Role>, Role> implements RoleService {
    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Override
    public Role getRoleByRoleName(String roleName) {
        LambdaQueryWrapper<Role> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Role::getRoleName, roleName);

        return roleMapper.selectOne(queryWrapper);
    }

    @Override
    public List<String> getRolesByUsername(String username) {
        // 根据用户名获取用户
        User user = userService.getUserByUsername(username);

        // 获取该用户对应的角色id集合
        List<String> roleIds = new ArrayList<>();
        LambdaQueryWrapper<UserRole> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserRole::getUserId, user.getId());
        List<UserRole> userRoles = userRoleMapper.selectList(queryWrapper);
        for (UserRole userRole : userRoles) {
            roleIds.add(userRole.getRoleId());
        }

        // 将角色id集合转化为角色集合
        List<String> roleNames = new ArrayList<>();
        for (String roleId : roleIds) {
            roleNames.add(roleMapper.selectById(roleId).getRoleName());
        }

        return roleNames;
    }

    @Override
    public String createRole(String roleName) {
        Role role = new Role(UUID.randomUUID().toString(), roleName);
        if (roleMapper.insert(role) > 0) {
            return "create role success";
        }
        return "create role fail";
    }
}
