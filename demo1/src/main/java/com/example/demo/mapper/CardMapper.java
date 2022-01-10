package com.example.demo.mapper;

import entity.Card;
import entity.CardRecord;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface CardMapper {

    @Insert("insert into card(cardid,balance,userid) values(#{cardid},#{balance},#{userid})")
    void insert(Card card);

    @Update("update card set balance=#{balance} where userid=#{userid}")
    void update(Card card);

    @Select("select * from card where userid = #{value}")
    Card findByUserid(int value);

    @Insert("insert into card_record(recordid,cardid,time,value,type,event) values(#{recordid},#{cardid},#{time},#{value},#{type},#{event})")
    void insertRecord(CardRecord cardRecord);

    @Select("select * from card_record where cardid=#{value} order by time DESC limit 0,20 ")
    List<CardRecord> selectRecord(int value);
}
