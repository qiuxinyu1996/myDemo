package com.qiuxinyu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qiuxinyu.entity.Perm;
import com.qiuxinyu.entity.Role;
import com.qiuxinyu.entity.User;

import java.util.List;

public interface UserService extends IService<User> {
    User getUserByUsername(String username);

    List<Role> getRolesByUsername(String username);

    List<Perm> getPermsByUsername(String username);
}
