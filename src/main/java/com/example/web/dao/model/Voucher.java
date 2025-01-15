package com.example.web.dao.model;

import java.util.Date;

public class Voucher {
    private int id;
    private String name;
    private double discount;
    private boolean isActive;
    private Date createAt;

    public Voucher(int id, String name, boolean isActive, Date createAt) {
        this.id = id;
        this.name = name;
        this.isActive = isActive;
        this.createAt = createAt;
    }
    public Voucher() {}

    public int getId() {
        return id;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    @Override
    public String toString() {
        return "Voucher{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", isActive=" + isActive +
                ", discount=" + discount +
                ", createAt=" + createAt +
                '}';
    }
}
