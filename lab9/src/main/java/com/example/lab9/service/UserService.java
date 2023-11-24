package com.example.lab9.service;

import com.example.lab9.model.User;

public interface UserService {
    User register(User user);
    boolean authenticate(String username, String password);
}
