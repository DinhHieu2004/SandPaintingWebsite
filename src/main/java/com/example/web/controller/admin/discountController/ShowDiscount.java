package com.example.web.controller.admin.discountController;

import com.example.web.dao.DiscountDao;
import com.example.web.dao.model.Discount;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/admin/discount")
public class ShowDiscount extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DiscountDao discountDao = new DiscountDao();

        List<Discount> list = null;

        try {
            list = discountDao.getAllDiscount();

            // Lấy id từ tham số URL
            String discountId = request.getParameter("id");

            if (discountId != null && !discountId.isEmpty()) {
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        request.setAttribute("list", list);

        request.getRequestDispatcher("/admin/discount.jsp").forward(request, response);
    }
}
