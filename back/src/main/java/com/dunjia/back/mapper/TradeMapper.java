package com.dunjia.back.mapper;

import com.dunjia.back.pojo.Trade;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface TradeMapper {


    void addTrade(Trade newTrade);

    @Select("select * from trades")
    List<Trade> getAllTrades();
}
