package com.example.web.controller;

import com.example.web.dao.model.Artist;
import com.example.web.service.ArtistService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name ="PainterDetail", value ="/painter-detail")
public class PainterDetail extends HttpServlet {
    private ArtistService artistService = new ArtistService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        try {
            Artist data = artistService.getArtistById(id);
            req.setAttribute("data", data);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        req.getRequestDispatcher("user/painter.jsp").forward(req, resp);
    }
}
