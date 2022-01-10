package com.example.demo.service.impl;

import com.example.demo.mapper.CardMapper;
import com.example.demo.service.CardService;
import entity.Card;
import entity.CardRecord;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Slf4j
public class CardServiceimpl implements CardService {

    @Autowired
    private CardMapper cardMapper;

    @Override
    public Card findByUserid(int userid) {
        return cardMapper.findByUserid(userid);
    }

    @Override
    public List<CardRecord> selectRecord(int cardid) {
        return cardMapper.selectRecord(cardid);
    }
}
