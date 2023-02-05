package com.qiuxinyu.controller;

import com.qiuxinyu.common.Result;
import com.qiuxinyu.domain.entity.LoginUser;
import com.qiuxinyu.domain.entity.User;
import com.qiuxinyu.util.JWTUtils;
import com.alibaba.fastjson.JSON;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Objects;

@RestController
@RequestMapping("/api/user")
public class LoginController {
    @Resource
    private AuthenticationManager authenticationManager;

    @Resource
    private RedisTemplate redisTemplate;

    @PostMapping("/login")
    public Result login(@RequestBody User user) {
        UsernamePasswordAuthenticationToken request = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword(), null);
        Authentication authenticate = authenticationManager.authenticate(request);
        if (Objects.isNull(authenticate)) {
            throw new RuntimeException("登录失败");
        }
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
        String token = JWTUtils.generateToken(loginUser.getUser().getId());
        // 存入redis
        String key = "userId:" + loginUser.getUser().getId();
        redisTemplate.opsForValue().set(key, JSON.toJSONString(loginUser));

        return Result.success("登录成功", token);
    }

    @PostMapping("/logout")
    public Result logout() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        String userId = loginUser.getUser().getId();
        String key = "userId:" + userId;
        if (!redisTemplate.delete(key)) {
            throw new RuntimeException("登出异常");
        }
        return Result.success("登出成功");
    }

}
