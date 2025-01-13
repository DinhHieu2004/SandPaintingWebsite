package com.example.web.controller;

import com.example.web.dao.model.Artist;
import com.example.web.dao.model.PaintingSize;
import com.example.web.dao.model.Theme;
import com.example.web.service.ArtistService;
import com.example.web.service.SizeService;
import com.example.web.service.ThemeService;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;

import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "InitServlet", loadOnStartup = 1, value = "/init")

public class InitServlet extends HttpServlet {
    private SizeService sizeService = new SizeService();
    private ArtistService artistService = new ArtistService();
    private ThemeService themeService = new ThemeService();


    @Override
    public void init() throws ServletException {
        try {
            List<Artist> artists = artistService.getAllArtists();
            List<PaintingSize> sizes = sizeService.getAllSize();
            List<Theme> themes = themeService.getAllTheme();
            System.out.println(sizes);
            ServletContext context = getServletContext();
            context.setAttribute("artists", artists);
            context.setAttribute("sizes", sizes);
            context.setAttribute("themes", themes);


        } catch (SQLException e) {
            throw new ServletException("Failed to load artists", e);
        }
    }
}
