package com.example.web.controller;

import com.example.web.dao.model.Painting;
import com.example.web.service.PaintingService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name ="Artwork", value = "/artwork")
public class PaintingController extends HttpServlet {
    PaintingService ps = new PaintingService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Painting>  data = ps.getAll(); ;
        req.setAttribute("data", data);
        System.out.println(data);
        req.getRequestDispatcher("user/artWork.jsp").forward(req  ,resp);

    }


}
