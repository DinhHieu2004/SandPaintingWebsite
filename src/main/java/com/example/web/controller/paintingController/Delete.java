package com.example.web.controller.paintingController;
import com.example.web.dao.PaintingDao;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;

import java.io.File;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name ="Painting", value = "/painting")
public class Delete extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 1. Lấy ID của ảnh từ request
        String PaintingId = request.getParameter("id");
        if (PaintingId == null || PaintingId.isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Image ID is missing");
            return;
        }

        try {
            // 2. Xóa ảnh từ cơ sở dữ liệu
            PaintingDao paintingDao = new PaintingDao(); // Bạn cần tạo DAO để thao tác với DB
            String imagePath = String.valueOf(PaintingDao.getPaintingDelete(PaintingId)); // Lấy đường dẫn ảnh từ DB
            boolean isDeleted = PaintingDao.getPaintingDelete(PaintingId); // Xóa ảnh khỏi DB

            if (isDeleted) {
                // 3. Xóa file ảnh từ thư mục lưu trữ (nếu có)
                if (imagePath != null && !imagePath.isEmpty()) {
                    File file = new File(getServletContext().getRealPath("/") + imagePath);
                    if (file.exists()) {
                        if (file.delete()) {
                            System.out.println("File deleted successfully: " + imagePath);
                        } else {
                            System.out.println("Failed to delete file: " + imagePath);
                        }
                    }
                }

                // 4. Gửi phản hồi thành công
                response.setStatus(HttpServletResponse.SC_OK);
                response.getWriter().write("Image deleted successfully.");
            } else {
                // Xử lý nếu không thể xóa
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Failed to delete image");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error while deleting image");
        }
    }
}

