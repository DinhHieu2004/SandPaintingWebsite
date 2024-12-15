package com.example.web.dao;

import com.example.web.dao.db.DbConnect;
import com.example.web.dao.model.Painting;
import com.example.web.service.PaintingService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PaintingDao {
    Connection con = DbConnect.getConnection();

    public PaintingDao() {

    }
    public Painting getPaintingDetail(int paintingId) throws SQLException {
        Painting paintingDetail = null;
        String sql = """
        SELECT 
            p.id AS paintingId,
            p.title AS paintingTitle,
            p.price,
            p.description,
            p.imageUrl,
            a.name AS artistName,
            t.themeName,
            d.discountName,
            d.discountPercentage,
            s.sizeDescription,
            ps.quantity AS sizeQuantity
        FROM paintings p
        LEFT JOIN artists a ON p.artistId = a.id
        LEFT JOIN themes t ON p.themeId = t.id
        LEFT JOIN discount_paintings dp ON p.id = dp.paintingId
        LEFT JOIN discounts d ON dp.discountId = d.id
        LEFT JOIN painting_sizes ps ON p.id = ps.paintingId
        LEFT JOIN sizes s ON ps.sizeId = s.id
        WHERE p.id = ?;
    """;

        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, paintingId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    if (paintingDetail == null) {
                        // Initialize the PaintingDetail object
                        paintingDetail = new Painting(
                                rs.getInt("paintingId"),
                                rs.getString("paintingTitle"),
                                rs.getDouble("price"),
                                rs.getString("description"),
                                rs.getString("imageUrl"),
                                rs.getString("artistName"),
                                rs.getString("themeName")
                        );
                    }

                    // Add size and quantity to the painting detail
                    String sizeDescription = rs.getString("sizeDescription");
                    int sizeQuantity = rs.getInt("sizeQuantity");
                    paintingDetail.addSize(sizeDescription, sizeQuantity);

                    // Add discount information if exists
                    if (rs.getString("discountName") != null) {
                        paintingDetail.setDiscount(
                                rs.getString("discountName"),
                                rs.getDouble("discountPercentage")
                        );
                    }
                }
            }
        }
        return paintingDetail;
    }



    public List<Painting> getPaintingList() throws SQLException {
         List<Painting> paintingList = new ArrayList<>();
        String sql = "SELECT p.id AS paintingId, p.title AS paintingTitle, p.imageUrl, " +
                "a.name AS artistName, t.themeName AS theme, " +
                "IFNULL(d.discountPercentage, 0) AS discount " +
                "FROM paintings p " +
                "LEFT JOIN artists a ON p.artistId = a.id " +
                "LEFT JOIN themes t ON p.themeId = t.id " +
                "LEFT JOIN discount_paintings dp ON p.id = dp.paintingId " +
                "LEFT JOIN discounts d ON dp.discountId = d.id";

        PreparedStatement stmt = con.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            Painting painting = new Painting();
            painting.setId(rs.getInt("paintingId"));
            painting.setTitle(rs.getString("paintingTitle"));
            painting.setImageUrl(rs.getString("imageUrl"));
            painting.setArtistName(rs.getString("artistName"));
            painting.setThemeName(rs.getString("theme"));
            painting.setDiscountPercentage(rs.getDouble("discount"));

            paintingList.add(painting);
        }
        return paintingList;
    }

    public static void main(String[] args) throws SQLException {
        PaintingDao dao = new PaintingDao();
        for (Painting p : dao.getPaintingList()) {
            System.out.println(p);

            // System.out.println(dao.getPaintingDetail(1));
        }
    }


}