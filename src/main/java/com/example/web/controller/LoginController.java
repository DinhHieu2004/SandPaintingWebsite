package com.example.web.controller;

import com.example.web.service.AuthService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "loginController", value = "/login")
public class LoginController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Lấy thông tin từ form
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Gọi service kiểm tra đăng nhập
        AuthService service = new AuthService();
        try {
            if (service.checkLogin(username, password)) {
                // Nếu đăng nhập thành công, chuyển hướng đến trang chính
                response.sendRedirect("index.jsp");
            } else {
                // Nếu đăng nhập thất bại, trả về form đăng nhập cùng thông báo lỗi
                request.setAttribute("errorMessage", "Tên đăng nhập hoặc mật khẩu không đúng!");
                request.setAttribute("username", username); // Giữ lại tên đăng nhập
                request.getRequestDispatcher("index.jsp").forward(request, response);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Chuyển hướng về trang login (index.jsp)
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }
}
