package com.qiuxinyu.service.impl;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qiuxinyu.entity.Perm;
import com.qiuxinyu.entity.Role;
import com.qiuxinyu.entity.relationship.RolePerm;
import com.qiuxinyu.mapper.RolePermMapper;
import com.qiuxinyu.service.PermService;
import com.qiuxinyu.service.RolePermService;
import com.qiuxinyu.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class RolePermServiceImpl extends ServiceImpl<BaseMapper<RolePerm>, RolePerm> implements RolePermService {

    @Autowired
    private RoleService roleService;

    @Autowired
    private PermService permService;

    @Autowired
    private RolePermMapper rolePermMapper;

    @Override
    public String distributePermToRole(String roleName, String permName) {
        Role role = roleService.getRoleByRoleName(roleName);
        Perm perm = permService.getPermByPermName(permName);
        if (role == null || perm == null) {
            return "bad request param";
        }
        RolePerm rolePerm = new RolePerm(UUID.randomUUID().toString(), role.getId(), perm.getId());
        if (rolePermMapper.insert(rolePerm) > 0) {
            return "distribute perm success";
        }
        return "distribute perm fail";
    }
}
