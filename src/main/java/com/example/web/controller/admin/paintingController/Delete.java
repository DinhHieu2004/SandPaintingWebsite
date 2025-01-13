package com.example.web.controller.admin.paintingController;


import com.example.web.service.UserSerive;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/admin/products/delete")
public class Delete extends HttpServlet {
    private UserSerive userSerive = new UserSerive();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String id = request.getParameter("pid");
        try {
            boolean isDeleted = userSerive.deleteUser(Integer.parseInt(id));
            if (isDeleted) {
                request.setAttribute("message", "Xóa sản phẩm thành công thành công!");
            } else {
                request.setAttribute("message", "Xóa sản phẩm thất bại!");
            }
        } catch (Exception e) {
            request.setAttribute("message", "Lỗi: " + e.getMessage());
        }
        response.sendRedirect("../products");
    }

}
