package com.example.web.controller.paintingController;

import com.example.web.dao.PaintingDao;
import com.example.web.dao.model.Painting;
import com.example.web.service.PaintingService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name ="Paiting-detail", value = "/painting-detail")
public class GetDetail extends HttpServlet {
    PaintingService paintingService = new PaintingService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pid = req.getParameter("pid");
        System.out.println("pid:"+ pid);
        int id = Integer.parseInt(pid);
        req.getSession().setAttribute("pid", id);
        try {
            Painting painting = paintingService.getPaintingDetail(id);
            req.setAttribute("painting", painting);
            req.setAttribute("p", painting);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        req.getRequestDispatcher("user/painting-detail.jsp").forward(req, resp);
    }

}
