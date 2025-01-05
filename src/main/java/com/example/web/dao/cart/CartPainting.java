package com.example.web.dao.cart;

import java.io.Serializable;
import java.math.BigDecimal;

public class CartPainting implements Serializable {
    private int productId;
    private String productName;
    private String sizeDescriptions;
    private String sizeId;
    private String imageUrl;
    private int quantity;
    private double price;
    private double totalPrice;

    public CartPainting(int productId, String productName, String sizeId,String sizeDescriptions, int quantity, double price, String imageUrl) {
        this.productId = productId;
        this.productName = productName;
        this.sizeDescriptions = sizeDescriptions;
        this.sizeId = sizeId;
        this.quantity = quantity;
        this.price = price;
        this.totalPrice = price * quantity;
        this.imageUrl = imageUrl;
    }
    public CartPainting() {

    }

    public String getSizeDescriptions() {
        return sizeDescriptions;
    }

    public void setSizeDescriptions(String size) {
        this.sizeDescriptions = size;
    }

    public int getSizeId() {
        return Integer.parseInt(sizeId);
    }

    public void setSizeId(String sizeId) {
        this.sizeId = sizeId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }



    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return "CartPainting{" +
                "productId=" + productId +
                ", productName='" + productName + '\'' +
                ", idSize='" + sizeId + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                ", totalPrice=" + totalPrice +
                '}';
    }

    public void updateQuantity(int additionalQuantity) {
        this.quantity += additionalQuantity;
        this.totalPrice = this.price * this.quantity;
    }

}
