package com.example.user.binarybeast.model;

/**
 * Created by marcus on 3/10/15.
 */

/**
 * Class to represent a sale.
 */
@SuppressWarnings("ALL")
public class Sale {
    private String name;
    private String category;
    private String location;
    private int price;
    private int owner;

    public Sale(String name, String category, int price, String location, int owner) {
        this.name = name;
        this.category = category;
        this.price = price;
        this.owner = owner;
        this.location = location;
    }

    public int getPrice() {
        return price;
    }

    public String getName() { return name; }
    public String getCategory() { return category; }
    public String getLocation() {
        return location;
    }
}
