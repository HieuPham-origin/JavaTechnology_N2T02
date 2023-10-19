package com.hieu.repository;

import com.hieu.model.User;

public interface IUser extends genericDAO<User> {
    User isValidUser(User user);
}