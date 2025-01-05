package com.example.web.dao.model;

import java.math.BigDecimal;

public class OrderItem {
    private int id;
    private int orderId;
    private int paintingId;
    private int sizeId;
    private double price;
    private int quantity;

    // Getters and Setters
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getSizeId() {
        return sizeId;
    }
    public void setSizeId(int sizeId) {
        this.sizeId = sizeId;
    }
    public int getOrderId() {
        return orderId;
    }
    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }
    public int getPaintingId() {
        return paintingId;
    }
    public void setPaintingId(int paintingId) {
        this.paintingId = paintingId;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "id=" + id +
                ", orderId=" + orderId +
                ", paintingId=" + paintingId +
                ", sizeId=" + sizeId +
                ", price=" + price +
                ", quantity=" + quantity +
                '}';
    }
}
