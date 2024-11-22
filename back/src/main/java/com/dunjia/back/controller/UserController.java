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

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public Result getAllUsers() {
        log.info("Get all users");
        List<User> users = userService.getAllUsers();
        return Result.success(users);
    }

    @RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
    public Result getUserById(@PathVariable Integer id) {
        log.info("Get user by id: {}", id);
        User user = userService.getUserById(id);
        return Result.success(user);
    }

    @RequestMapping(value = "/sellers/{id}", method = RequestMethod.GET)
    public Result getSellerById(@PathVariable Integer id) {
        log.info("Get seller by id: {}", id);
        User user = userService.getUserById(id);
        return Result.success(user);
    }

    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public Result addUser(@RequestBody User user) {
        log.info("Add user: {}", user);
        userService.addUser(user);
        return Result.success(null);
    }

    @RequestMapping(value = "/users", method = RequestMethod.PUT)
    public Result updateUser(@RequestBody User user) {
        log.info("Update user: {}", user);
        userService.updateUser(user);
        return Result.success(null);
    }
}
