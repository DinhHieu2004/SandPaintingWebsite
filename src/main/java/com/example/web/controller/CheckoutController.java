package com.example.web.controller;

import com.example.web.dao.cart.Cart;
import com.example.web.dao.model.Order;
import com.example.web.dao.model.User;
import com.example.web.service.CheckoutService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(name="Checkout", value = "/checkout")
public class CheckoutController extends HttpServlet {
    private CheckoutService checkoutService = new CheckoutService();
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");
        User user = (User) session.getAttribute("user");
        int userId = user.getId();
        int paymentMethodId = Integer.parseInt(request.getParameter("paymentMethod"));

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        try {
            if (cart == null || cart.getItems().isEmpty()) {
                response.getWriter().write("{\"status\": \"error\", \"message\": \"Giỏ hàng trống.\"}");
                return;
            }

            checkoutService.processCheckout(cart, userId, paymentMethodId);

            session.removeAttribute("cart");

            response.getWriter().write("{\"status\": \"success\", \"message\": \"Thanh toán thành công!\"}");
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().write("{\"status\": \"error\", \"message\": \"Đã xảy ra lỗi.\"}");
        }
    }
}