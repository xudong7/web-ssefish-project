package com.dunjia.back.service;

import com.dunjia.back.mapper.ProductMapper;
import com.dunjia.back.pojo.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;
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

    public Product getProductById(Integer id) {
        return productMapper.getProductById(id);  // 使用Mapper接口获取产品数据
    }

    public void addProduct(Product product) {
        product.setCreateTime(LocalDateTime.now()); // 设置产品创建时间
        productMapper.addProduct(product);  // 使用Mapper接口添加产品
    }

    public void deleteProductById(Integer id) {
        productMapper.deleteProductById(id);  // 使用Mapper接口删除产品
    }

    public List<Product> getPublishedProducts(Integer id) {
        return productMapper.getPublishedProducts(id);  // 使用Mapper接口获取已发布产品
    }

    public List<Product> getProductsListByPriceFromH() {
        return productMapper.getProductsListByPriceFromH();
    }

    public List<Product> getProductsListByPriceFromL() {
        return productMapper.getProductsListByPriceFromL();
    }

    public List<Product> getProductsListByTime() {
        return productMapper.getProductsListByTime();
    }

    public List<Product> getProductsListByTimeWeek() {
        return productMapper.getProductsListByTimeWeek();
    }

    public List<Product> getProductsListByTimeMonth() {
        return productMapper.getProductsListByTimeMonth();
    }
}