package com.example.web.dao;

import com.example.web.dao.db.DbConnect;
import com.example.web.dao.model.Discount;
import com.example.web.dao.model.Painting;
import com.example.web.service.DiscountService;

import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class DiscountDao {
    private Connection conn = DbConnect.getConnection();

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
    public List<Painting> getProductHaveNoDC() throws SQLException {
        // Câu lệnh SQL sử dụng LEFT JOIN để lấy các sản phẩm không được giảm giá
        String sql = """
        SELECT 
            p.id, 
            p.title, 
            p.price, 
            a.name AS artistName, 
            t.themeName AS themeName, 
            p.createdAt 
        FROM 
            paintings p
        LEFT JOIN 
            discount_paintings dp ON p.id = dp.paintingId
        JOIN 
            artists a ON p.artistId = a.id
        JOIN 
            themes t ON p.themeId = t.id
        WHERE 
            dp.paintingId IS NULL
    """;

        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        List<Painting> productDcByNullDcId = new ArrayList<>();

        while (rs.next()) {
            Painting painting = new Painting();

            // Lấy dữ liệu từ kết quả ResultSet
            int paintingId = rs.getInt("id");
            String title = rs.getString("title");
            double price = rs.getDouble("price");
            String theme = rs.getString("themeName");
            Date createdAt = rs.getDate("createdAt");
            String artistName = rs.getString("artistName");

            // Gán dữ liệu vào đối tượng Painting
            painting.setId(paintingId);
            painting.setTitle(title);
            painting.setPrice(price);
            painting.setThemeName(theme);
            painting.setArtistName(artistName);
            painting.setCrateDate(createdAt);

            // Thêm Painting vào danh sách
            productDcByNullDcId.add(painting);
        }

        return productDcByNullDcId;
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
    public boolean assignProductToDiscount(int productId, int discountId) throws SQLException {
        String sql = "INSERT INTO discount_paintings (discountId, paintingId) VALUES (?, ?)";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, discountId); // Gán giá trị discountId
            ps.setInt(2, productId);  // Gán giá trị paintingId

            int rowsAffected = ps.executeUpdate(); // Thực thi câu lệnh
            return rowsAffected > 0; // Trả về true nếu có ít nhất một dòng được thêm
        }
    }


    public Discount getDiscountById(int discountId) {
        Discount discount = null;
        String query = "SELECT * FROM discounts WHERE id = ?";

        try (PreparedStatement stmt = conn.prepareStatement(query)) {

            // Thiết lập tham số cho câu lệnh SQL
            stmt.setInt(1, discountId);

            // Thực thi câu lệnh và lấy kết quả
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    // Lấy thông tin từ ResultSet
                    int id = rs.getInt("id");
                    String imageUrl = rs.getString("imageUrl");
                    String discountName = rs.getString("discountName");
                    BigDecimal discountPercentage = rs.getBigDecimal("discountPercentage");
                    LocalDate localStartDate = rs.getDate("startDate").toLocalDate();
                    LocalDate localEndDate = rs.getDate("endDate").toLocalDate();
                    Timestamp createdAt = rs.getTimestamp("createdAt");

                    // Chuyển đổi java.sql.Date thành LocalDate
                    LocalDateTime localCreatedAt = (createdAt != null) ? createdAt.toLocalDateTime() : null;

                    // Tạo đối tượng Discount từ kết quả truy vấn
                    discount = new Discount(id, imageUrl, discountName, discountPercentage, localStartDate, localEndDate, localCreatedAt);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Xử lý lỗi
        }

        return discount;
    }
    public boolean removeProductFromDiscount(int productId) throws SQLException {
        String sql = "DELETE FROM discount_paintings WHERE paintingId = ?";

        try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setInt(1, productId);
            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Sản phẩm đã được xóa khỏi chương trình giảm giá.");
                return true; // Xóa thành công
            } else {
                System.out.println("Không tìm thấy sản phẩm trong chương trình giảm giá.");
                return false; // Không có sản phẩm nào bị xóa
            }
        }
    }

    public boolean addDiscount(Discount discount) {
        String sql = "INSERT INTO discounts (imageUrl, discountName, discountPercentage, startDate, endDate) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement statement = conn.prepareStatement(sql)) {

            // Đặt các giá trị cho các tham số trong câu lệnh SQL
            statement.setString(1, discount.getImageUrl());
            statement.setString(2, discount.getDiscountName());
            statement.setBigDecimal(3, discount.getDiscountPercentage());
            statement.setDate(4, java.sql.Date.valueOf(discount.getStartDate()));
            statement.setDate(5, java.sql.Date.valueOf(discount.getEndDate()));

            // Thực thi câu lệnh SQL
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;  // Nếu có ít nhất 1 dòng được thêm vào, trả về true
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean deleteDiscount(int discountId) {
        boolean isDeleted = false;
        String sql = "DELETE FROM discounts WHERE id = ?";
        try {
            deleteProductsByDiscountId(discountId);
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setInt(1, discountId);
                int rowsAffected = ps.executeUpdate();

                // Nếu có ít nhất một dòng bị xóa, trả về true
                if (rowsAffected > 0) {
                    isDeleted = true;
                }
            }
        } catch (SQLException e) {
            // Xử lý lỗi và ghi lại thông tin lỗi nếu cần
            e.printStackTrace();
        }

        return isDeleted;
    }

    // Phương thức xóa sản phẩm liên quan đến giảm giá
    private void deleteProductsByDiscountId(int discountId) throws SQLException {
        String sql = "DELETE FROM discount_paintings WHERE discountId = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, discountId);
            ps.executeUpdate();
        }
    }
    public void editDiscount(Discount discount) {
        String sql = "UPDATE discounts SET discountName = ?, discountPercentage = ?, startDate = ?, endDate = ?, imageUrl = ? WHERE id = ?";
        try(PreparedStatement stmt = conn.prepareStatement(sql)) {
            // Gán giá trị cho câu lệnh SQL
            stmt.setString(1, discount.getDiscountName());
            stmt.setBigDecimal(2, discount.getDiscountPercentage());
            stmt.setDate(3, java.sql.Date.valueOf(discount.getStartDate()));
            stmt.setDate(4, java.sql.Date.valueOf(discount.getEndDate()));
            stmt.setString(5, discount.getImageUrl());
            stmt.setInt(6, discount.getId());  // ID để xác định chương trình giảm giá cần chỉnh sửa

            // Thực thi câu lệnh cập nhật
            stmt.executeUpdate();

            // Kiểm tra xem câu lệnh có ảnh hưởng đến cơ sở dữ liệu không

        } catch (SQLException e) {
            e.printStackTrace();
            // Xử lý lỗi nếu có
            System.out.println("Lỗi khi chỉnh sửa giảm giá: " + e.getMessage());
        }
    }
    public static void main(String[] args) {
        DiscountDao dao = new DiscountDao();
        System.out.println( dao.deleteDiscount(1));
    }

}

