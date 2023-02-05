package com.qiuxinyu.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class TestController {
    @GetMapping("/")
    public String index() {
        return "this is index";
    }

    @GetMapping("/hello")
    public String sayHello() {
        return "hello, spring security";
    }
}
