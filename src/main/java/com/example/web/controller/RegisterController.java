package com.example.web.controller;

import com.example.web.dao.UserDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name="RegisterController", value = "/register")
public class RegisterController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Lấy thông tin từ form đăng ký
        String fullName = request.getParameter("fullName");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");
        String address = request.getParameter("address");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        UserDao userDao = new UserDao();
        try {
            boolean isRegistered = userDao.registerUser(fullName, username, password, address, email, phone, "user");
            if (isRegistered) {
                // Nếu đăng ký thành công, trả về trang đăng ký và hiển thị thông báo thành công
                String successMessage = "Đăng ký thành công! Bạn có thể đăng nhập ngay.";
                response.setContentType("text/html");
                response.getWriter().write("<script type='text/javascript'>"
                        + "alert('" + successMessage + "');"
                        + "window.location.href = 'index.jsp';"
                        + "</script>");
            } else {
                // Nếu đăng ký thất bại, trả về trang đăng ký với thông báo lỗi
                request.setAttribute("errorMessage", "Đăng ký không thành công. Vui lòng thử lại.");
                request.setAttribute("fullName", fullName);
                request.setAttribute("username", username);
                request.setAttribute("address", address);
                request.setAttribute("email", email);
                request.setAttribute("phone", phone);
                request.getRequestDispatcher("index.jsp").forward(request, response);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi khi thực hiện đăng ký", e);
        }
    }

}
