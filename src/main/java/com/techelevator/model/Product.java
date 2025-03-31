package com.techelevator.model;

public class Product {

    private int id;
    private String product_sku;
    private String name;
    private String description;
    private double price;
    private String image_name;

    public Product(){

    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setProduct_sku(String product_sku) {
        this.product_sku = product_sku;
    }

    public String getProduct_sku() {
        return product_sku;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public void setImage_name(String image_name) {
        this.image_name = image_name;
    }

    public String getImage_name() {
        return image_name;
    }
}
