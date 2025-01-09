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

    public List<Painting> getPaintingsByDiscountId(int id) throws SQLException {
        String sql = """
       
                SELECT p.id,
                                      p.title,
                                      p.description,
                                      p.imageUrl,
                                      a.name AS artistName,
                                      t.themeName AS themeName,
                                      p.price,
                                      p.createdAt,
                                      p.discountId -- Thêm cột này vào
                               FROM paintings p
                               JOIN artists a ON p.artistId = a.id
                               JOIN themes t ON p.themeId = t.id
                               WHERE p.discountId = ?;
                               
        """;


        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();
        List<Painting> paintingList = new ArrayList<>();
        while (rs.next()) {
            Painting painting = new Painting();
            int paintingId = rs.getInt("id");
            String title = rs.getString("title");
            double price = rs.getDouble("price");
            String imageUrl = rs.getString("imageUrl");
            String theme = rs.getString("themeName");
            Date createdAt = rs.getDate("createdAt");
            String artistName = rs.getString("artistName");
            int discountId = rs.getInt("discountId");
            painting.setId(paintingId);
            painting.setTitle(title);
            painting.setPrice(price);
            painting.setImageUrl(imageUrl);
            painting.setThemeName(theme);
            painting.setThemeName(theme);
            painting.setArtistName(artistName);
            painting.setCrateDate(createdAt);
            painting.setDescription(rs.getString("description"));
            painting.setDiscountId(discountId);
            paintingList.add(painting);
        }
        return paintingList;
    }

    public static void main(String[] args) throws SQLException {
        DiscountDao dao = new DiscountDao();
        List<Painting> list = dao.getPaintingsByDiscountId(1);
        for (Painting painting : list) {
            System.out.println(painting);
        }
    }
}

