package com.ksg.easykitchen.model;

import java.io.Serializable;

public class Products implements Serializable {

    int id;
    String product;
    String description;
    double price;
    double weight;
    int isAvailable;

    public Products() {
    }

    public Products(int id, String product, String description, double price, double weight, int isAvailable) {
        this.id = id;
        this.product = product;
        this.description = description;
        this.price = price;
        this.weight = weight;
        this.isAvailable = isAvailable;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public int getIsAvailable() {
        return isAvailable;
    }

    public void setIsAvailable(int isAvailable) {
        this.isAvailable = isAvailable;
    }
}
