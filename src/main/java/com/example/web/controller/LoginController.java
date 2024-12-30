package com.example.web.controller;

import com.example.web.service.AuthService;
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
        // Lấy thông tin từ form đăng nhập
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        AuthService service = new AuthService();
        try {
            // Kiểm tra thông tin đăng nhập
            if (service.checkLogin(username, password)) {
                // Nếu đăng nhập thành công, lưu thông tin vào session
                HttpSession session = request.getSession(); // Tạo session mới hoặc lấy session hiện tại
                session.setAttribute("currentUser", username);
                response.sendRedirect("index.jsp"); // Chuyển hướng đến trang chính
            } else {
                // Nếu đăng nhập thất bại, gửi lại thông báo lỗi
                request.setAttribute("errorMessage", "Tên đăng nhập hoặc mật khẩu không đúng!");
                request.setAttribute("username", username); // Lưu lại tên đăng nhập để hiển thị lại
                request.getRequestDispatcher("index.jsp").forward(request, response);
            }
        } catch (SQLException e) {
            // Xử lý lỗi hệ thống
            e.printStackTrace();
            request.setAttribute("errorMessage", "Lỗi hệ thống, vui lòng thử lại sau!");
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
    }
}
