package com.example.web.service;

import com.example.web.dao.ThemeDao;
import com.example.web.dao.model.Theme;

import java.sql.SQLException;
import java.util.List;

public class ThemeService {
    ThemeDao themeDao= new ThemeDao();

    public ThemeService(){}

    public List<Theme> getAllTheme() throws SQLException {
        return themeDao.getAllTheme();
    }

}
