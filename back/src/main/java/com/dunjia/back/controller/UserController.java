package com.dunjia.back.controller;

import com.dunjia.back.pojo.Result;
import com.dunjia.back.pojo.User;
import com.dunjia.back.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    private UserService userService;

    // 获取所有user信息
    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public Result getAllUsers() {
        log.info("Get all users");
        List<User> users = userService.getAllUsers();
        return Result.success(users);
    }

    // 根据id获取user信息
    @RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
    public Result getUserById(@PathVariable Integer id) {
        log.info("Get user by id: {}", id);
        User user = userService.getUserById(id);
        return Result.success(user);
    }

    // 根据id获取seller信息
    @RequestMapping(value = "/sellers/{id}", method = RequestMethod.GET)
    public Result getSellerById(@PathVariable Integer id) {
        log.info("Get seller by id: {}", id);
        User user = userService.getUserById(id);
        return Result.success(user);
    }

    // 添加user
    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public Result addUser(@RequestBody User user) {
        log.info("Add user: {}", user);
        userService.addUser(user);
        return Result.success(null);
    }

    // 更新user信息
    @RequestMapping(value = "/users", method = RequestMethod.PUT)
    public Result updateUser(@RequestBody User user) {
        log.info("Update user: {}", user);
        userService.updateUser(user);
        return Result.success(null);
    }

    // 删除user
    @RequestMapping(value = "/users/{id}", method = RequestMethod.DELETE)
    public Result deleteUserById(@PathVariable Integer id) {
        log.info("delete user by id: {}", id);
        userService.deleteUserById(id);
        return Result.success(null);
    }
}
