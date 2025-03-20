package com.example.midterm_frost;

import java.io.Serializable;

public class Product implements Serializable {
    private String name;
    private int imageResource;
    private double price;
    private float rating;
    private String skinType;
    private String category;

    public Product(String name, int imageResource, double price, float rating, String skinType, String category) {
        this.name = name;
        this.imageResource = imageResource;
        this.price = price;
        this.rating = rating;
        this.skinType = skinType;
        this.category = category;
    }

    public String getName() { return name; }
    public int getImageResource() { return imageResource; }
    public double getPrice() { return price; }
    public float getRating() { return rating; }
    public String getSkinType() { return skinType; }
    public String getCategory() { return category; }
}
