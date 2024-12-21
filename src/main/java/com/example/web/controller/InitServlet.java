package com.example.web.controller;

import com.example.web.dao.model.Artist;
import com.example.web.service.ArtistService;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;

import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "InitServlet", loadOnStartup = 1, value = "/init")
public class InitServlet extends HttpServlet {
    private ArtistService artistService = new ArtistService();

    @Override
    public void init() throws ServletException {
        try {
            List<Artist> artists = artistService.getAllArtists();
            ServletContext context = getServletContext();
            context.setAttribute("artists", artists);
        } catch (SQLException e) {
            throw new ServletException("Failed to load artists", e);
        }
    }
}
