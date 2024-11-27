package com.dunjia.back.mapper;

import com.dunjia.back.pojo.Trade;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TradeMapper {


    void addTrade(Trade newTrade);
}
