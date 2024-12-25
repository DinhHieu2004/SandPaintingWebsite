package com.example.web.controller.cartController;
import com.example.web.dao.cart.Cart;
import com.example.web.dao.cart.CartPainting;
import com.example.web.dao.model.Painting;
import com.example.web.dao.model.PaintingSize;
import com.example.web.service.PaintingService;
import com.example.web.service.SizeService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "Add-to-cart", value = "/add-to-cart")
public class AddItemCartController extends HttpServlet {
    PaintingService paintingService = new PaintingService();
    SizeService sizeService = new SizeService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            int id = (Integer) req.getSession().getAttribute("pid");
            Painting p = null;
            p = paintingService.getPainting(id);
            String size = req.getParameter("size");
            int quantity = Integer.parseInt(req.getParameter("quantity"));
            System.out.println("add id: "+id);
            System.out.println(quantity);
            System.out.println(size);

            PaintingSize paintingSize =  sizeService.getSizeById(Integer.parseInt(size));
            String sizeDescriptions = paintingSize.getSizeDescriptions();

            CartPainting cartPainting = new CartPainting(p.getId(), p.getTitle(), size,sizeDescriptions, quantity, p.getPrice(), p.getImageUrl());

            HttpSession session = req.getSession();
            Cart cart = (Cart) session.getAttribute("cart");
            if (cart == null) {
                cart = new Cart();
            }
            cart.addToCart(cartPainting);
            session.setAttribute("cart", cart);
            req.setAttribute("message", "Thêm vào giỏ hàng thành công!");
            System.out.println(cart.getItemsMap().keySet().toString());
            for(CartPainting c : cart.getItems()){
                System.out.println(c);
            }

            resp.sendRedirect("painting-detail?pid=" + id);
           // RequestDispatcher dispatcher = req.getRequestDispatcher("painting-detail?pid=" + id);
           // dispatcher.forward(req, resp);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
