package com.dunjia.back.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Trade {
    private User buyer;
    private User seller;
    private Product product;
    private String status; // 订单进行中 -- 1 已完成订单 -- 2 订单取消 -- 3
    private Date createDate;
}
