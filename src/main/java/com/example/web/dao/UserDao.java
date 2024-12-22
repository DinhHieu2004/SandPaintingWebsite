package com.example.web.dao;

import com.example.web.dao.db.DbConnect;
import com.example.web.dao.model.User;

import javax.management.relation.Role;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class UserDao {
    private Connection con = DbConnect.getConnection();

    public User findUsername(String username) throws SQLException {
        String sql = "SELECT * FROM users WHERE username = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, username);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            int id = rs.getInt("id");
            String fullname = rs.getString("fullName");
            String uname = rs.getString("username");
            String password = rs.getString("password");
            String address = rs.getString("address");
            String email = rs.getString("email");
            String phone = rs.getString("phone");

            String roleString = rs.getString("role");
            User.Role role = User.Role.valueOf(roleString);

            return new User(id, fullname, uname, password, address, email, phone, role);
        }
        return null;
    }

    public boolean registerUser(String fullName, String username, String password, String address, String email, String phone, String role) throws SQLException {
        if (findUsername(username) == null) {
            String sql = "INSERT INTO users (fullName, username, password, address, email, phone, role) VALUES (?, ?, ?, ?, ?, ?, 'user')";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, fullName);
            ps.setString(2, username);
            ps.setString(3, hashPassword(password));
            ps.setString(4, address);
            ps.setString(5, email);
            ps.setString(6, phone);

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0; // Trả về true nếu thêm thành công
        }
        return false;
    }

    public String hashPassword(String password) {
        if (password == null || password.trim().isEmpty()) {
            throw new IllegalArgumentException("Password cannot be null or empty.");
        }

        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] hash = md.digest(password.getBytes());

            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }

            return hexString.toString(); // Trả về chuỗi mã hóa MD5
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error while hashing password with MD5", e);
        }
    }

}
