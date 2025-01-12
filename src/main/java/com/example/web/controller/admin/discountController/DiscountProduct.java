package com.example.web.controller.admin.discountController;

import com.example.web.dao.DiscountDao;
import com.example.web.dao.PaintingDao;
import com.example.web.dao.model.Discount;
import com.example.web.dao.model.Painting;
import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/admin/discountPainting")
public class DiscountProduct extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String discountIdParam = request.getParameter("discountId");

        if (discountIdParam != null) {
            try {
                int discountId = Integer.parseInt(discountIdParam);

                // Sử dụng discountId để lấy danh sách sản phẩm
                DiscountDao discountDao = new DiscountDao();
                List<Painting> discountedPaintings = discountDao.getPaintingsByDiscountId(discountId);
                List<Painting> nonDiscountedPaintings = discountDao.getProductHaveNoDC();
                Discount discount = discountDao.getDiscountById(discountId); // Lấy thông tin chi tiết của giảm giá

                // Chuyển đối tượng Discount thành JSON để trả về
                Gson gson = new Gson();
                Map<String, Object> result = new HashMap<>();
                result.put("discountedProducts", discountedPaintings);
                result.put("nonDiscountedProducts", nonDiscountedPaintings);
//                result.put("discountDetails", discount);

                String jsonResponse = gson.toJson(result);

                // Cài đặt kiểu nội dung là JSON
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");

                // Gửi dữ liệu JSON về client
                response.getWriter().write(jsonResponse);
            } catch (NumberFormatException e) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid discount ID");
            } catch (SQLException e) {
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Database error");
            }
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing discount ID");
        }
    }
}
