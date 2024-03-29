package com.qiuxinyu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qiuxinyu.entity.User;
import com.qiuxinyu.mapper.UserMapper;
import com.qiuxinyu.service.UserService;
import com.qiuxinyu.util.Md5Util;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.UUID;

@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<BaseMapper<User>, User> implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public String login(@RequestBody User user) {
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()) {
            log.info("has logged in");
            return "fail";
        }
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), user.getPassword());
        try {
            subject.login(token);
            log.info("login success : {}",user.getUsername());
            return "success";
        } catch (UnknownAccountException e) {
            log.info("login fail,username is not exist");
            return "fail";
        } catch (IncorrectCredentialsException e) {
            log.info("login fail,password is wrong");
            return "fail";
        } catch (Exception e) {
            log.info("unknown error");
            return "fail";
        }
    }

    @Override
    public String logout() {
        Subject subject = SecurityUtils.getSubject();
        if (!subject.isAuthenticated()) {
            log.info("has logged out");
            return "fail";
        }
        subject.logout();
        log.info("logout success");
        return "success";
    }

    @Override
    public String register(@RequestBody User user) {
        // 对密码进行md5加密后保存，加密方法必须和securityManager中的matcher保持一致
        User entity = new User();
        entity.setId(UUID.randomUUID().toString());
        entity.setUsername(user.getUsername());
        entity.setPassword(Md5Util.encrypt(user.getPassword()));

        try {
            userMapper.insert(entity);
        } catch (DuplicateKeyException e) {
            log.info("register fail,duplicate username");
            return "fail";
        }

        log.info("register success");
        return "success";
    }


    public User getUserByUsername(String username) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUsername, username);
        return userMapper.selectOne(queryWrapper);
    }
}
