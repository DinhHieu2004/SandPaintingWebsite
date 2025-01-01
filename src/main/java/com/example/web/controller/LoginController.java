package com.example.web.controller;

import com.example.web.dao.model.User;
import com.example.web.service.AuthService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.sql.SQLException;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "LoginController", value = "/login")
public class LoginController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        AuthService service = new AuthService();
        try {
            User user = service.checkLogin(username, password); // Trả về User nếu đăng nhập thành công
            if (user != null) {
                // Lưu đối tượng User vào session (không có password)
                HttpSession session = request.getSession();
                session.setAttribute("currentUser", user);

                // Kiểm tra vai trò và chuyển hướng
                if (user.getRole() == User.Role.admin) {
                    response.sendRedirect("/web_war/admin/dashboard.html");
                } else {
                    response.sendRedirect("index.jsp");
                }
            } else {
                // Đăng nhập thất bại
                request.setAttribute("errorMessage", "Tên đăng nhập hoặc mật khẩu không đúng!");
                request.getRequestDispatcher("index.jsp").forward(request, response);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Lỗi hệ thống, vui lòng thử lại sau!");
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
    }

}
