package com.example.user.binarybeast.helper;

import android.content.Context;

import com.example.user.binarybeast.model.Interest;
import com.example.user.binarybeast.model.Sale;
import com.example.user.binarybeast.model.UserData;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Created by Yan on 2015/2/23.
 * @author Yan Chen
 */
public class Helper {

    private UserDBHandler dataBase;
    public UserData currUser;

    public Helper(Context context) {
        this.dataBase = new UserDBHandler(context);
        defaultUser();
    }


    /**
     * upgrade the database
     */
    public void databaseUpgrade() {
        dataBase.onUpgrade(dataBase.getWritableDatabase(), 3, 4);
    }

    /**
     * add default user to database
     */
    private void defaultUser() {
        if (findUser("user", "user") == null) {
            dataBase.addUser("user", "pass", "user", "user@gatech.edu");
        }
    }

    /**
     * initialize TextViews to show friend's detail
     * @param information search information about the user
     * @param type type of information, has to be either user, name or email
     * @return return null if no user match the information, else return found user
     */
    public UserData findUser(String information, String type) {
        if (information == null) {
            throw new IllegalArgumentException("information is null");
        }
        if (type == null) {
            throw new IllegalArgumentException("type is null");
        }
        return dataBase.getUser(information, type);
    }

    /**
     * login and set current user
     * @param user username
     * @param pass password
     * @return return true if login successful else return false
     */
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

    /**
     * add new user to database
     * @param username username of the new user
     * @param pass password of the new user
     * @param name name of the new user
     * @param email email of the new user
     */
    public void addUser(String username, String pass, String name, String email) {
        dataBase.addUser(username, pass, name, email);
    }

    /**
     * add new friend to current user
     * @param friend friend needs to be added
     * @return return true if add successful
     */
    public boolean addFriend(UserData friend) {
        if (dataBase.isFriend(currUser, friend)) {
            return false;
        }
        dataBase.addFriend(currUser.getId(), friend.getId());
        return true;
    }

    /**
     * get all friends' name
     * @return list of friends' name
     */
    public List<String> getFriendName() {
        List<UserData> users = dataBase.getFriends(currUser);
        List<String> userNames = new ArrayList<>();
        for (UserData u:users) {
            userNames.add(u.getName());
        }
        return userNames;
    }

    /**
     * delete a friend of current user
     * @param friend friend needs to be deleted
     */
    public void deleteFriend(UserData friend) {
        int userID = currUser.getId();
        int friendID = friend.getId();
        dataBase.deleteFriend(userID, friendID);
    }

    /**
     * add a new interest
     * @param name name of the new interest
     * @param category category of the new interest
     * @param price price of the new interest
     * @return return true if add successful otherwise return false
     */
    public boolean addInterest(String name, String category, String price) {
        dataBase.addInterest(name, category, price, currUser.getId());
        return true;
    }

    /**
     * add a new sale
     * @param name name of the new sale
     * @param category category of the new sale
     * @param price price of the new sale
     * @param location location of the new sale
     * @return return true if add successful otherwise return false
     */
    public boolean addSale(String name, String category, String price, String location) {
        dataBase.addSale(name, category, price, location,currUser.getId());
        return true;
    }

    /**
     * sign out
     */
    public void signOut() {
        currUser = null;
    }

    /**
     * add a new interest
     * @param id user's id
     * @return list of interest of the user
     */
    public List<Interest> getInterestListByOwner(int id) {
        List<Interest> interestList = new ArrayList<>(dataBase.findInterestByOwner(id));
        return interestList;
    }

    public List<Sale> getSaleByPrice(String name, int price) {
        return dataBase.findSales(name, price);
    }
    public Sale getSaleByName(String name) {
        return dataBase.findSaleByName(name).get(0);
    }
}
