package com.example.web.controller;

import com.example.web.dao.model.User;
import com.example.web.service.AuthService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import jakarta.servlet.annotation.WebServlet;

@WebServlet(name = "LoginController", value = "/login")
public class LoginController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        AuthService service = new AuthService();
        try {
            User user = service.checkLogin(username, password);
            if (user != null && user.getRole() != null) { // Kiểm tra user có khác null và có role
                HttpSession session = request.getSession();
                session.setAttribute("user", user);
                User currentUser = (User) session.getAttribute("user");
                System.out.println(currentUser);
                if (user.getRole().equals(User.Role.admin)) { // Kiểm tra role của user
                    response.sendRedirect("/web_war/admin/dashboard.jsp"); // Chuyển đến trang admin nếu là admin
                } else {
                    response.sendRedirect("index.jsp");
                }
            } else {
                // Nếu user là null hoặc role không xác định, hiển thị thông báo lỗi
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
