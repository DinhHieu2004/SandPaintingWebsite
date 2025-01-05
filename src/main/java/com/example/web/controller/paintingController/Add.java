package com.example.web.controller.paintingController;

import com.example.web.dao.PaintingDao;
import com.example.web.dao.model.Painting;
import com.example.web.dao.db.DbConnect;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet("/addPainting")
public class AddPaintingServlet extends HttpServlet {

    private PaintingDao paintingDao;
    private Connection connection;

    @Override
    public void init() throws ServletException {
        try {
            connection = DbConnect.getConnection();
            paintingDao = new PaintingDao(connection);
        } catch (SQLException e) {
            throw new ServletException("Không thể kết nối đến CSDL", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String title = req.getParameter("title");
            int themeId = Integer.parseInt(req.getParameter("themeId"));
            double price = Double.parseDouble(req.getParameter("price"));
            int artistId = Integer.parseInt(req.getParameter("artistId"));
            String description = req.getParameter("description");
            String imageUrl = req.getParameter("imageUrl");

            Painting painting = paintingDao.getPaintingAdd();

            resp.sendRedirect("painting-list"); // Chuyển hướng về trang danh sách
        } catch (SQLException e) {
            e.printStackTrace();
            req.setAttribute("errorMessage", "Lỗi khi thêm tranh: " + e.getMessage());
            req.getRequestDispatcher("painting-form.jsp").forward(req, resp); // Hiển thị lại form với thông báo lỗi
        } catch (NumberFormatException e) {
            e.printStackTrace();
            req.setAttribute("errorMessage", "Lỗi định dạng dữ liệu: " + e.getMessage());
            req.getRequestDispatcher("painting-form.jsp").forward(req, resp); // Hiển thị lại form với thông báo lỗi
        }
    }

    @Override
    public void destroy() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}