package com.example.web.dao;

import com.example.web.dao.db.DbConnect;
import com.example.web.dao.model.Artist;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ArtistDao {
    private Connection con = DbConnect.getConnection();

    public ArtistDao() {

    }
    public List<Artist> getAllArtists() throws SQLException {
        List<Artist> artists = new ArrayList<Artist>();
        String sql = "select * from artists";
        PreparedStatement pstmt = con.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
            int id = rs.getInt("id");
            String name = rs.getString("name");
            String bio = rs.getString("bio");
            Date date = rs.getDate("birthDate");
            String nationality = rs.getString("nationality");
            String photoUrl = rs.getString("photoUrl");
            artists.add(new Artist(id, name, bio, date, nationality, photoUrl));
        }
        return artists;
    }
    public Artist getArtistById(int id) throws SQLException {
        String sql = "select * from artists where id = ?";
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setInt(1, id);
        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
            String name = rs.getString("name");
            String bio = rs.getString("bio");
            Date date = rs.getDate("birthDate");
            String nationality = rs.getString("nationality");
            String photoUrl = rs.getString("photoUrl");
            return new Artist(id, name, bio, date, nationality, photoUrl);
        }
        return null;
    }

    public static void main(String[] args) throws SQLException {
        ArtistDao dao = new ArtistDao();
        System.out.println(dao.getArtistById(1));
    }

}
