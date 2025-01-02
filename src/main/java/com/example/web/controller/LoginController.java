package com.example.web.controller;

import com.example.web.dao.model.User;
import com.example.web.service.AuthService;
import com.example.web.service.SizeService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "LoginController", value = "/login")
public class LoginController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Kiểm tra nếu người dùng đã đăng nhập
        HttpSession session = request.getSession(false); // Lấy session hiện tại, không tạo mới
        if (session != null && session.getAttribute("currentUser") != null) {
            // Nếu đã đăng nhập, chuyển hướng đến trang chính
            response.sendRedirect("index.jsp");
            return;
        }
        // Nếu chưa đăng nhập, hiển thị form trong index.jsp
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }

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
                    response.sendRedirect("/web_war/admin/dashboard.html"); // Chuyển đến trang admin nếu là admin
                } else {
                    response.sendRedirect("index.jsp"); // Chuyển đến trang chính nếu là người dùng bình thường
                }
            } else {

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
}
