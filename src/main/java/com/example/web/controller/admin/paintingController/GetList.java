package com.example.web.controller.admin.paintingController;

import com.example.web.dao.model.Painting;
import com.example.web.dao.model.PaintingSize;
import com.example.web.dao.model.Theme;
import com.example.web.service.PaintingService;
import com.example.web.service.SizeService;
import com.example.web.service.ThemeService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/admin/products")
public class GetList extends HttpServlet {
    private PaintingService paintingService = new PaintingService();
    private SizeService sizeService = new SizeService();
    private ThemeService themeService = new ThemeService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Painting> listP  ;
        List<PaintingSize> listS  ;
        List<Theme> listP2  ;
        try {

            listS = sizeService.getAllSize();
            listP2 = themeService.getAllTheme();
            listP = paintingService.getAll();
            req.setAttribute("products", listP);
            req.setAttribute("sizes", listS);
            req.setAttribute("themes", listP2);

            System.out.println(listP);
            RequestDispatcher dispatcher = req.getRequestDispatcher("products.jsp");
            dispatcher.forward(req, resp);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
}
