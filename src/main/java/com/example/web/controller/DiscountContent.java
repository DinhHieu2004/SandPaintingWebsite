package com.example.web.controller;

import com.example.web.dao.DiscountDao;
import com.example.web.dao.model.Discount;
import com.example.web.dao.model.Painting;
import com.example.web.service.DiscountService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/discount_content")
public class DiscountContent extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Lấy id từ tham số URL
        String discountIdStr = request.getParameter("id");

        if (discountIdStr != null) {
            try {
                int discountId = Integer.parseInt(discountIdStr);

                DiscountService service = new DiscountService();
                Discount discount = service.getDiscountById(discountId);
                List<Painting> list = service.getPaintingsByDiscountId(discountId);

                if (discount != null) {
                    request.setAttribute("discount", discount);
                    request.setAttribute("paintings", list);

                    RequestDispatcher dispatcher = request.getRequestDispatcher("/user/discount_content.jsp");
                    dispatcher.forward(request, response);
                } else {
                    response.sendRedirect("/user/discount");
                }
            } catch (NumberFormatException e) {
                response.sendRedirect("/user/discount");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

