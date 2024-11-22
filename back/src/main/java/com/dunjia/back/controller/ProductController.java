package com.dunjia.back.controller;

import com.dunjia.back.pojo.Product;
import com.dunjia.back.pojo.Result;
import com.dunjia.back.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api")
public class ProductController {
    @Autowired
    private ProductService productService;

    // 获取所有product信息
    @RequestMapping(value = "/products", method = RequestMethod.GET)
    public Result getAllProducts() {
        log.info("Get all products");
        List<Product> products = productService.getAllProducts();
        return Result.success(products);
    }

    // 根据关键字搜索product信息
    @RequestMapping(value = "/products/search", method = RequestMethod.GET)
    public Result getSearchedProducts(@RequestParam(defaultValue = "") String keyword) {
        log.info("Get searched products with keyword: {}", keyword);

        List<Product> products = productService.getSearchedProducts(keyword);
        return Result.success(products);
    }

    // 根据id获取product信息
    @RequestMapping(value = "/products/{id}", method = RequestMethod.GET)
    public Result getProductById(@PathVariable Integer id) {
        log.info("Get product by id: {}", id);
        Product product = productService.getProductById(id);
        return Result.success(product);
    }

    // 添加product
    @RequestMapping(value = "/products", method = RequestMethod.POST)
    public Result addProduct(@RequestBody Product product) {
        log.info("Add product: {}", product);
        productService.addProduct(product);
        return Result.success(null);
    }

    // 根据id删除product
    @RequestMapping(value = "/products/{id}", method = RequestMethod.DELETE)
    public Result deleteProductById(@PathVariable Integer id) {
        log.info("delete product by id: {}", id);
        productService.deleteProductById(id);
        return Result.success(null);
    }

    // 更新product信息
    @RequestMapping(value = "/products", method = RequestMethod.PUT)
    public Result updateProduct(@RequestBody Product product) {
        log.info("Update product: {}", product);
        productService.updateProduct(product);
        return Result.success(null);
    }

    // 根据用户id得到其发布的product
    @RequestMapping(value = "/publishedProducts/{id}", method = RequestMethod.GET)
    public Result getPublishedProducts(@PathVariable Integer id) {
        log.info("Get published products by id: {}", id);
        List<Product> products = productService.getPublishedProducts(id);
        return Result.success(products);
    }

}
