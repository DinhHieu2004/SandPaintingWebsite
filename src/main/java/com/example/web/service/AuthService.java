package com.example.web.service;


import com.example.web.dao.UserDao;
import com.example.web.dao.model.User;

import java.sql.SQLException;

import java.sql.SQLException;

public class AuthService {

    public User checkLogin(String username, String password) throws SQLException {
        UserDao userDao = new UserDao();
        User user = userDao.findByUsername(username);

        if (user != null && checkPassword(password, user.getPassword())) {
            user.setPassword(null); // Xóa mật khẩu trước khi trả về
            return user;
        }
        return null;
    }

    private boolean checkPassword(String rawPassword, String hashedPassword) {
        UserDao userDao = new UserDao();
        String hashedInput = userDao.hashPassword(rawPassword);
        return hashedInput.equals(hashedPassword);
    }
}
