package com.example.user.binarybeast.model;

/**
 * @author Marcus
 * @version 1.0
 */
@SuppressWarnings("CanBeFinal")
public class Sale {
    private String name;
    private String category;
    private String location;
    private int price;
    @SuppressWarnings("FieldCanBeLocal")
    private int owner;

    @SuppressWarnings("SameParameterValue")
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
