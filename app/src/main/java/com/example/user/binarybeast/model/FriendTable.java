package com.example.user.binarybeast.model;

/**
 * Created by Administrator on 2015/2/23.
 */
public class FriendTable {
    private int id;
    private int userID;
    private int friendID;
    private int post;
    public FriendTable() {
    }
    public FriendTable(int id, int userID, int friendID) {
        this.id = id;
        this.userID = userID;
        this.friendID = friendID;
    }

    public void setPost(int post) {
        this.post = post;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public void setFriendID(int friendID) {
        this.friendID = friendID;
    }

    public int getPost() {
        return post;
    }

    public int getId() {
        return id;
    }

    public int getUserID() {
        return userID;
    }

    public int getFriendID() {
        return friendID;
    }
}
