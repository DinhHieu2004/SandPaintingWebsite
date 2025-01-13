package com.example.web.controller.admin.discountController;

import com.example.web.dao.DiscountDao;
import com.example.web.dao.PaintingDao;
import com.example.web.dao.model.Discount;
import com.example.web.dao.model.Painting;
import com.example.web.service.DiscountService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/admin/addProductDiscount")
public class AddProductDiscount extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String discountIdParam = request.getParameter("discountId");
        PaintingDao ptDao = new PaintingDao();
        DiscountService service = new DiscountService();

        try {
            if (discountIdParam == null || discountIdParam.isEmpty()) {
                // Nếu không có discountId, trả về lỗi hoặc chuyển hướng về trang khác
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Discount ID is required.");
                return;
            }

            int discountId = Integer.parseInt(discountIdParam); // Chuyển đổi discountId thành int

            List<Painting> paintingList = service.getProductHaveNoDC(); // Lấy danh sách sản phẩm chưa có giảm giá
            List<Discount> discountList = service.getAllDiscount(); // Lấy danh sách tất cả chương trình giảm giá
            String discountName = service.getDiscountNameById(discountId); // Lấy tên chương trình giảm giá

            // Truyền các thông tin vào request để hiển thị trên JSP
            request.setAttribute("discountId", discountId);
            request.setAttribute("paintingList", paintingList);
            request.setAttribute("discountName", discountName);
            request.setAttribute("discountList", discountList); // Truyền danh sách chương trình giảm giá

            request.getRequestDispatcher("/admin/addProductDiscount.jsp").forward(request, response);
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid Discount ID format.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
