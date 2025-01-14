package com.example.web.dao;

import com.example.web.dao.db.DbConnect;
import com.example.web.dao.model.ProductReview;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ReviewDao {
    private Connection con = DbConnect.getConnection();


    public boolean saveReview(ProductReview review) throws SQLException {
        String sql = "INSERT INTO product_reviews (userId, paintingId, rating, comment, createdAt) VALUES (?, ?, ?, ?, NOW())";
        PreparedStatement stmt = con.prepareStatement(sql);

        stmt.setInt(1, review.getUserId());
        stmt.setInt(2, review.getPaintingId());
        stmt.setInt(3, review.getRating());
        stmt.setString(4, review.getComment());
        int rowsInserted = stmt.executeUpdate();
        return rowsInserted > 0;


    }
    public boolean hasReviewed(int userId, int paintingId, int orderItemId) throws Exception {
        String query = "SELECT COUNT(*) FROM product_reviews WHERE userId = ? AND paintingId = ? AND orderItemId = ?";
        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, userId);
            ps.setInt(2, paintingId);
            ps.setInt(3, orderItemId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        }
        return false;
    }
    public void addReview(ProductReview review) throws Exception {
        String query = "INSERT INTO product_reviews (userId, paintingId, orderItemId, rating, comment) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, review.getUserId());
            ps.setInt(2, review.getPaintingId());
            ps.setInt(3, review.getOrderItemId());
            ps.setInt(4, review.getRating());
            ps.setString(5, review.getComment());
            ps.executeUpdate();
        }
    }

}
