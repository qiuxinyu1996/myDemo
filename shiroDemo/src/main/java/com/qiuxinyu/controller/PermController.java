package com.qiuxinyu.controller;

import com.qiuxinyu.service.PermService;
import com.qiuxinyu.service.RolePermService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/perm")
public class PermController {

    @Autowired
    private RolePermService rolePermService;

    @Autowired
    private PermService permService;

    @GetMapping("create/{permName}")
    public String createPerm(@PathVariable String permName) {
        return permService.createPerm(permName);
    }

    @GetMapping("/distribute/{rolaName}/{permName}")
    public String distributePermToRole(@PathVariable String rolaName, @PathVariable String permName) {
        return rolePermService.distributePermToRole(rolaName, permName);
    }
}
