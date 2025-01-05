package com.example.web.dao;

import com.example.web.dao.db.DbConnect;
import com.example.web.dao.model.Order;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDao {
    private Connection conn = DbConnect.getConnection();
    public int createOrder(Order order) throws Exception {
        String sql = "INSERT INTO orders (userId, totalAmount, status, deliveryDate) VALUES (?, ?, ?, ?)";
        PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ps.setInt(1, order.getUserId());
        ps.setDouble(2, order.getTotalAmount());
        ps.setString(3, order.getStatus());
        ps.setDate(4, order.getDeliveryDate() != null ? java.sql.Date.valueOf(order.getDeliveryDate()) : null);

        ps.executeUpdate();
        try (ResultSet rs = ps.getGeneratedKeys()) {
            if (rs.next()) {
                return rs.getInt(1);
            }
        }
        throw new Exception("Unable to create order");
    }
    public List<Order> getCurrentOrdersForUser(int userId) throws Exception {
        List<Order> orders = new ArrayList<>();
        String query = "SELECT * FROM orders WHERE userId = ? AND status IN ('chờ', 'đang giao') ORDER BY orderDate DESC";

        PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, userId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Order order = extractOrderFromResultSet(rs);
                    orders.add(order);
                }
            }
        return orders;
    }
    public List<Order> getHistoryOrder(int userId) throws Exception {
        List<Order> orders = new ArrayList<>();
        String query = "SELECT * FROM orders WHERE userId = ? AND status IN ('hoàn thành', 'thất bại','đã hủy') ORDER BY orderDate DESC";

        PreparedStatement ps = conn.prepareStatement(query);
        ps.setInt(1, userId);

        try (ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Order order = extractOrderFromResultSet(rs);
                orders.add(order);
            }
        }
        return orders;
    }
    private Order extractOrderFromResultSet(ResultSet rs) throws Exception {
        Order order = new Order();
        order.setId(rs.getInt("id"));
        order.setUserId(rs.getInt("userId"));
        order.setOrderDate(rs.getTimestamp("orderDate").toLocalDateTime());
        order.setTotalAmount(rs.getDouble("totalAmount"));
        order.setStatus(rs.getString("status"));
        order.setDeliveryDate(rs.getDate("deliveryDate") != null ? rs.getDate("deliveryDate").toLocalDate().atStartOfDay() : null);
        return order;
    }

    public static void main(String[] args) throws Exception {
        OrderDao orderDao = new OrderDao();
        for(Order o : orderDao.getCurrentOrdersForUser(2)){
            System.out.println(o);
        }
    }
}