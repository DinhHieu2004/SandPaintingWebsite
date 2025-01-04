package com.example.web.dao;
import com.example.web.dao.db.DbConnect;
import com.example.web.dao.model.OrderItem;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class OrderItemDao {
    private Connection conn = DbConnect.getConnection();
    public void addOrderItem(OrderItem orderItem) throws Exception {
        String sql = "INSERT INTO order_items (orderId, paintingId, sizeId,quantity , price) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement ps = conn.prepareStatement(sql) ;
        ps.setInt(1, orderItem.getOrderId());
        ps.setInt(2, orderItem.getPaintingId());
        ps.setInt(3, orderItem.getSizeId());
        ps.setInt(4, orderItem.getQuantity());
        ps.setDouble(5, orderItem.getPrice());
        ps.executeUpdate();

    }
}