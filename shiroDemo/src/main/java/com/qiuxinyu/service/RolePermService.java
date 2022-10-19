package com.qiuxinyu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qiuxinyu.entity.relationship.RolePerm;

public interface RolePermService extends IService<RolePerm> {

    String distributePermToRole(String roleName,String permName);
}
