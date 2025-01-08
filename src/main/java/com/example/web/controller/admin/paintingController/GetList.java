package com.example.web.controller.admin.paintingController;

import com.example.web.dao.model.Painting;
import com.example.web.service.PaintingService;
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

@WebServlet("/admin/product")

public class GetList extends HttpServlet {
    private PaintingService paintingService = new PaintingService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Painting> listP = new ArrayList<>();
        try {
            listP = paintingService.getAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        req.setAttribute("products", listP);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/admin/admin.jsp");
        dispatcher.forward(req, resp);

    }
}
