package com.example.web.dao;

import com.example.web.dao.db.DbConnect;
import com.example.web.dao.model.Painting;
import com.example.web.service.PaintingService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
                        s.id AS idSize,
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
                        paintingDetail = new Painting(rs.getInt("paintingId"), rs.getString("paintingTitle"), rs.getDouble("price"), rs.getString("description"), rs.getString("imageUrl"), rs.getString("artistName"), rs.getString("themeName"));
                    }

                    // Add size and quantity to the painting detail
                    int idSize = rs.getInt("idSize");
                    String sizeDescription = rs.getString("sizeDescription");
                    int sizeQuantity = rs.getInt("sizeQuantity");
                    paintingDetail.addSize(idSize, sizeDescription, sizeQuantity);

                    // Add discount information if exists
                    if (rs.getString("discountName") != null) {
                        paintingDetail.setDiscount(rs.getString("discountName"), rs.getDouble("discountPercentage"));
                    }
                }
            }
        }
        return paintingDetail;
    }


    public List<Painting> getPaintingList(Double minPrice, Double maxPrice, String[] sizes, String[] themes, String[] artists) throws SQLException {
        List<Painting> paintingList = new ArrayList<>();
        Map<Integer, Painting> paintingMap = new HashMap<>();

        StringBuilder sql = new StringBuilder("""
                    SELECT 
                        p.id AS paintingId,
                        p.title AS paintingTitle,
                        p.price,
                        p.imageUrl,
                        a.name AS artistName,
                        t.themeName AS theme,
                        IFNULL(d.discountPercentage, 0) AS discount,
                        s.sizeDescription AS size,
                        ps.quantity AS stock
                    FROM paintings p
                    LEFT JOIN artists a ON p.artistId = a.id
                    LEFT JOIN themes t ON p.themeId = t.id
                    LEFT JOIN discount_paintings dp ON p.id = dp.paintingId
                    LEFT JOIN discounts d ON dp.discountId = d.id
                    LEFT JOIN painting_sizes ps ON p.id = ps.paintingId
                    LEFT JOIN sizes s ON ps.sizeId = s.id
                    WHERE 1=1
                """);

        List<Object> params = new ArrayList<>();

        if (minPrice != null) {
            sql.append(" AND p.price >= ?");
            params.add(minPrice);
        }
        if (maxPrice != null) {
            sql.append(" AND p.price <= ?");
            params.add(maxPrice);
        }

        if (sizes != null && sizes.length > 0) {
            sql.append(" AND s.id IN (").append("?,".repeat(sizes.length - 1)).append("?)");
            params.addAll(List.of(sizes));
        }

        if (themes != null && themes.length > 0) {
            sql.append(" AND t.id IN (").append("?,".repeat(themes.length - 1)).append("?)");
            params.addAll(List.of(themes));
        }

        if (artists != null && artists.length > 0) {
            sql.append(" AND a.id IN (").append("?,".repeat(artists.length - 1)).append("?)");
            params.addAll(List.of(artists));
        }

        sql.append(" AND ps.quantity > 0");

        try (PreparedStatement stmt = con.prepareStatement(sql.toString())) {
            for (int i = 0; i < params.size(); i++) {
                stmt.setObject(i + 1, params.get(i));
            }

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    int paintingId = rs.getInt("paintingId");

                    if (paintingMap.containsKey(paintingId)) {
                    } else {
                        Painting painting = new Painting();
                        painting.setId(paintingId);
                        painting.setTitle(rs.getString("paintingTitle"));
                        painting.setImageUrl(rs.getString("imageUrl"));
                        painting.setArtistName(rs.getString("artistName"));
                        painting.setThemeName(rs.getString("theme"));
                        painting.setDiscountPercentage(rs.getDouble("discount"));
                        painting.setPrice(rs.getDouble("price"));
                        painting.setSizes(new ArrayList<>());
                        paintingMap.put(paintingId, painting);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Lỗi khi lấy danh sách tranh có lọc", e);
        }

        paintingList.addAll(paintingMap.values());

        return paintingList;
    }

    public List<Painting> getListPaintingByArtist(int artistId) throws SQLException {
        Map<Integer, Painting> paintingMap = new HashMap<>();
        List<Painting> paintings = new ArrayList<>();
        String sql = """
                            SELECT 
                            p.id AS paintingId,
                            p.title AS paintingTitle,
                            p.price,
                            p.imageUrl,
                            a.name AS artistName,
                            t.themeName AS theme,
                            IFNULL(d.discountPercentage, 0) AS discount,
                            s.sizeDescription AS size,
                            ps.quantity AS stock
                        FROM paintings p
                        LEFT JOIN artists a ON p.artistId = a.id
                        LEFT JOIN themes t ON p.themeId = t.id
                        LEFT JOIN discount_paintings dp ON p.id = dp.paintingId
                        LEFT JOIN discounts d ON dp.discountId = d.id
                        LEFT JOIN painting_sizes ps ON p.id = ps.paintingId
                        LEFT JOIN sizes s ON ps.sizeId = s.id
                        WHERE artistId = ?;
                    """;

        PreparedStatement statement = con.prepareStatement(sql);

        statement.setInt(1, artistId); // Đặt giá trị id của nghệ sĩ
        try (ResultSet rs = statement.executeQuery()) {
            while (rs.next()) {
                int paintingId = rs.getInt("paintingId");

                if (paintingMap.containsKey(paintingId)) {
                } else {
                    Painting painting = new Painting();
                    painting.setId(rs.getInt("paintingId"));
                    painting.setTitle(rs.getString("paintingTitle"));
                    painting.setImageUrl(rs.getString("imageUrl"));
                    painting.setThemeName(rs.getString("theme"));
                    painting.setDiscountPercentage(rs.getDouble("discount"));
                    painting.setPrice(rs.getDouble("price"));
                    paintingMap.put(paintingId, painting);
                }
            }
        }
        paintings.addAll(paintingMap.values());
        return paintings;
    }

    public static void main(String[] args) throws SQLException {
        PaintingDao paintingDao = new PaintingDao();
        for (Painting P : paintingDao.getListPaintingByArtist(3)) {
            System.out.println(P);
        }
    }


}