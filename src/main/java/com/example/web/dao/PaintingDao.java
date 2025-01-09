package com.example.web.dao;

import com.example.web.dao.db.DbConnect;
import com.example.web.dao.model.Painting;
import com.example.web.dao.model.Theme;
import com.example.web.service.PaintingService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import static com.example.web.dao.db.DbConnect.getConnection;

public class PaintingDao {
    Connection con = getConnection();

    public PaintingDao() {
    }
    public static boolean getPaintingDelete(String id) {
        String sql = "DELETE FROM Painting WHERE id = ?";
        DbConnect DBConnect = null;
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public List<Painting> getAll() throws SQLException {
        String sql = """ 
                SELECT p.id,
                p.title,
                p.description,
                p.imageUrl,
                a.name AS artistName,
                t.themeName AS themeName,
                p.price,
                p.createdAt
        FROM paintings p
        JOIN artists a ON p.artistId = a.id
        JOIN themes t ON p.themeId = t.id """;

        PreparedStatement stmt = con.prepareStatement(sql);
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
            painting.setId(paintingId);
            painting.setTitle(title);
            painting.setPrice(price);
            painting.setImageUrl(imageUrl);
            painting.setThemeName(theme);
            painting.setThemeName(theme);
            painting.setArtistName(artistName);
            painting.setCrateDate(createdAt);
            painting.setDescription(rs.getString("description"));
            paintingList.add(painting);
        }
        return paintingList;
    }

        public boolean getPaintingAdd(String title, String description, String imageUrl) {
            String sql = "INSERT INTO Painting (id,title, themeId, price,artistId,description,imageUrl) VALUES (?, ?, ?)";
            DbConnect DBConnect = null;
            try (Connection conn = DBConnect.getConnection();
                 PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, title);
                ps.setString(2, description);
                ps.setString(3, imageUrl);
                return ps.executeUpdate() > 0;
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
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


    // danh sach tranh theo từng họa sĩ .
    public List<Painting> getPaintingListByArtist(Double minPrice, Double maxPrice, String[] sizes, String[] themes, String artistId) throws SQLException {
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
                    WHERE 1=1 AND a.id = ?
                """);

        List<Object> params = new ArrayList<>();
        params.add(artistId);

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
    public void updateQuanity(int paintingId, int sizeId, int quantity) throws SQLException {
        con.setAutoCommit(false);
        try {
            String sql = "UPDATE painting_sizes SET quantity = quantity - ? WHERE paintingId = ? AND sizeId = ?";
            try (PreparedStatement stmt = con.prepareStatement(sql)) {
                stmt.setInt(1, quantity);
                stmt.setInt(2, paintingId);
                stmt.setInt(3, sizeId);
                stmt.executeUpdate();
            }
            con.commit();
        } catch (Exception e) {
            con.rollback();
            e.printStackTrace();
            throw new SQLException("Error updating quantity with transaction", e);
        } finally {
            con.setAutoCommit(true);
        }
    }
    public List<Painting> getFeaturedArtworks() {
        String sql = "SELECT p.id, p.title, p.imageUrl, ar.name AS artist_name, p.price " +
                "FROM paintings p " +
                "JOIN artists ar ON p.artistId = ar.id " +
                "WHERE p.isFeatured = true AND p.isSold = false";
        List<Painting> featuredArtworks = new ArrayList<>();

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                featuredArtworks.add(new Painting(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getDouble(5)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Trả về danh sách các tác phẩm trưng bày chưa bán
        return featuredArtworks;
    }
    public List<Theme> getTheme() {
        String sql = "SELECT * FROM THEMES";
        List<Theme> theme = new ArrayList<>();

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                theme.add(new Theme(rs.getInt(1), rs.getString(2)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return theme;
    }
    public static void main(String[] args) throws SQLException {
        PaintingDao paintingDao = new PaintingDao();
        Double m1 = null;
        Double m2 = null;

        String[] sizes = null;
        String[] themes = {"1"};

      //  for (Painting P : paintingDao.getPaintingListByArtist(m1,m2,sizes, themes, "1")) {
       //     System.out.println(P);
       // }
    }


}