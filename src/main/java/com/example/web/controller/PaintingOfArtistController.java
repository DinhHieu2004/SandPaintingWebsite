package com.example.web.controller;
import com.example.web.dao.model.Painting;
import com.example.web.dao.model.PaintingSize;
import com.example.web.dao.model.Theme;
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

@WebServlet(name="PaintingListOfArtist", value="/painting-list-of-painter")
public class PaintingOfArtistController extends HttpServlet{
    private PaintingService ps = new PaintingService();
    private ThemeService ts = new ThemeService();
    private SizeService ss = new SizeService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Painting> data = null;
        List<Theme> themes = null;
        List<PaintingSize> paintingSizes = null;
        try {
            String id = req.getParameter("id");

            String minPriceParam = req.getParameter("minPrice");
            String maxPriceParam = req.getParameter("maxPrice");

            String[] sizes = req.getParameterValues("size");
            String[] themeArr = req.getParameterValues("theme");

            Double minPrice = null;
            Double maxPrice = null;

            try {
                if (minPriceParam != null && !minPriceParam.isEmpty()) {
                    minPrice = Double.valueOf(minPriceParam);
                }
                if (maxPriceParam != null && !maxPriceParam.isEmpty()) {
                    maxPrice = Double.valueOf(maxPriceParam);
                }
            } catch (NumberFormatException e) {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid price format");
                return;
            }
            data  = ps.getPaintingListByArtist(minPrice, maxPrice, sizes, themeArr, id);

            themes = ts.getAllTheme();
            paintingSizes = ss.getAllSize();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        req.setAttribute("data", data);
        req.setAttribute("themes", themes);
        req.setAttribute("paintingSizes", paintingSizes);
        req.getRequestDispatcher("user/ArtworkOfArtist.jsp").forward(req  ,resp);

    }

}
