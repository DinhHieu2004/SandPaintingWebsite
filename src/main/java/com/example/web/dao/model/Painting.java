package com.example.web.dao.model;

import java.io.Serializable;
import java.util.Date;

public class Painting implements Serializable{
    private int id;
    private String title;
    private int themId;
    private int sizeId;
    private double price;
    private int quantity;
    private int artistId;
    private int discountId;
    private String description;
    private String imageUrl;
    private boolean isSold;
    private boolean isFeatured;
    private Date createdAt;

    public Painting(int id, String title, int themId, int sizeId, double price, int quantity, int artistId, int discountId, String description, String imageUrl, boolean isSold, boolean isFeatured, Date createdAt) {
        this.id = id;
        this.title = title;
        this.themId = themId;
        this.sizeId = sizeId;
        this.price = price;
        this.quantity = quantity;
        this.artistId = artistId;
        this.discountId = discountId;
        this.description = description;
        this.imageUrl = imageUrl;
        this.isSold = isSold;
        this.isFeatured = isFeatured;
        this.createdAt = createdAt;
    }
    public Painting(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getThemId() {
        return themId;
    }

    public void setThemId(int themId) {
        this.themId = themId;
    }

    public int getSizeId() {
        return sizeId;
    }

    public void setSizeId(int sizeId) {
        this.sizeId = sizeId;
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

    public int getArtistId() {
        return artistId;
    }

    public void setArtistId(int artistId) {
        this.artistId = artistId;
    }

    public int getDiscountId() {
        return discountId;
    }

    public void setDiscountId(int discountId) {
        this.discountId = discountId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public boolean isSold() {
        return isSold;
    }

    public void setSold(boolean sold) {
        isSold = sold;
    }

    public boolean isFeatured() {
        return isFeatured;
    }

    public void setFeatured(boolean featured) {
        isFeatured = featured;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "Painting{" +
                "isFeatured=" + isFeatured +
                ", id=" + id +
                ", title='" + title + '\'' +
                ", themId=" + themId +
                ", sizeId=" + sizeId +
                ", price=" + price +
                ", quantity=" + quantity +
                ", artistId=" + artistId +
                ", discountId=" + discountId +
                ", description='" + description + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", isSold=" + isSold +
                ", createdAt=" + createdAt +
                '}';
    }
}
