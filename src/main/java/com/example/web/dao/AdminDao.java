package com.example.web.dao;

import com.example.web.dao.db.DbConnect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class AdminDao {
    Connection conn = DbConnect.getConnection();

    public double getTotalRevenue() throws SQLException {
        double totalRevenue = 0;
        String query = "SELECT SUM(totalAmount) AS totalRevenue FROM orders WHERE status = 'hoàn thành'";

        PreparedStatement statement = conn.prepareStatement(query);
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            totalRevenue = resultSet.getDouble("totalRevenue");
        }


        return totalRevenue;
    }

    public int getTotalOrders() throws SQLException {
        int totalOrders = 0;
        String query = "SELECT COUNT(*) AS totalOrders FROM orders";

        PreparedStatement statement = conn.prepareStatement(query);
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            totalOrders = resultSet.getInt("totalOrders");
        }

        return totalOrders;
    }

    // 3. Tổng sản phẩm
    public int getTotalProducts() throws SQLException {
        int totalProducts = 0;
        String query = "SELECT COUNT(*) AS totalProducts FROM paintings";

        PreparedStatement statement = conn.prepareStatement(query);
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            totalProducts = resultSet.getInt("totalProducts");
        }

        return totalProducts;
    }

    // 4. Tổng người dùng
    public int getTotalUsers() throws SQLException {
        int totalUsers = 0;
        String query = "SELECT COUNT(*) AS totalUsers FROM users";

        PreparedStatement statement = conn.prepareStatement(query);
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            totalUsers = resultSet.getInt("totalUsers");
        }

        return totalUsers;
    }

    // 5. Doanh thu theo nghệ sĩ
    public Map<String, Double> getRevenueByArtist() throws SQLException {
        Map<String, Double> revenueByArtist = new HashMap<>();
        String query = "SELECT a.name AS artistName, SUM(o.totalAmount) AS revenue " +
                "FROM orders o " +
                "JOIN order_items oi ON o.id = oi.orderId " +
                "JOIN paintings p ON oi.paintingId = p.id " +
                "JOIN artists a ON p.artistId = a.id " +
                "WHERE o.status = 'hoàn thành' " +
                "GROUP BY a.name";

        PreparedStatement statement = conn.prepareStatement(query);
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            String artistName = resultSet.getString("artistName");
            double revenue = resultSet.getDouble("revenue");
            revenueByArtist.put(artistName, revenue);
        }

        return revenueByArtist;
    }

    // 6. Trạng thái đơn hàng
    public Map<String, Integer> getOrderStatusCount() throws SQLException {
        Map<String, Integer> orderStatusCount = new HashMap<>();
        String query = "SELECT status, COUNT(*) AS count FROM orders GROUP BY status";

        PreparedStatement statement = conn.prepareStatement(query);
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            String status = resultSet.getString("status");
            int count = resultSet.getInt("count");
            orderStatusCount.put(status, count);
        }

        return orderStatusCount;
    }

    public static void main(String[] args) throws SQLException {
        AdminDao a = new AdminDao();
        System.out.println(a.getOrderStatusCount());
        System.out.println(a.getTotalOrders());
        System.out.println(a.getTotalProducts());
        System.out.println(a.getRevenueByArtist());

    }

}
