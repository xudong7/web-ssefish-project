package com.dunjia.back.controller;

import com.dunjia.back.pojo.Result;
import com.dunjia.back.pojo.Trade;
import com.dunjia.back.service.TradeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
public class TradeController {
    @Autowired
    private TradeService tradeService;

    // 获取所有trade信息
    @GetMapping("/trades")
    public Result getAllTrades() {
        log.info("Get all trades");
        List<Trade> trades = tradeService.getAllTrades();
        return Result.success(trades);
    }

}
