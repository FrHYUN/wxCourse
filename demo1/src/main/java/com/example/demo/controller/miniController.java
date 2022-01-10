package com.example.demo.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.mapper.SysUserMapper;
import com.example.demo.service.SysUserService;
import com.example.demo.utill.Result;
import com.example.demo.utill.TokenUtil;
import entity.SysUser;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Slf4j
@RestController
public class miniController {

    @Autowired
    private SysUserService userService;

    @Autowired
    private TokenUtil tokenUtil;

    @Autowired
    private SysUserMapper userMapper;


    @PostMapping("/login")
    public Result login(@RequestBody String body) throws IOException {

        JSONObject json = JSONObject.parseObject(body);
        String code = (String)json.get("code");
        if(StringUtils.isEmpty(code)){
            return Result.fail("失败");
        }
        CloseableHttpClient client = HttpClientBuilder.create().build();

        String url = "https://api.weixin.qq.com/sns/jscode2session?appid="+
                "wx2a28193a6082cbb3"+
                "&secret="+
                "8738af4e82e47d0d3f6babcc4953cce3"+
                //"63907022a6e2c179eb1a9b7335d53c67"+
                "&js_code="+
                code +
                "&grant_type=authorization_code";

        HttpGet get = new HttpGet(url);
        CloseableHttpResponse response = client.execute(get);
        String result = EntityUtils.toString(response.getEntity());

        JSONObject jsonObject = JSON.parseObject(result);

        String openid = jsonObject.getString("openid");

        Result res = userService.miniLogin(openid);

        log.info(openid);

        return Result.success("成功",res);
    }

    @GetMapping("/user/info")
    public Result getUserInfo(HttpServletRequest request) {


        String token = request.getHeader("token");

        log.info(token);
        String openid = tokenUtil.getOpenidBytoken(token);


        SysUser user = userService.findByOpenid(openid);

        return new Result(0, "成功",user);
    }


    @PostMapping("/user/info")
    public Result updateUserinfo(HttpServletRequest request, @RequestBody String body){

        String token = request.getHeader("token");
        log.info("更新用户信息");
        String openid = tokenUtil.getOpenidBytoken(token);
        SysUser user = userService.findByOpenid(openid);

        JSONObject json = JSONObject.parseObject(body);

        log.info((String)json.get("nickname"));
        user.setUsername((String)json.get("nickname"));

        user.setAvatar((String)json.get("avatar"));
        log.info(user.getAvatar());
        log.info(user.getUsername());
        userMapper.update(user);

        return new Result(0, "成功",user);


    }




}
