package com.example.web.controller;

import com.example.web.dao.ThemeDao;
import com.example.web.dao.model.Artist;
import com.example.web.dao.model.Painting;
import com.example.web.dao.model.PaintingSize;
import com.example.web.dao.model.Theme;
import com.example.web.service.ArtistService;
import com.example.web.service.PaintingService;
import com.example.web.service.SizeService;
import com.example.web.service.ThemeService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name ="Artwork", value = "/artwork")
public class PaintingController extends HttpServlet {
    PaintingService ps = new PaintingService();
    ArtistService as = new ArtistService();
    ThemeService ts = new ThemeService();
    SizeService ss = new SizeService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Painting>  data = null;
        List<Artist>  artists = null;
        List<Theme> themes = null;
        List<PaintingSize> paintingSizes = null;
        try {
            data = ps.getAll();
            artists = as.getAllArtists();
            themes = ts.getAllTheme();
            paintingSizes = ss.getAllSize();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        ;
        req.setAttribute("data", data);
        req.setAttribute("artists", artists);
        req.setAttribute("themes", themes);
        req.setAttribute("paintingSizes", paintingSizes);
      //  System.out.println(data);
        req.getRequestDispatcher("user/artWork.jsp").forward(req  ,resp);

    }

    public static void main(String[] args) {

    }


}
