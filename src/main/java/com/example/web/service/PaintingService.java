package com.example.web.service;

import com.example.web.dao.PaintingDao;
import com.example.web.dao.model.Order;
import com.example.web.dao.model.OrderItem;
import com.example.web.dao.model.Painting;

import java.sql.SQLException;
import java.util.List;

public class PaintingService {
    private PaintingDao paintingDao = new PaintingDao();

    public List<Painting> getPaintingList(Double minPrice, Double maxPrice, String[] themes, String[] artists, int currentPage, int recordsPerPage) throws SQLException {
        return paintingDao.getPaintingList(minPrice, maxPrice, themes, artists, currentPage, recordsPerPage);
    }
    public int countPaintings(Double minPrice, Double maxPrice, String[] themes, String[] artists) throws SQLException {
        return paintingDao.countPaintings(minPrice, maxPrice, themes, artists);
    }

    public Painting getPainting(int id) throws SQLException {
        return paintingDao.getPaintingDetail(id);
    }

    public List<Painting> getListPaintingByArtist(int id) throws SQLException {
        return paintingDao.getListPaintingByArtist(id);
    }

    public List<Painting> getPaintingListByArtist(Double minPrice, Double maxPrice, String[] sizes, String[] themes, String artist) throws SQLException {
        return paintingDao.getPaintingListByArtist(minPrice, maxPrice, sizes, themes, artist);
    }
    public List<Painting> getAll() throws SQLException {
        return paintingDao.getAll();
    }




    public static void main(String[] args) throws SQLException {
        PaintingService paintingService = new PaintingService();
        System.out.println(paintingService.getAll());
    }


    public Painting getPaintingDetail(int id) throws SQLException {
        return paintingDao.getPaintingDetail(id);
    }

    public boolean deletePainting(int i) {
        return false;
    }
}
