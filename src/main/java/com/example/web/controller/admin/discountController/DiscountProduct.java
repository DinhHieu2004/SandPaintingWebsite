package com.example.web.controller.admin.discountController;

import com.example.web.dao.DiscountDao;
import com.example.web.dao.model.Painting;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

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
                List<Painting> paintings = discountDao.getPaintingsByDiscountId(discountId);
                String discountName = discountDao.getDiscountNameById(discountId);

                request.setAttribute("paintings", paintings);
                request.setAttribute("discountName", discountName);
                request.getRequestDispatcher("/admin/discount_paintings.jsp").forward(request, response);
            } catch (NumberFormatException e) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid discount ID");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing discount ID");
        }
    }
}

