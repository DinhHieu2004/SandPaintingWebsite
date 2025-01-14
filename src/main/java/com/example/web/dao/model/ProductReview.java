package com.example.web.dao.model;


public class ProductReview {
    private int userId;
    private int paintingId;
    private int orderItemId;
    private int rating;
    private String comment;

    public ProductReview(int userId, int paintingId, int orderItemId, int rating, String comment) {
        this.userId = userId;
        this.paintingId = paintingId;
        this.orderItemId = orderItemId;
        this.rating = rating;
        this.comment = comment;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getPaintingId() {
        return paintingId;
    }

    public void setPaintingId(int paintingId) {
        this.paintingId = paintingId;
    }

    public int getOrderItemId() {
        return orderItemId;
    }

    public void setOrderItemId(int orderItemId) {
        this.orderItemId = orderItemId;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
