package com.example.web.dao.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Order {
    private int id;
    private int userId;
    private String status;
    private double totalAmount;
    private LocalDateTime orderDate;
    private LocalDateTime deliveryDate;

    public static final String STATUS_PENDING = "chờ";
    public static final String STATUS_DELIVERING = "đang giao";
    public static final String STATUS_COMPLETED = "hoàn thành";
    public static final String STATUS_FAILED = "thất bại";
    public static final String STATUS_CANCELED = "đã hủy";

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public String getDeliveryDate() {
        if (deliveryDate != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            return  deliveryDate.format(formatter);
        }
        return null;
    }
    public LocalDateTime getDeliveryDateByLocal(){
        return this.deliveryDate;
    }


    public void setDeliveryDate(LocalDateTime deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", userId=" + userId +
                ", status='" + status + '\'' +
                ", totalAmount=" + totalAmount +
                ", orderDate=" + orderDate +
                ", deliveryDate=" + deliveryDate +
                '}';
    }
}
