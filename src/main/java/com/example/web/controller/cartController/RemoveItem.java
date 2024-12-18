package com.example.web.controller.cartController;

import com.example.web.dao.cart.Cart;
import com.example.web.dao.cart.CartPainting;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(name="RemoveFromCart", value = "/remove-from-cart")
public class RemoveItem extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String productId = req.getParameter("productId");
        String sizeId = req.getParameter("sizeId");
        System.out.println( " truoc" );

        HttpSession session = req.getSession();
        Cart cart = (Cart) session.getAttribute("cart");
        for(CartPainting c : cart.getItems()){
            System.out.println(c);
        }

        if(cart != null) {
            cart.removeFromCart(productId, sizeId);
            session.setAttribute("cart", cart);
        }
        System.out.println( " sau" );

        for(CartPainting c : cart.getItems()){
            System.out.println(c);
        }

         resp.sendRedirect("show-cart");
    }
}
