package com.example.demo.utill;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class TokenUtil {

    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @Value("${jwt.secrect}")
    private  String secrect;

    public String generateToken(UserDetails details){
        Map<String,Object> map = new HashMap<>(2);
        map.put("username",details.getUsername());
        map.put("password",details.getPassword());

        map.put("time",new Date());
        String token = Jwts.builder()
                .setClaims(map)
                .signWith(SignatureAlgorithm.HS512,secrect)
                .setExpiration(new Date(System.currentTimeMillis()+1800*1000))
                .compact();
        return token;
    }

    public Claims getTokenBody(String token){
        return Jwts.parser()
                .setSigningKey(secrect)
                .parseClaimsJws(token)
                .getBody();
    }

    public String getUseridBytoken(String token){
        return this.getTokenBody(token).get("id").toString();
    }

    public String getOpenidBytoken(String token){
        return this.getTokenBody(token).get("openid").toString();
    }
}
