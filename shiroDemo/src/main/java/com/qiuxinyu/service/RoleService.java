package com.qiuxinyu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qiuxinyu.entity.Perm;
import com.qiuxinyu.entity.Role;

import java.util.List;

public interface RoleService extends IService<Role> {
    List<Perm> getPermsByRole(String role);
}
