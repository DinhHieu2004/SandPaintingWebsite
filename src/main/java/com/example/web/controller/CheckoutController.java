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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("user");
            if(user == null){
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().println("Bạn cần đăng nhập để tiếp tục thanh toán!");
                return;
            }
            int userId = user.getId();
            Cart cart = (Cart) request.getSession().getAttribute("cart");
            if (cart == null || cart.getItems().isEmpty()) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getWriter().write("Giỏ hàng của bạn đang trống!");
                return;
            }
            int paymentMethodId = Integer.parseInt(request.getParameter("paymentMethod"));

            CheckoutService checkoutService = new CheckoutService();
            try {
                checkoutService.processCheckout(cart, userId, paymentMethodId);
                session.removeAttribute("cart");
                response.setStatus(HttpServletResponse.SC_OK);
                response.getWriter().write("Thanh toán thành công!");
            } catch (Exception e) {
                e.printStackTrace();
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                response.getWriter().write("Có lỗi xảy ra trong quá trình thanh toán. Vui lòng thử lại!");
            }
        } catch (Exception e) {
            response.setContentType("application/json");
            response.getWriter().write("{\"success\": false, \"message\": \"Có lỗi xảy ra: " + e.getMessage() + "\"}");
        }
    }
}