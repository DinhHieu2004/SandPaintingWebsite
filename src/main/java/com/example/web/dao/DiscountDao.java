package com.example.web.dao;

import com.example.web.dao.db.DbConnect;
import com.example.web.dao.model.Discount;
import com.example.web.dao.model.Painting;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class DiscountDao {
    Connection conn = DbConnect.getConnection();

    public DiscountDao() {
    }

    public List<Discount> getAllDiscount() throws SQLException {
        String sql = "SELECT * FROM discounts";
        List<Discount> list = new ArrayList<>();

        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Timestamp timestamp = rs.getTimestamp("createdAt");
            LocalDateTime createdAt = timestamp != null
                    ? timestamp.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime()
                    : null;

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

        return list;
    }

    public List<Painting> getPaintingsByDiscountId(int discountId) throws SQLException {
        String sql = """
        SELECT 
            p.id,
            p.title,
            p.description,
            p.imageUrl,
            a.name AS artistName,
            t.themeName AS themeName,
            p.price,
            p.createdAt
        FROM 
            paintings p
        JOIN 
            discount_paintings dp ON p.id = dp.paintingId
        JOIN 
            artists a ON p.artistId = a.id
        JOIN 
            themes t ON p.themeId = t.id
        WHERE 
            dp.discountId = ?;
    """;

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, discountId);
            ResultSet rs = stmt.executeQuery();
            List<Painting> paintingList = new ArrayList<>();

            while (rs.next()) {
                Painting painting = new Painting();
                painting.setId(rs.getInt("id"));
                painting.setTitle(rs.getString("title"));
                painting.setPrice(rs.getDouble("price"));
                painting.setImageUrl(rs.getString("imageUrl"));
                painting.setThemeName(rs.getString("themeName"));
                painting.setArtistName(rs.getString("artistName"));
                painting.setCrateDate(rs.getDate("createdAt"));
                painting.setDescription(rs.getString("description"));

                paintingList.add(painting);
            }
            return paintingList;
        }
    }

    public String getDiscountNameById(int discountId) throws SQLException {
        String sql = "SELECT discountName FROM discounts WHERE id = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        String result = "";
        ps.setInt(1, discountId);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            result = rs.getString("discountName");
        }
        return result;
    }
    public void assignProductsToDiscount(int discountId, List<Integer> productIds) throws SQLException {
        String sql = "INSERT INTO discount_paintings (discountId, paintingId) VALUES (?, ?)";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            // Lặp qua danh sách productIds và chèn từng bản ghi
            for (int productId : productIds) {
                ps.setInt(1, discountId); // Gán giá trị discountId
                ps.setInt(2, productId); // Gán giá trị paintingId
                ps.addBatch();
            }

            // Thực thi batch
            ps.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Không thể gán sản phẩm vào chương trình giảm giá.", e);
        }
    }


    public void removeProductFromDiscount(int productId) throws SQLException {
        String sql = "DELETE FROM discount_paintings WHERE paintingId = ?";

        try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setInt(1, productId);
            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Sản phẩm đã được xóa khỏi chương trình giảm giá.");
            } else {
                System.out.println("Không tìm thấy sản phẩm trong chương trình giảm giá.");
            }
        }
    }

    public static void main(String[] args) throws SQLException {
        DiscountDao dao = new DiscountDao();
    }

}

