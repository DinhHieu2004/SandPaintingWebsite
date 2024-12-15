package com.example.web.controller;

import com.example.web.dao.PaintingDao;
import com.example.web.dao.model.Painting;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name ="Paiting-detail", value = "/painting-detail")
public class PaintingDetail extends HttpServlet {
    PaintingDao paintingDao = new PaintingDao();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int id = Integer.parseInt(req.getParameter("pid"));
        try {
            Painting painting = paintingDao.getPaintingDetail(id);
            req.setAttribute("painting", painting);
            req.setAttribute("p", painting);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        req.getRequestDispatcher("user/painting-detail.jsp").forward(req, resp);
    }

}
