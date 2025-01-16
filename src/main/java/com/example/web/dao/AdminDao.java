package com.example.web.dao;

import com.example.web.dao.db.DbConnect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class AdminDao {
    private Connection conn = DbConnect.getConnection();

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
    public Map<String, Double> getArtistRevenueMap() throws SQLException {
        Map<String, Double> revenueMap = new HashMap<>();

        String sql = """
            SELECT 
                a.name AS artist_name,
                COALESCE(SUM(oi.price * oi.quantity), 0) AS total_revenue
            FROM 
                artists a
                LEFT JOIN paintings p ON a.id = p.artistId
                LEFT JOIN order_items oi ON p.id = oi.paintingId
                LEFT JOIN orders o ON oi.orderId = o.id AND o.status = 'hoàn thành'
            GROUP BY 
                a.name
            ORDER BY 
                total_revenue DESC
        """;

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String artistName = rs.getString("artist_name");
                double revenue = rs.getDouble("total_revenue");
                revenueMap.put(artistName, revenue);
            }
        }

        return revenueMap;
    }

    public static void main(String[] args) throws SQLException {
        AdminDao a = new AdminDao();
      //  System.out.println(a.getOrderStatusCount());
       // System.out.println(a.getTotalOrders());
       // System.out.println(a.getTotalProducts());
        System.out.println(a.getArtistRevenueMap());

    }

}
