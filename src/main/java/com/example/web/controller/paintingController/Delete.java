package com.example.web.controller.paintingController;
import com.example.web.service.PaintingService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/admin/painitng/delete")
public class Delete extends HttpServlet {
    private PaintingService paintingService = new PaintingService();
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String paintingId = request.getParameter("painitngId");
        try {
            boolean isDeleted = paintingService.deletePainting(Integer.parseInt(paintingId));
            if (isDeleted) {
                request.setAttribute("message", "Xóa tranh thành công!");
            } else {
                request.setAttribute("message", "Xóa tranh thất bại!");
            }
        } catch (Exception e) {
            request.setAttribute("message", "Lỗi: " + e.getMessage());
        }
        response.sendRedirect("../paintings");

    }


}
