package com.example.web.controller.admin.artistController;

import com.example.web.dao.ArtistDao;
import com.example.web.service.ArtistService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/admin/artists/add")

public class Add extends HttpServlet {
    private ArtistService artistService = new ArtistService();
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            String name = request.getParameter("name");
            String bio = request.getParameter("bio");
            String birthDate = request.getParameter("birthDate");
            String nationality = request.getParameter("nationality");
            String photoUrl = request.getParameter("photoUrl");
            System.out.println("name: " + name);
            System.out.println("bio: " + bio);
            System.out.println("birthDate: " + birthDate);
            System.out.println("nationality: " + nationality);
            System.out.println("photoUrl: " + photoUrl);

            try {
                boolean isAdded = artistService.addArtist(name, bio, birthDate, nationality, photoUrl);

                if (isAdded) {
                    request.setAttribute("message", "Thêm họa sĩ thành công!");

                } else {
                    request.setAttribute("message", "Thêm họa sĩ thất bại!");
                }
            } catch (Exception e) {
                request.setAttribute("message", "Lỗi: " + e.getMessage());
            }
        request.getRequestDispatcher("../artists.jsp").forward(request, response);
       // response.sendRedirect("/web_war/admin/artists");


    }


}
