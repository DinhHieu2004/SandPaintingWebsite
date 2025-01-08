package com.example.web.service;

import com.example.web.dao.UserDao;
import com.example.web.dao.model.User;

import java.sql.SQLException;
import java.util.List;

public class UserSerive {
    private UserDao userDao =  new UserDao();

    public List<User> getListUser() throws SQLException {
        return userDao.getListUser();
    }

    public static void main(String[] args) throws SQLException {
        UserSerive userSerive = new UserSerive();
        System.out.println(userSerive.getListUser());
    }
}
