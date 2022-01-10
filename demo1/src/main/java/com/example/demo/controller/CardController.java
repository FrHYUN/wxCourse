package com.example.demo.controller;


import com.alibaba.fastjson.JSONObject;
import com.example.demo.mapper.CardMapper;
import com.example.demo.service.CardService;
import com.example.demo.utill.Result;
import com.example.demo.utill.TokenUtil;
import entity.Card;
import entity.CardRecord;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
public class CardController {

    @Autowired
    private CardMapper cardMapper;

    @Autowired
    private CardService cardService;

    @Autowired
    private TokenUtil tokenUtil;

    @PostMapping("/card/balance")
    public Result UpdateCardInfo(HttpServletRequest request, @RequestBody String body){

        String token = request.getHeader("token");
        String userid = tokenUtil.getUseridBytoken(token);
        JSONObject json = JSONObject.parseObject(body);
        log.info(token);
        log.info(userid);
        Card card = cardService.findByUserid(Integer.parseInt(userid));

        log.info((int)json.get("type")+"");
        if((int)json.get("type")==0){
        card.setBalance((int)json.get("value")-card.getBalance());
        CardRecord cardRecord = new CardRecord(card.getCardid(), new Date(),(int)json.get("value"),0,"预约课程成功");
        cardMapper.insertRecord(cardRecord);
        }
        else{
            log.info(card.getUserid()+"");
            card.setBalance((int)json.get("value")+card.getBalance());
            CardRecord cardRecord = new CardRecord(card.getCardid(), new Date(),(int)json.get("value"),1,"预约课程成功");
            cardMapper.insertRecord(cardRecord);
        }
        log.info("更新储蓄卡信息");
        cardMapper.update(card);


        return new Result(0,"更新成功",card);
    }

    @GetMapping("/card/info")
    public Result getCardInfo(HttpServletRequest request){
        String token = request.getHeader("token");
        String userid = tokenUtil.getUseridBytoken(token);
        Card card = cardService.findByUserid(Integer.parseInt(userid));
        Map<String,Object> map = new HashMap<>(2);
        map.put("balance",card.getBalance());
        map.put("record",cardService.selectRecord(card.getCardid()));
        
        return new Result(0,"成功",map);
    }

}
