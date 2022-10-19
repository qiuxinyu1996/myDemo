package com.qiuxinyu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qiuxinyu.entity.User;

import java.util.List;

public interface UserService extends IService<User> {
    String login(User user) ;

    String logout() ;

    String register(User user) ;

    User getUserByUsername(String username);
}
