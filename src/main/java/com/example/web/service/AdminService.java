package com.example.web.service;

import com.example.web.dao.AdminDao;

import java.sql.SQLException;
import java.util.Map;

public class AdminService {
    AdminDao adminDao = new AdminDao();
    public double getTotalRevenue() throws SQLException {
        return adminDao.getTotalRevenue();
    }

    public int getTotalOrders() throws SQLException {
        return adminDao.getTotalOrders();
    }

    public int getTotalProducts() throws SQLException {
        return adminDao.getTotalProducts();
    }

    public int getTotalUsers() throws SQLException {
        return adminDao.getTotalUsers();
    }

    public Map<String, Double> getRevenueByArtist() throws SQLException {
        return adminDao.getArtistRevenueMap();
    }

    public Map<String, Integer> getOrderStatusCount() throws SQLException {
        return adminDao.getOrderStatusCount();
    }
}
