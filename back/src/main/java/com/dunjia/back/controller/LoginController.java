package com.dunjia.back.controller;

import com.dunjia.back.pojo.Result;
import com.dunjia.back.pojo.User;
import com.dunjia.back.service.UserService;
import com.dunjia.back.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
public class LoginController {
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public Result LoginController(@RequestBody User user) {
        log.info("登录请求：{}", user);

        User userDB = userService.login(user);

        // 登录成功 -> 生成 jwt
        if (userDB != null) {
            Map<String, Object> claims = new HashMap<>();
            claims.put("id", userDB.getId());
            claims.put("name", userDB.getName());
            String jwt = JwtUtils.generateJwt(claims);
            return Result.success(jwt);
        }

        // 登录失败
        return Result.error("用户名或密码错误");
    }
}
