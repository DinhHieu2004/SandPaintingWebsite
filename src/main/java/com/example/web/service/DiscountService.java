package com.example.web.service;

import com.example.web.dao.DiscountDao;
import com.example.web.dao.model.Discount;
import com.example.web.dao.model.Painting;

import java.sql.SQLException;
import java.util.List;

public class DiscountService {

    private DiscountDao discountDAO;

    public DiscountService() {
        discountDAO = new DiscountDao();
    }

    // Lấy tất cả các giảm giá
    public List<Discount> getAllDiscount() throws SQLException {
        return discountDAO.getAllDiscount();  // Gọi DiscountDAO để lấy tất cả các giảm giá từ DB
    }

    // Xóa sản phẩm khỏi giảm giá (bằng cách sử dụng productId)
    public boolean removeProductFromDiscount(int productId) throws SQLException {
       return discountDAO.removeProductFromDiscount(productId);  // Gọi DiscountDAO để xóa sản phẩm khỏi giảm giá
    }

    // Lấy thông tin giảm giá theo ID
    public Discount getDiscountById(int discountId) {
        return discountDAO.getDiscountById(discountId);  // Gọi DiscountDAO để lấy giảm giá từ DB
    }

    // Lấy các sản phẩm không có giảm giá
    public List<Painting> getProductHaveNoDC() throws SQLException {
        return discountDAO.getProductHaveNoDC();  // Gọi PaintingDAO để lấy các sản phẩm không có giảm giá
    }

    // Lấy các bức tranh thuộc một giảm giá nào đó
    public List<Painting> getPaintingsByDiscountId(int discountId) throws SQLException {
        return discountDAO.getPaintingsByDiscountId(discountId);  // Gọi PaintingDAO để lấy các bức tranh thuộc giảm giá
    }

    // Gán sản phẩm vào giảm giá (dựa trên discountId và danh sách productIds)
    public void assignProductsToDiscount(int discountId, List<Integer> productIdsList) throws SQLException {
        discountDAO.assignProductsToDiscount(discountId, productIdsList);  // Gọi DiscountDAO để gán sản phẩm vào giảm giá
    }

    // Lấy tên của giảm giá theo ID
    public String getDiscountNameById(int discountId) throws SQLException {
        return discountDAO.getDiscountNameById(discountId);  // Gọi DiscountDAO để lấy tên giảm giá
    }

    // Thêm một giảm giá mới vào hệ thống
    public boolean addDiscount(Discount discount) {
        return discountDAO.addDiscount(discount);  // Gọi DiscountDAO để thêm giảm giá mới
    }
}

