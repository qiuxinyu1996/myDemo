package com.qiuxinyu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qiuxinyu.entity.relationship.UserRole;
import org.springframework.web.bind.annotation.PathVariable;

public interface UserRoleService extends IService<UserRole> {
    String distributeRoleToUser(String username, String roleName);
}
