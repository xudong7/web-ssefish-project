package com.dunjia.back.service;

import com.dunjia.back.mapper.UserMapper;
import com.dunjia.back.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public User login(User user) {
        return userMapper.getByUserNameAndPassword(user);
    }

    public List<User> getAllUsers() {
        return userMapper.getAllUsers();
    }

    public User getUserById(Integer id) {
        return userMapper.getUserById(id);
    }

    public void addUser(User user) {
        userMapper.addUser(user);
    }

    public void updateUser(User user) {
        userMapper.updateUser(user);
    }
}
