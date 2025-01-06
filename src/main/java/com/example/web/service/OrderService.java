package com.example.web.service;

import com.example.web.dao.OrderDao;
import com.example.web.dao.model.Order;

import java.util.List;

public class OrderService {
    private OrderDao orderDao = new OrderDao();

    public List<Order> getCurrentOrdersForUser(int userId) throws Exception {
        return orderDao.getCurrentOrdersForUser(userId);
    }

    public List<Order> getHistoryOrder(int userId) throws Exception {
        return orderDao.getHistoryOrder(userId);
    }
    public Order getOrder(int orderId) throws Exception {
        return orderDao.getOrder(orderId);
    }
}
