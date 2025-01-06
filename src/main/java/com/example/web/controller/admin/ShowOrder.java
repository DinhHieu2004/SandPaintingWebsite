package com.example.web.controller.admin;

import com.example.web.controller.util.GsonProvider;
import com.example.web.dao.model.Order;
import com.example.web.service.OrderService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "ShowOrderAdmin",value = "/admin/show-order")

public class ShowOrder extends HttpServlet {
    OrderService orderService = new OrderService();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Order> currentOrders = null;
        List<Order> previousOrders = null;
        try {
            currentOrders = orderService.getOrderCurrentAdmin();
            previousOrders = orderService.getOrderHistoryAdmin();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        Map<String, Object> result = new HashMap<>();
        result.put("currentOrders", currentOrders);
        result.put("previousOrders", previousOrders);

        System.out.println(GsonProvider.getGson().toJson(result));
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(GsonProvider.getGson().toJson(result));
    }
}
