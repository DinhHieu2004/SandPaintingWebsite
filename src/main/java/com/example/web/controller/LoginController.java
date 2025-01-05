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
            User user= service.checkLogin(username, password);
            if (user.getRole() != null) {
                HttpSession session = request.getSession();
                session.setAttribute("user", user);
                User currentUser = (User) session.getAttribute("user");
                System.out.println(currentUser);
                if (user.getRole().equals("admin")) {
                    response.sendRedirect("/web_war/admin/dashboard.jsp"); // Chuyển đến trang admin nếu là admin

                } else {
                    response.sendRedirect("index.jsp");
                }
            } else {

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
