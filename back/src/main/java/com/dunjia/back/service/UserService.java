package com.dunjia.back.service;

import com.dunjia.back.mapper.UserMapper;
import com.dunjia.back.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    public void deleteUserById(Integer id) {
        userMapper.deleteUserById(id);
    }

    public void toggleProductInWantList(Integer userId, Integer productId) {
        System.out.println("userId: " + userId + " productId: " + productId);

        // Fetch user object from the database
        User user = userMapper.getUserById(userId);
        System.out.println("user: " + user);

        // Get the current want list
        String wantList = user.getWantList();

        // Initialize a StringBuilder to build the updated want list
        StringBuilder updatedWantList = new StringBuilder();

        // Check if wantList is empty or null
        if (wantList == null || wantList.isEmpty()) {
            updatedWantList.append(productId); // Add productId if list is empty
        } else {
            // Split the current wantList by commas to get individual product IDs
            String[] productIds = wantList.split(",");
            boolean isProductIdPresent = false;

            // Loop through the product IDs to check if the productId is already in the list
            for (String id : productIds) {
                if (id.equals(String.valueOf(productId))) {
                    isProductIdPresent = true;
                } else {
                    // Add the id to the updated list if it's not the one we want to toggle
                    if (updatedWantList.length() > 0) {
                        updatedWantList.append(","); // Add comma if not the first element
                    }
                    updatedWantList.append(id);
                }
            }

            // If the productId was not found, add it to the list (for toggling behavior)
            if (!isProductIdPresent) {
                if (updatedWantList.length() > 0) {
                    updatedWantList.append(","); // Add comma before appending new productId
                }
                updatedWantList.append(productId);
            }
        }

        // Update the wantList in the user object
        user.setWantList(updatedWantList.toString());

        // Update the user in the database
        userMapper.updateUser(user);
    }

}
