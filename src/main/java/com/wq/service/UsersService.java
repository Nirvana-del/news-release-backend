package com.wq.service;

import com.wq.entity.User;

import java.util.List;

public interface UsersService {
    List<User> getUserList();

    void addUser(User user);

    void deleteUser(String id);

    void updateUser(String id, User user);

    List<User> searchUser(String username, String password);
}
