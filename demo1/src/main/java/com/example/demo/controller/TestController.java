package com.example.demo.controller;


import com.example.demo.service.SysUserService;
import com.example.demo.utill.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    private SysUserService userService;

    @GetMapping("/test")
    public Result test(){
        return Result.success("信息返回成功","123");
    }
}

