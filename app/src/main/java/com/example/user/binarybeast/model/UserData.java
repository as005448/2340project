package com.example.user.binarybeast.model;

import java.util.HashMap;
import java.util.Set;

import com.example.user.binarybeast.helper.UserDBHandler;

/**
 * Created by Administrator on 2015/2/5.
 *
 * @author Marcus Godwin
 * @author Yan Chen
 * @version 1.0
 *
 */
@SuppressWarnings("ALL")
public class UserData {

    private String currentUser;
    private int id;
    private String user;
    private String pass;
    private String name;
    private String email;
    private int rate;
    private int post;

    /** Constructor for the UserData.
    */
    public UserData() {

    }

    /**
     * constructor that takes in username, password, name and email
     * @param user user of the new user
     * @param pass pass of the new user
     * @param name name of the new user
     * @param email email of the new user
     */
    public UserData(String user, String pass, String name, String email) {
        this.user = user;
        this.pass = pass;
        this.name = name;
        this.email = email;
    }

    /**
     * getter for id
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * getter for user
     * @return user
     */
    public String getUser() {
        return user;
    }

    /**
     * getter for pass
     * @return pass
     */
    public String getPass() {
        return pass;
    }

    /**
     * getter for name
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * getter for email
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * getter for post
     * @return post
     */
    public int getPost() {
        return post;
    }

    /**
     * getter for rate
     * @return rate
     */
    public int getRate() {
        return rate;
    }

    /**
     * setter for rate
     * @param rate
     */
    public void setRate(int rate) {
        this.rate = rate;
    }

    /**
     * setter for post
     * @param post
     */
    public void setPost(int post) {
        this.post = post;
    }

    /**
     * setter for id
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * setter for user
     * @param user
     */
    public void setUser(String user) {
        this.user = user;
    }

    /**
     * setter for pass
     * @param pass
     */
    public void setPass(String pass) {
        this.pass = pass;
    }

    /**
     * setter for name
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * setter for email
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "UserData{" +
                ", id=" + id +
                ", user='" + user + '\'' +
                ", pass='" + pass + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
