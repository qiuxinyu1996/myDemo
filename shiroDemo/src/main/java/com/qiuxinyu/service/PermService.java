package com.qiuxinyu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qiuxinyu.entity.Perm;

import java.util.List;

public interface PermService extends IService<Perm> {
    Perm getPermByPermName(String permName);

    List<String> getPermsByRoleName(String roleName);

    List<String> getPermsByUsername(String username);

    String createPerm(String permName);
}
