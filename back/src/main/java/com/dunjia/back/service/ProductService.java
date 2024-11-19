package com.dunjia.back.service;

import com.dunjia.back.mapper.ProductMapper;
import com.dunjia.back.pojo.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductMapper productMapper;

    public List<Product> getAllProducts() {
        return productMapper.getAllProducts();  // 使用Mapper接口获取产品数据
    }

    public List<Product> getSearchedProducts(String keyword) {
        return productMapper.getSearchedProducts(keyword);  // 使用Mapper接口获取搜索结果
    }
}
