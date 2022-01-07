package com.example.demo.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.service.SysUserService;
import com.example.demo.utill.Result;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@Slf4j
@RestController
public class miniController {

    @Autowired
    private SysUserService userService;


    @GetMapping("/login")
    public Result login(String code) throws IOException {
        if(StringUtils.isEmpty(code)){
            return Result.fail("shibai");
        }
        CloseableHttpClient client = HttpClientBuilder.create().build();

        String url = "https://api.weixin.qq.com/sns/jscode2session?appid="+
                "wx02e5986b1e033f63"+
                "&secret="+
                "63907022a6e2c179eb1a9b7335d53c67"+
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

        return Result.success("chenggong",res);
    }


}
