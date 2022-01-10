package com.example.demo.service;

import entity.Card;
import entity.CardRecord;

import java.util.List;

public interface CardService {

    Card findByUserid(int userid);

    List<CardRecord> selectRecord(int cardid);
}
