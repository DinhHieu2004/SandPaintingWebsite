package com.example.web.service;


import com.example.web.dao.UserDao;
import com.example.web.dao.model.User;

import java.sql.SQLException;

public class AuthService {
    public boolean checkLogin(String username, String password) throws SQLException {
        UserDao userDao = new UserDao();
        User user = userDao.findByUsername(username);

        if (user == null) {
            return false; // Người dùng không tồn tại
        }

        // So sánh mật khẩu mã hóa
        String hashedPassword = userDao.hashPassword(password);
        return hashedPassword.equals(user.getPassword());
    }

    public boolean checkRegister(String fullName, String username, String password, String address, String email, String phone, String role) throws SQLException {
        UserDao userDao = new UserDao();
        User existingUser = userDao.findByUsername(username);

        if (existingUser != null) {
            return false; // Tên đăng nhập đã tồn tại
        }

        String hashedPassword = userDao.hashPassword(password);
        return userDao.registerUser(fullName, username, hashedPassword, address, email, phone, role);
    }
}

