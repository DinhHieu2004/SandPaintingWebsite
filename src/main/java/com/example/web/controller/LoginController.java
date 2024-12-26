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
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        AuthService service = new AuthService();
        try {
            if (service.checkLogin(username, password)) {
                // Lưu thông tin người dùng vào session
                HttpSession session = request.getSession();
                session.setAttribute("currentUser", username);
                response.sendRedirect("index.jsp"); // Chuyển đến trang chính
            } else {
                // Đăng nhập thất bại
                request.setAttribute("errorMessage", "Tên đăng nhập hoặc mật khẩu không đúng!");
                request.setAttribute("username", username); // Lưu lại tên đăng nhập
                request.getRequestDispatcher("index.jsp").forward(request, response);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Lỗi hệ thống, vui lòng thử lại sau!");
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }
}
