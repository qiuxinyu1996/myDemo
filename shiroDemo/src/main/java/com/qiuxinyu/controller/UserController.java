package com.qiuxinyu.controller;

import com.qiuxinyu.entity.User;
import com.qiuxinyu.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public String login(@RequestBody User user) {
        return userService.login(user);
    }

    @PostMapping("/logout")
    public String logout() {
        return userService.logout();
    }

    @PostMapping("/register")
    public String register(@RequestBody User user) {
        return userService.register(user);
    }
}
