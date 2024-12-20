package com.example.web.controller;

import com.example.web.service.AuthService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name="loginController", value = "/login")
public class LoginController extends HttpServlet {
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {}
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String username = request.getParameter("username");
    String password = request.getParameter("password");
    AuthService service = new AuthService();
    if(service.checkLogin(username,password)) {
        response.sendRedirect("index.jsp");
    }else{
        response.sendRedirect("index.jsp?error=Dang+Nhap+Khong+Thanh+Cong");
    }
}
}
