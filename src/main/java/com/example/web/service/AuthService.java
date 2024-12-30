package com.example.web.service;


import com.example.web.dao.UserDao;
import com.example.web.dao.model.User;

import java.sql.SQLException;

public class AuthService {
    public String checkLogin(String username, String password) throws SQLException {
        UserDao userDao = new UserDao();
        User user = userDao.findByUsername(username);

        if (user != null && checkPassword(username, password)) { // Gọi phương thức checkPassword
            return String.valueOf(user.getRole()); // Trả về vai trò người dùng nếu đăng nhập thành công
        }
        return null; // Nếu không thành công
    }

    public boolean checkPassword(String username, String password) throws SQLException {
        UserDao userDao = new UserDao();
        User user = userDao.findByUsername(username); // Lấy người dùng theo username (không phải password)
        if (user == null) {
            return false; // Nếu không tìm thấy người dùng
        }
        // So sánh mật khẩu đã mã hóa
        String hashedPassword = userDao.hashPassword(password);
        return hashedPassword.equals(user.getPassword());
    }


}

