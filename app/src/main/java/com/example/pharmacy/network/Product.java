package com.example.pharmacy.network;


public class Product {

    private long id;
    private String productName;
    private double price;
    private int categoryType;
    private int stock;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getCategoryType() {
        return categoryType;
    }

    public void setCategoryType(int categoryType) {
        this.categoryType = categoryType;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
        return
                 productName + '\'' +
                " id=" + id +
                " price=" + price +
                " category=" + categoryType +
                " stock=" + stock;
    }
}

