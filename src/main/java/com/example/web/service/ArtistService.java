package com.example.web.service;

import com.example.web.dao.ArtistDao;
import com.example.web.dao.model.Artist;

import java.sql.SQLException;
import java.util.List;

public class ArtistService {
    private ArtistDao artistDao = new ArtistDao();

    public ArtistService() {}

    public List<Artist> getAllArtists() throws SQLException {
        return artistDao.getAllArtists();
    }

    public static void main(String[] args) throws SQLException {
        ArtistService artistService = new ArtistService();
        for (Artist a : artistService.getAllArtists()){
            System.out.println(a);
        }

    }
}
