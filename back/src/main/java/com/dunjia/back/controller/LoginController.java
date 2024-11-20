package com.dunjia.back.controller;

import com.dunjia.back.pojo.Result;
import com.dunjia.back.pojo.User;
import com.dunjia.back.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

@Slf4j
@RestController
public class LoginController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/api/login", method = RequestMethod.POST)
    public Result LoginController(@RequestBody User user) {
        log.info("登录请求：{}", user);

        User userDB = userService.login(user);

        return userDB != null ? Result.success() : Result.error("用户名或密码错误");
    }
}
