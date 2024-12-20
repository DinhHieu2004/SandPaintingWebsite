package com.example.web.dao;

import com.example.web.dao.db.DbConnect;
import com.example.web.dao.model.Theme;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ThemeDao {
    private Connection con = DbConnect.getConnection();

    public ThemeDao(){}

    public List<Theme> getAllTheme() throws SQLException {
        List<Theme> themes = new ArrayList<Theme>();
        String sql = "select * from themes";
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int id = rs.getInt("id");
            String name = rs.getString("themeName");
            Theme theme = new Theme(id, name);
            themes.add(theme);
        }
        return themes;
    }
}
