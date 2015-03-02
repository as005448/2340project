package com.example.user.binarybeast.model;

/**
 * Created by Yan Chen on 2015/2/23.
 * @author Yan Chen
 * @version 1.0
 */
public class FriendTable {
    private int id;
    private int userID;
    private int friendID;
    private int post;

    /**
     * non-argument constructor
     */
    public FriendTable() {
    }

    /**
     * constructor that takes in id, user's id and friend's id
     * @param id name of the new friend
     * @param userID category of the new friend
     * @param friendID price of the new friend
     */
    public FriendTable(int id, int userID, int friendID) {
        this.id = id;
        this.userID = userID;
        this.friendID = friendID;
    }
    /**
     * setter for number of post
     * @param post
     */
    public void setPost(int post) {
        this.post = post;
    }

    /**
     * setter for friend table's id
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * setter for user's id
     * @param userID
     */
    public void setUserID(int userID) {
        this.userID = userID;
    }

    /**
     * setter for friend's id
     * @param friendID
     */
    public void setFriendID(int friendID) {
        this.friendID = friendID;
    }

    /**
     * getter for number of post
     */
    public int getPost() {
        return post;
    }

    /**
     * getter for id
     */
    public int getId() {
        return id;
    }

    /**
     * getter for user's id
     */
    public int getUserID() {
        return userID;
    }

    /**
     * getter for friend's id
     */
    public int getFriendID() {
        return friendID;
    }
}
