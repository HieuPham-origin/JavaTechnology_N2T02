package com.hieu.dao;

import com.hieu.model.User;
import com.hieu.repository.IUser;

import java.util.List;

public class UserDAO extends AbstractDAO<User> implements IUser {
    public UserDAO(Class<User> clazz) {
        super(clazz);
    }

    @Override
    public User isValidUser(User usertable) {
        String hql = "FROM Usertable where email=?1 and pwd=?2";
        List<User> users = this.query(hql,usertable.getEmail(),usertable.getPwd());
        try {
            users.get(0);
            return   users.get(0);
        }catch (Exception e){
            return null;
        }
    }


    @Override
    public List<User> getAll() {
        String hql = "FROM Usertable";
        return this.query(hql);
    }
}
