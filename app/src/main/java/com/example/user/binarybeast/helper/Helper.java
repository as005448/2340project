package com.example.user.binarybeast.helper;

import com.example.user.binarybeast.model.Friend;
import com.example.user.binarybeast.model.FriendTable;
import com.example.user.binarybeast.model.UserData;

import java.util.List;

/**
 * Created by Administrator on 2015/2/23.
 */
public class Helper {
    private UserDBHandler dataBase;
    private List<UserData> userData;
    private List<FriendTable> friendTable;
    public Helper() {
        this.dataBase = UserDBHandler.getInstance();
        this.userData = dataBase.getAllUser();
        this.friendTable = dataBase.getFriendTable();
    }

}
