package com.example.user.binarybeast.model;

/**
 * Created by Administrator on 2015/3/1.
 */
public class Interest {
    private String name;
    private String category;
    private int price;
    private int owner;

    /**
     * non-argument constructor
     */
    public Interest() {
    }

    /**
     * constructor that takes in name, category and owner
     * @param name name of the new interest
     * @param category category of the new interest
     * @param owner price of the new interest
     */
    public Interest(String name, String category, int price, int owner) {
        this.name = name;
        this.category = category;
        this.price = price;
        this.owner = owner;
    }

    /**
     * getter for number of name
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * getter for number of category
     * @return category
     */
    public String getCategory() {
        return category;
    }

    /**
     * getter for number of price
     * @return price
     */
    public int getPrice() {
        return price;
    }

    /**
     * getter for number of owner
     * @return owner
     */
    public int getOwner() {
        return owner;
    }

    /**
     * setter for name
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * setter for category
     * @param category
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * setter for price
     * @param price
     */
    public void setPrice(int price) {
        this.price = price;
    }

    /**
     * setter for owner
     * @param owner
     */
    public void setOwner(int owner) {
        this.owner = owner;
    }
}
