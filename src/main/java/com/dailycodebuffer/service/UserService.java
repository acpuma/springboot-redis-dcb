package com.dailycodebuffer.service;

import com.dailycodebuffer.model.User;

import java.util.List;

public interface UserService {
    boolean saveUser(User user);

    List<User> fetchAllUsers();

    User fetchUserById(Long id);

    boolean deleteUser(Long id);
}
