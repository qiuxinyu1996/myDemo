package com.qiuxinyu.service.impl;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qiuxinyu.entity.Perm;
import com.qiuxinyu.entity.Role;
import com.qiuxinyu.mapper.RoleMapper;
import com.qiuxinyu.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl extends ServiceImpl<BaseMapper<Role>, Role> implements RoleService {
    @Autowired
    private RoleMapper roleMapper;

    @Override
    public List<Perm> getPermsByRole(String role) {
        return roleMapper.getPermsByRole(role);
    }
}
