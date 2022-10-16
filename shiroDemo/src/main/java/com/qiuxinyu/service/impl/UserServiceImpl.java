package com.qiuxinyu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qiuxinyu.entity.Perm;
import com.qiuxinyu.entity.Role;
import com.qiuxinyu.entity.User;
import com.qiuxinyu.mapper.UserMapper;
import com.qiuxinyu.service.RoleService;
import com.qiuxinyu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl extends ServiceImpl<BaseMapper<User>, User> implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleService roleService;

    public User getUserByUsername(String username) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUsername, username);
        return userMapper.selectOne(queryWrapper);
    }

    @Override
    public List<Role> getRolesByUsername(String username) {
        return userMapper.getRolesByUsername(username);
    }

    @Override
    public List<Perm> getPermsByUsername(String username) {
        List<Perm> perms = new ArrayList<>();
        List<Role> roles = getRolesByUsername(username);
        for (Role role : roles) {
            perms.addAll(roleService.getPermsByRole(role.getRole()));
        }
        return perms;
    }
}
