package com.example.web.controller.paintingController;


import com.example.web.dao.PaintingDao;
import com.example.web.dao.model.Painting;
import com.example.web.dao.model.Theme;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/index")
public class FeaturePainting extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Tạo đối tượng DAO để làm việc với cơ sở dữ liệu
        PaintingDao paintingDAO = new PaintingDao();

        // Lấy danh sách các tác phẩm trưng bày từ database
        List<Painting> featuredArtworks = paintingDAO.getFeaturedArtworks();
        List<Theme> themes = paintingDAO.getTheme();

        // Gắn danh sách tác phẩm vào request để truyền sang index
        request.setAttribute("featuredArtworks", featuredArtworks);
        request.setAttribute("themes", themes);

        // Chuyển hướng tới trang index để hiển thị danh sách
        request.getRequestDispatcher("index.jsp").forward(request, response);

    }

}

