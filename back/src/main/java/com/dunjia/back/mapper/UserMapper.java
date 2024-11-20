package com.dunjia.back.mapper;

import com.dunjia.back.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {

    @Select("select * from users where name = #{name} and password = #{password}")
    User getByUserNameAndPassword(User user);
}
