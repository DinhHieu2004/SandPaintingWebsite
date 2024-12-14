package com.example.web.service;

import com.example.web.dao.PaintingDao;
import com.example.web.dao.model.Painting;

import java.sql.SQLException;
import java.util.List;

public class PaintingService {
    PaintingDao paintingDao = new PaintingDao();
    public List<Painting> getAll()  {
        return paintingDao.getPaintings();
    }

    public static void main(String[] args) {
        PaintingService paintingService = new PaintingService();
        System.out.println(paintingService.getAll());
    }

}
