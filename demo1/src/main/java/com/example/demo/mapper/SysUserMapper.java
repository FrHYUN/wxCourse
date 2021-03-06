package com.example.demo.mapper;

import entity.SysUser;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface SysUserMapper {
    List<SysUser> findAll();


    @Select("select * from user where openid = #{value}")
    SysUser findByOpenid(String value);

    @Select("select * from user where username = #{value}")
    SysUser findByuserName(String value);

    @Insert("insert into user(id ,username,password,avatar,openid) values(#{id},#{username},#{password},#{avatar},#{openid})")
    void  insert(SysUser user);

    @Update("update user set id=#{id},username=#{username},password=#{password},avatar=#{avatar} where openid=#{openid}")
    void update(SysUser user);
}
