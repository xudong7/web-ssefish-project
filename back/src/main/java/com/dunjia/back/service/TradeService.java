package com.dunjia.back.service;

import com.dunjia.back.mapper.TradeMapper;
import com.dunjia.back.pojo.Trade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TradeService {
    @Autowired
    private TradeMapper tradeMapper;

    public void addTrade(Trade newTrade) {
        tradeMapper.addTrade(newTrade);
    }
}
