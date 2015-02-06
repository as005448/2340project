package com.example.user.binarybeast;

import java.util.HashMap;

/**
 * Created by Administrator on 2015/2/5.
 */
public class UserData {
    private HashMap<String, String> account;

    public UserData() {
        this.account = new HashMap<String, String>();
        account.put("user","pass");
    }

    public void addAccount(String username, String password) {
        account.put(username, password);
    }
    public boolean existAccount(String username) {
        return account.containsKey(username);
    }

    public boolean verifyAccount(String username, String password) {
        return account.get(username).equals(password);
    }


}
