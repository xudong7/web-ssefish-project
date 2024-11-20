package com.dunjia.back.service;

import com.dunjia.back.mapper.UserMapper;
import com.dunjia.back.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public User login(User user) {
        return userMapper.getByUserNameAndPassword(user);
    }
}
