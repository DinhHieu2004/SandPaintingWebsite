package com.example.web.controller.paintingController;

import com.example.web.dao.PaintingDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.File;
import java.io.IOException;

public class Add {
    @WebServlet("/ainting")
    @MultipartConfig(
            fileSizeThreshold = 1024 * 1024 * 2, // 2MB
            maxFileSize = 1024 * 1024 * 10,      // 10MB
            maxRequestSize = 1024 * 1024 * 50   // 50MB
    )
    public class AddImageServlet extends HttpServlet {
        private static final long serialVersionUID = 1L;

        // Đường dẫn thư mục lưu ảnh
        private static final String UPLOAD_DIR = "uploads";

        protected void doPost(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException {
            // 1. Nhận dữ liệu từ request
            String title = request.getParameter("title");
            String description = request.getParameter("description");
            Part filePart = request.getPart("image"); // Lấy file ảnh từ form

            // Kiểm tra thông tin
            if (title == null || title.isEmpty() || filePart == null) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Title or image is missing");
                return;
            }

            // 2. Lưu file ảnh lên server
            String uploadPath = getServletContext().getRealPath("") + File.separator + UPLOAD_DIR;
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdir(); // Tạo thư mục nếu chưa tồn tại
            }

            String fileName = filePart.getSubmittedFileName();
            String filePath = uploadPath + File.separator + fileName;
            filePart.write(filePath); // Ghi file ảnh lên server

            // 3. Lưu thông tin vào cơ sở dữ liệu
            try {
                PaintingDao paintingDao = new PaintingDao(); // DAO xử lý cơ sở dữ liệu
                boolean isAdded = paintingDao.getPaintingAdd(title, description, UPLOAD_DIR + "/" + fileName);

                if (isAdded) {
                    // Phản hồi thành công
                    response.setStatus(HttpServletResponse.SC_OK);
                    response.getWriter().write("Image added successfully");
                } else {
                    // Lỗi khi thêm vào DB
                    response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Failed to add image to database");
                }
            } catch (Exception e) {
                e.printStackTrace();
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error while adding image");
            }
        }
    }
}