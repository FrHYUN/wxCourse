package com.example.demo.service;

import com.example.demo.utill.Result;
import com.example.demo.vo.loginvo;
import entity.SysUser;

public interface SysUserService {

    Result login(loginvo loginvo);

    Result miniLogin(String openid);

    SysUser findByuserName(String username);

    SysUser findByOpenid(String username);



}
