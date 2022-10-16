package com.qiuxinyu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qiuxinyu.entity.Perm;
import com.qiuxinyu.entity.Role;
import com.qiuxinyu.entity.relationship.RolePerm;
import com.qiuxinyu.mapper.PermMapper;
import com.qiuxinyu.service.PermService;
import com.qiuxinyu.service.RolePermService;
import com.qiuxinyu.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class PermServiceImpl extends ServiceImpl<BaseMapper<Perm>, Perm> implements PermService {
    @Autowired
    private PermMapper permMapper;

    @Autowired
    private RoleService roleService;

    @Autowired
    private RolePermService rolePermService;

    @Override
    public Perm getPermByPermName(String permName) {
        LambdaQueryWrapper<Perm> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Perm::getPerm, permName);

        return permMapper.selectOne(queryWrapper);
    }

    @Override
    public List<String> getPermsByRoleName(String roleName) {
        // 根据角色名获取角色
        Role role = roleService.getRoleByRoleName(roleName);

        // 获取该角色对应的权限id集合
        List<String> permIds = new ArrayList<>();
        LambdaQueryWrapper<RolePerm> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(RolePerm::getRoleId, role.getId());
        List<RolePerm> rolePerms = rolePermService.list(queryWrapper);
        for (RolePerm rolePerm : rolePerms) {
            permIds.add(rolePerm.getPermId());
        }

        // 将角色id集合转化为角色集合
        List<String> permNames = new ArrayList<>();
        for (String permId : permIds) {
            permNames.add(permMapper.selectById(permId).getPerm());
        }

        return permNames;
    }

    @Override
    public List<String> getPermsByUsername(String username) {
        // 获取该用户拥有的角色集合
        List<String> roleNames = roleService.getRolesByUsername(username);

        // 获取角色集合对应的权限集合，此处set用于去重
        Set<String> permSet = new HashSet<>();
        for (String roleName : roleNames) {
            permSet.addAll(getPermsByRoleName(roleName));
        }

        List<String> permNames = new ArrayList<>();
        permNames.addAll(permSet);

        return permNames;
    }
}
