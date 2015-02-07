package com.example.user.binarybeast;

import java.util.HashMap;
import android.content.Context;

/**
 * Created by Administrator on 2015/2/5.
 */
public class UserData {
    private HashMap<String, String> account;
    private UserDBHandler dbhandler;
    public UserData(Context context) {
        //this.account = new HashMap<String, String>();
        this.dbhandler = new UserDBHandler(context);
        this.account = dbhandler.getAllUsers();
        account.put("user","pass");
    }

    public void addAccount(String username, String password) {
        account.put(username, password);
        dbhandler.addUser(username, password);
        
    }
    public boolean existAccount(String username) {
        return account.containsKey(username);
    }

    public boolean verifyAccount(String username, String password) {
        return account.get(username).equals(password);
    }


}
