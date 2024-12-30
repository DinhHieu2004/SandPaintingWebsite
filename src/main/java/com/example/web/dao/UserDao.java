package com.example.web.dao;

import com.example.web.dao.db.DbConnect;
import com.example.web.dao.model.User;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class UserDao {

    private Connection getConnection() throws SQLException {
        return DbConnect.getConnection();
    }

    public User findByUsername(String username) throws SQLException {
        String sql = "SELECT * FROM users WHERE username = ?";
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, username);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    int id = rs.getInt("id");
                    String fullName = rs.getString("fullName");
                    String uname = rs.getString("username");
                    String password = rs.getString("password");
                    String address = rs.getString("address");
                    String email = rs.getString("email");
                    String phone = rs.getString("phone");
                    User.Role role = User.Role.valueOf(rs.getString("role"));

                    return new User(id, fullName, uname, password, address, email, phone, role);
                }
            }
        }
        return null;
    }

    public boolean registerUser(String fullName, String username, String password, String address, String email, String phone, String role) throws SQLException {
        if (findByUsername(username) != null) {
            return false;
        }

        String hashedPassword = hashPassword(password);
        String sql = "INSERT INTO users (fullName, username, password, address, email, phone, role) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, fullName);
            ps.setString(2, username);
            ps.setString(3, hashedPassword);
            ps.setString(4, address);
            ps.setString(5, email);
            ps.setString(6, phone);
            ps.setString(7, role);

            return ps.executeUpdate() > 0;
        }
    }

    public String hashPassword(String password) {
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
