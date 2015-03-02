package com.example.user.binarybeast.model;

/**
 * Created by Administrator on 2015/3/1.
 */
public class Interest {
    private String name;
    private String category;
    private int price;
    private int owner;
    public Interest() {
    }
    public Interest(String name, String category, int price, int owner) {
        this.name = name;
        this.category = category;
        this.price = price;
        this.owner = owner;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public int getPrice() {
        return price;
    }

    public int getOwner() {
        return owner;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setOwner(int owner) {
        this.owner = owner;
    }
}
