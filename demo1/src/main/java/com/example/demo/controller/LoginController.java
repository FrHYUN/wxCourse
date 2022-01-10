package com.example.demo.controller;


import com.example.demo.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {


    @Autowired
    private SysUserService userService;




    @PostMapping("/user/login")
    public String login(){
           return "userService.findAll()";
    }


}
