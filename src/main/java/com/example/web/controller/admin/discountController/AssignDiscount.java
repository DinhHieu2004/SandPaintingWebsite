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
import java.util.ArrayList;
import java.util.List;

@WebServlet("/admin/assignDiscount")
public class AssignDiscount extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Lấy các tham số từ form
        String[] productIds = request.getParameterValues("productIds");
        String discountIdParam = request.getParameter("discountId");

        if (discountIdParam == null || discountIdParam.isEmpty()) {
            request.setAttribute("errorMessage", "Chương trình giảm giá không hợp lệ.");
            // Truyền discountId và các tham số khác vào request
            request.setAttribute("discountId", discountIdParam);
            response.sendRedirect(request.getContextPath() + "/admin/addProductDiscount?discountId=" + discountIdParam);
            return;
        }

        if (productIds == null || productIds.length == 0) {
            request.setAttribute("errorMessage", "Bạn chưa chọn sản phẩm nào.");
            // Truyền discountId và các tham số khác vào request
            request.setAttribute("discountId", discountIdParam);
            response.sendRedirect(request.getContextPath() + "/admin/addProductDiscount?discountId=" + discountIdParam);
            return;
        }

        try {
            // Chuyển đổi discountId và xử lý logic bình thường
            int discountId = Integer.parseInt(discountIdParam);
            List<Integer> productIdsList = new ArrayList<>();
            for (String productId : productIds) {
                productIdsList.add(Integer.parseInt(productId));
            }

            DiscountService service = new DiscountService();
            service.assignProductsToDiscount(discountId, productIdsList);

            // Chuyển hướng sau khi thành công
            response.sendRedirect(request.getContextPath() + "/admin/discountPainting?discountId=" + discountId);
        } catch (NumberFormatException | SQLException e) {
            request.setAttribute("errorMessage", "Dữ liệu không hợp lệ.");
            request.setAttribute("discountId", discountIdParam);
            request.getRequestDispatcher("/admin/addProductDiscount.jsp").forward(request, response);
        }
    }
}


