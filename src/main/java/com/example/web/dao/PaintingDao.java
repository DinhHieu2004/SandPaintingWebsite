package com.example.web.dao;

import com.example.web.dao.db.DbConnect;
import com.example.web.dao.model.Painting;
import com.example.web.service.PaintingService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PaintingDao {
    Connection con = DbConnect.getConnection();

    public PaintingDao() {

    }

    public List<Painting> getPaintings() {
        try {
            String sql = "select * from paintings";
            List<Painting> paintings = new ArrayList<Painting>();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Painting painting = new Painting();
                painting.setId(rs.getInt("id"));
                painting.setTitle(rs.getString("title"));
                painting.setDescription(rs.getString("description"));
                painting.setArtistId(rs.getInt("ArtistId"));
                painting.setCreatedAt(rs.getDate("createdAt"));
                painting.setImageUrl(rs.getString("imageUrl"));
                painting.setFeatured(rs.getBoolean("isFeatured"));
                painting.setDiscountId(rs.getInt("DiscountId"));
                painting.setSold(rs.getBoolean("isSold"));
                paintings.add(painting);

            }
            return paintings;
        } catch (SQLException e) {
            e.printStackTrace();

            return new ArrayList<>();
        }


    }

    public static void main(String[] args) {
        PaintingService paintingService = new PaintingService();
        PaintingDao paintingDao = new PaintingDao();
        System.out.print(paintingDao.getPaintings());

    }


}