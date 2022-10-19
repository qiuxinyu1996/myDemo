package com.qiuxinyu.controller;

import com.qiuxinyu.service.RoleService;
import com.qiuxinyu.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserRoleService userRoleService;

    @GetMapping("/create/{roleName}")
    public String createRole(@PathVariable String roleName) {
        return roleService.createRole(roleName);
    }

    @GetMapping("/distribute/{username}/{roleName}")
    public String distributeRoleToUser(@PathVariable String username, @PathVariable String roleName) {
        return userRoleService.distributeRoleToUser(username, roleName);
    }
}
