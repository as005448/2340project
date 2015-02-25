package com.example.user.binarybeast.helper;

import android.content.Context;

import com.example.user.binarybeast.model.FriendTable;
import com.example.user.binarybeast.model.UserData;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Created by Administrator on 2015/2/23.
 */
public class Helper {
    private UserDBHandler dataBase;
    private List<UserData> userDatas;
    private List<FriendTable> friendTables;
    private UserData currUser;
    public Helper(Context context) {

//        this.dataBase = UserDBHandler.getInstance();
        this.dataBase = new UserDBHandler(context);
        this.userDatas = dataBase.getAllUser();
        this.friendTables = dataBase.getFriendTable();
        defaultUser();
    }
    private void defaultUser() {
        if (!containUser("user")) {
            dataBase.addUser("user", "pass", "user", "user@gatech.edu");
        }
        userDatas = dataBase.getAllUser();
    }
    public boolean containUser(String information) {
        for (UserData user:userDatas) {
            if (user.getName() != null && user.getName().equals(information)) {
                return true;
            }
            if (user.getEmail() != null && user.getEmail().equals(information)) {
                return true;
            }
            if (user.getUser() != null && user.getUser().equals(information)) {
                return true;
            }
        }
        return false;
    }
    public UserData findUser(String information) throws IllegalArgumentException, NoSuchElementException {
        if (information == null) {
            throw new IllegalArgumentException("information is null");
        }
        for (UserData user:userDatas) {
            if (user.getName() != null && user.getName().equals(information)) {
                    return user;
            }
            if (user.getEmail() != null && user.getEmail().equals(information)) {
                return user;
            }
            if (user.getUser() != null && user.getUser().equals(information)) {
                return user;
            }
        }
        throw new NoSuchElementException("User not Found.");
    }
    public boolean login(String user, String pass) throws NoSuchElementException {
         UserData userdata = findUser(user);
         if (userdata.getPass().equals(pass)) {
             currUser = userdata;
             return true;
         } else {
            return false;
         }
    }
    public void addUser(String username, String pass, String name, String email) {
        dataBase.addUser(username, pass, name, email);
        userDatas = dataBase.getAllUser();
    }
    public void addFriend(String name, String email) throws NoSuchElementException {
        UserData user;
        try {
            user = findUser(name);
        } catch (NoSuchElementException e) {
            user = findUser(email);
        }
        dataBase.addFriend(currUser.getId(), user.getId());
        friendTables = dataBase.getFriendTable();
    }
    public ArrayList<String> getFriendName() {
        ArrayList<String> friendNames = new ArrayList<>();
        for (FriendTable friendRow:friendTables) {
            if (friendRow.getUserID() == (currUser.getId())) {
                try {
                    friendNames.add(getUser(friendRow.getFriendID()).getName());
                } catch (NoSuchElementException e) {
                }
            }
        }
        return friendNames;
    }
    public UserData getUser(int id) throws NoSuchElementException {
        for (UserData user:userDatas) {
            if (user.getId() == id) {
                return user;
            }
        }
        throw new NoSuchElementException("User does not exist.");
    }
    public void deleteFriend(UserData friend) {
        int userID = currUser.getId();
        int friendID = friend.getId();
        dataBase.deleteFriend(userID, friendID);
        friendTables = dataBase.getFriendTable();
    }
    private void check() {
        System.out.println(userDatas);
    }
}
