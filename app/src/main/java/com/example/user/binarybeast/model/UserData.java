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
public class UserData {
//    private HashMap<String, String> account;
//    public UserDBHandler dbhandler;
    private String currentUser;
//    public HashMap<String, Friend> friends;

    private int id;
    private String user;
    private String pass;
    private String name;
    private String email;
    private int rate;
    private int post;
    public UserData(String user, String pass, String name, String email) {
        this.user = user;
        this.pass = pass;
        this.name = name;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public String getUser() {
        return user;
    }

    public String getPass() {
        return pass;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public int getPost() {
        return post;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public void setPost(int post) {
        this.post = post;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    /* Constructor for the UserData.
             *
             * @param context of activity creating it.
             */
    public UserData() {
        //this.account = new HashMap<String, String>();
//        this.dbhandler = UserDBHandler.getInstance();
//        this.account = dbhandler.getAllUsers();
//        this.friends = new HashMap<>();
//        account.put("user","pass");
//        Set<String> names = account.keySet();
//        Friend temp = new Friend("temp", "temp@willchange");
//        for(String s : names) {
//            friends.put(s, temp);
//        }
    }

    /*
     *  Adds a new account to the list of registered users.
     *
     *  @param username the username of the user
     *  @param password the password of the user
     *
     */
//    public void addAccount(String username, String password) {
//        account.put(username, password);
//        dbhandler.addUser(username, password);
//
//    }

    /*
     *  Checks to see if the user is registered.
     *
     *  @param username the name of the user to check
     *  @return whether of not the user is registered
     *
     */
//    public boolean existAccount(String username) {
//        return account.containsKey(username);
////    }
    
    /*
     * Authenticates the user information provided.
     *
     * @param username the name of the user wanting to login
     * @param password the entered password of the user
     * @return true if the user and password are correct for that user's
     * account.
     *
     */
//    public boolean verifyAccount(String username, String password) {
//        return account.get(username).equals(password);
//    }


    /**
     * This function gets the set of friend names to the caller.
     * It is used to populate the list view of friends.
     *
     * @return a set of friend names to the user.
     */
//    public Set<String> getUserList() {
//        Set<String> set2 = friends.keySet();
//
//        return set2;
//    }

    /**
     * Sets the current user on login.
     * Otherwise we don't know who we are logged in as
     *
     * @param user
     */
    public void setCurrentUser(String user) {
        currentUser = user;
    }

    /**
     * This returns the currently logged in user.
     *
     * @return the name of the current user.
     */
    public String getCurrentUser() {
        return currentUser;
    }

//    public void addFriend(String user, String email) {
//        Friend newFriendPerson = new Friend(user, email);
//        friends.put(user, newFriendPerson);
//    }

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
