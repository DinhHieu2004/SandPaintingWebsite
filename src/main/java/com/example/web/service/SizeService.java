package com.example.web.service;

import com.example.web.dao.SizeDao;
import com.example.web.dao.model.Painting;
import com.example.web.dao.model.PaintingSize;

import java.sql.SQLException;
import java.util.List;

public class SizeService {
    private SizeDao sizeDao = new SizeDao();

    public SizeService (){

    }
    public List<PaintingSize> getAllSize() throws SQLException {
        return sizeDao.getAllSizes();
    }
}
