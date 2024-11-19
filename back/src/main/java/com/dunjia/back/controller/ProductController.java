package com.dunjia.back.controller;

import com.dunjia.back.pojo.Product;
import com.dunjia.back.pojo.Result;
import com.dunjia.back.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.io.ResolverUtil;
import org.apache.ibatis.jdbc.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api")
public class ProductController {
    @Autowired
    private ProductService productService;

    @RequestMapping(value = "/products", method = RequestMethod.GET)
    public Result getAllProducts() {
        log.info("Get all products");
        List<Product> products = productService.getAllProducts();
        return Result.success(products);
    }

    @RequestMapping(value = "/products/search", method = RequestMethod.GET)
    public Result getSearchedProducts(@RequestParam(defaultValue = "") String keyword) {
        log.info("Get searched products with keyword: {}", keyword);

        List<Product> products = productService.getSearchedProducts(keyword);
        return Result.success(products);
    }

}
