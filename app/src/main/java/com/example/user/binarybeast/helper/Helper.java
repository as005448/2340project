package com.example.user.binarybeast.helper;

import android.content.Context;

import com.example.user.binarybeast.model.FriendTable;
import com.example.user.binarybeast.model.Interest;
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
    public UserData currUser;
    public Helper(Context context) {

//        this.dataBase = UserDBHandler.getInstance();
        this.dataBase = new UserDBHandler(context);
//        databaseUpgrade();
        this.userDatas = dataBase.getAllUser();
        this.friendTables = dataBase.getFriendTable();
        defaultUser();
    }
    public void databaseUpgrade() {
        dataBase.onUpgrade(dataBase.getWritableDatabase(), 3, 4);
    }
    private void defaultUser() {
        if (findUser("user", "user") == null) {
            dataBase.addUser("user", "pass", "user", "user@gatech.edu");
        }
        userDatas = dataBase.getAllUser();
    }

    public UserData findUser(String information, String type) {
        if (information == null) {
            throw new IllegalArgumentException("information is null");
        }
        if (information == null) {
            throw new IllegalArgumentException("type is null");
        }
        return dataBase.getUser(information, type);
    }
    public boolean login(String user, String pass) {
         UserData userdata = findUser(user, "user");
         if (userdata == null) {
             throw new NoSuchElementException("can not find the username");
         }
         if (userdata.getPass().equals(pass)) {
             currUser = userdata;
             return true;
         } else {
            return false;
         }
    }
    public void addUser(String username, String pass, String name, String email) {
        dataBase.addUser(username, pass, name, email);
//        userDatas = dataBase.getAllUser();
    }
    public boolean addFriend(String name, String email) {
        UserData user;
        if (findUser(name, "name") != null) {
            user = findUser(name, "name");
        } else if (findUser(email, "email") != null) {
            user = findUser(email, "email");
        } else {
            return false;
        }
        dataBase.addFriend(currUser.getId(), user.getId());
        friendTables = dataBase.getFriendTable();
        return true;
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

    public boolean addInterest(String name, String category, String price) {
        dataBase.addInterest(name, category, price, currUser.getId());
        return true;
    }

    public void signOut() {
        currUser = null;
    }

    public List<Interest> getInterestListByOwner(int id) {
        List<Interest> interestList = new ArrayList<>(dataBase.findInterestByOwner(id));
        return interestList;
    }
}
