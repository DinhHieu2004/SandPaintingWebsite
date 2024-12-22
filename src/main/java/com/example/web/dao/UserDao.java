package com.example.web.dao;

import com.example.web.dao.db.DbConnect;
import com.example.web.dao.model.User;

import javax.management.relation.Role;
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
}
