package com.dunjia.back.mapper;

import com.dunjia.back.pojo.Product;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ProductMapper {

    @Select("select * from products where id = #{id}")
    Product getProductById(int id);

    @Select("select * from products")
    List<Product> getAllProducts();

    @Select("select * from products where name like concat('%', #{keyword}, '%')")
    List<Product> getSearchedProducts(String keyword);

    void addProduct(Product product);
}
