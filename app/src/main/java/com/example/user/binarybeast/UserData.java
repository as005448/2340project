package com.example.user.binarybeast;

import java.util.HashMap;
import android.content.Context;

/**
 * Created by Administrator on 2015/2/5.
 *
 * @author Marcus Godwin
 * @author Yan Chen
 * @version 1.0
 *
 */
public class UserData {
    private HashMap<String, String> account;
    public UserDBHandler dbhandler;

    /* Constructor for the UserData.
     *
     * @param context of activity creating it.
     */
    public UserData() {
        //this.account = new HashMap<String, String>();
        this.dbhandler = UserDBHandler.getInstance();
        this.account = dbhandler.getAllUsers();
        account.put("user","pass");
    }
      

    /*
     *  Adds a new account to the list of registered users.
     *
     *  @param username the username of the user
     *  @param password the password of the user
     *
     */
    public void addAccount(String username, String password) {
        account.put(username, password);
        dbhandler.addUser(username, password);
        
    }

    /*
     *  Checks to see if the user is registered.
     *
     *  @param username the name of the user to check
     *  @return whether of not the user is registered
     *
     */
    public boolean existAccount(String username) {
        return account.containsKey(username);
    }
    
    /*
     * Authenticates the user information provided.
     *
     * @param username the name of the user wanting to login
     * @param password the entered password of the user
     * @return true if the user and password are correct for that user's
     * account.
     *
     */
    public boolean verifyAccount(String username, String password) {
        return account.get(username).equals(password);
    }


}
