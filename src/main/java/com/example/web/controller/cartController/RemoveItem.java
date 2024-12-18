package com.example.web.controller.cartController;

import com.example.web.dao.cart.Cart;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

public class RemoveItem extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int productId = Integer.parseInt(req.getParameter("id"));

        HttpSession session = req.getSession();
        Cart cart = (Cart) session.getAttribute("cart");
        if(cart == null) {
            boolean isRemoved = cart.removeFromCart(productId);
        }
    }
}
