package com.example.web.dao;

import com.example.web.dao.db.DbConnect;
import com.example.web.dao.model.Discount;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import java.sql.*;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class DiscountDao {
    private Connection con = DbConnect.getConnection();

    public DiscountDao() {}

    public List<Discount> getAllDiscount() {
        String sql = "SELECT * FROM discounts";
        List<Discount> list = new ArrayList<>();
        try (Connection conn = con;
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Timestamp timestamp = rs.getTimestamp("createdAt");
                LocalDateTime createdAt = timestamp != null ? timestamp.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime() : null;

                Discount discount = new Discount(
                        rs.getInt("id"),
                        rs.getString("imageUrl"),
                        rs.getString("discountName"),
                        rs.getBigDecimal("discountPercentage"),
                        rs.getDate("startDate").toLocalDate(),
                        rs.getDate("endDate").toLocalDate(),
                        createdAt
                );
                list.add(discount);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

}

