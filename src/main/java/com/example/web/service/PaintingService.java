package com.example.web.service;

import com.example.web.dao.PaintingDao;
import com.example.web.dao.model.Painting;

import java.sql.SQLException;
import java.util.List;

public class PaintingService {
    PaintingDao paintingDao = new PaintingDao();
    public List<Painting> getAll() throws SQLException {
        return paintingDao.getPaintingList();
    }
    public Painting getPainting(int id) throws SQLException {
        return paintingDao.getPaintingDetail(id);
    }

    public static void main(String[] args) throws SQLException {
        PaintingService paintingService = new PaintingService();
        System.out.println(paintingService.getAll());
    }

}
