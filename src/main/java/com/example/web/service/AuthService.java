package com.example.web.service;


import com.example.web.dao.UserDao;
import com.example.web.dao.model.User;

import java.sql.SQLException;

public class AuthService {
    public boolean checkLogin(String uname, String pass) throws SQLException {
        UserDao udao= new UserDao();
        User u = udao.findUsername(uname);
        if(u==null)  return false;
        String hashedPass = udao.hashPassword(pass);
        return hashedPass.equals(u.getPassword());
    }
    public boolean checkRegister(String fullName, String username, String password, String address, String email, String phone, String role) throws SQLException {
        // Kiểm tra xem username đã tồn tại chưa
        UserDao udao= new UserDao();
        User existingUser = udao.findUsername(username);
        if (existingUser != null) {
            return false; // Tên đăng nhập đã tồn tại
        }

        // Thêm người dùng mới với mật khẩu mã hóa
        return udao.registerUser(fullName, username, password, address, email, phone, role);
    }
}
