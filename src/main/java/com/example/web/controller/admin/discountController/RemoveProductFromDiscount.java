package com.example.web.controller.admin.discountController;

import com.example.web.dao.DiscountDao;
import com.example.web.service.DiscountService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/admin/removePaintingFromDiscount")
public class RemoveProductFromDiscount extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Lấy productId và discountId từ request
        String productIdParam = request.getParameter("productId");
        String discountIdParam = request.getParameter("discountId");

        // Kiểm tra nếu productId và discountId không hợp lệ
        if (productIdParam == null || productIdParam.isEmpty() || discountIdParam == null || discountIdParam.isEmpty()) {
            request.setAttribute("errorMessage", "Mã sản phẩm hoặc chương trình giảm giá không hợp lệ.");
            request.getRequestDispatcher("/admin/discountPainting").forward(request, response);
            return;
        }

        try {
            // Chuyển productId và discountId từ String sang int
            int productId = Integer.parseInt(productIdParam);
            int discountId = Integer.parseInt(discountIdParam);

            // Gọi phương thức removeProductFromDiscount trong DiscountDao
            DiscountService service = new DiscountService();
            service.removeProductFromDiscount(productId);

            // Chuyển hướng đến trang danh sách sản phẩm trong chương trình giảm giá với discountId
            response.sendRedirect(request.getContextPath() + "/admin/discountPainting?discountId=" + discountId);

        } catch (NumberFormatException | SQLException e) {
            // Nếu không thể chuyển đổi productId hoặc discountId thành số
            request.setAttribute("errorMessage", "Mã sản phẩm hoặc chương trình giảm giá không hợp lệ.");
            request.getRequestDispatcher("/admin/discountPainting").forward(request, response);
        }
    }
}


