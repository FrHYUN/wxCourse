package com.example.demo.service.impl;

import com.example.demo.mapper.SysUserMapper;
import com.example.demo.service.SysUserService;
import com.example.demo.utill.Result;
import com.example.demo.utill.TokenUtil;
import com.example.demo.vo.loginvo;
import entity.SysUser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class SysUserServiceimpl implements SysUserService {

    @Autowired
    private SysUserMapper usermapper;


    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private TokenUtil tokenUtil;

    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @Value("${jwt.secrect}")
    private  String secrect;



    @Override
    public Result login(loginvo loginvo) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(loginvo.getUsername());
        if(null == userDetails||passwordEncoder.matches(loginvo.getUsername(),loginvo.getPassword())){
            return Result.fail("账号或密码错误");
        }
        String token = "";
        Map<String,String> map = new HashMap<>(2);
        map.put("tokenHead",tokenHead);
        map.put("token",token);


        return Result.success("",map);
    }

    @Override
    public Result miniLogin(String openid) {


        SysUser user = usermapper.findByOpenid(openid);
        if(null == user){
            user.setId(new Date().toString());
            user.setOpenid(openid);
            usermapper.insert(user);
        }

        Map<String,Object> map = new HashMap<>(2);
        map.put("id",user.getId());
        map.put("openid",user.getOpenid());

        map.put("time",new Date());
        String token = Jwts.builder()
                           .setClaims(map)
                            .signWith(SignatureAlgorithm.HS512,secrect)
                            .setExpiration(new Date(System.currentTimeMillis()+1800*1000))
                           .compact();
        Map<String,Object> m = new HashMap<>(2);
        m.put("token",token);
        m.put("is_new","");
        log.info(user.getUsername());

        return Result.success("成功",m);



    }

    @Override
    public SysUser findByuserName(String username) {
        return null;
    }
}
