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

    public boolean updatePainting(int id, String title, int themeId, double price, int artistId, String description, String imageUrl, boolean isFeatured) throws SQLException {
        return paintingDao.updatePainting( id, title, themeId, price, artistId, description, imageUrl, isFeatured);

    }

    public boolean updatePaintingSizes(int paintingId, List<Integer> sizeIds, List<Integer> quantities) throws SQLException {
        return paintingDao.updatePaintingSizes( paintingId, sizeIds, quantities);


    }
    public int addPainting(String title, int themeId, double price, int artistId, String description, String imageUrl, boolean isFeatured) throws SQLException {
        return paintingDao.addPainting(title, themeId, price, artistId, description, imageUrl, isFeatured);

    }

    public boolean addPaintingSizes(int paintingId, List<Integer> sizeIds, List<Integer> quantities) throws SQLException {
        return paintingDao.addPaintingSizes(paintingId, sizeIds, quantities);


    }

    public Painting getPaintingDetail(int id) throws SQLException {
        return paintingDao.getPaintingDetail(id);
    }


    public static void main(String[] args) throws SQLException {
        PaintingService paintingService = new PaintingService();
        System.out.println(paintingService.getAll());
    }


}
