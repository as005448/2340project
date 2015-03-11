package com.example.user.binarybeast.helper;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;
import android.content.Context;
import android.content.ContentValues;
import android.util.Log;

import com.example.user.binarybeast.Friend;
import com.example.user.binarybeast.model.FriendTable;
import com.example.user.binarybeast.model.Interest;
import com.example.user.binarybeast.model.Sale;
import com.example.user.binarybeast.model.UserData;

import java.util.ArrayList;
import java.util.List;

/*
 *  @author Marcus Godwin
 *  @author Yan Chen
 *  @version 1.0
 */
public class UserDBHandler extends SQLiteOpenHelper {
    //Static vars
    private static final int DATABASE_VERSION = 1;
    // Logcat tag
    private static final String LOG = "UserDBHandler";
    //Database name
    private static final String DATABASE_NAME = "registeredUsers";

    //table name
    private static final String TABLE_USERS = "users";
    private static final String TABLE_FRIEND = "friend";
    private static final String TABLE_INTEREST = "interest";
    private static final String TABLE_SALES = "sales";


    // table users column names
    private static final String KEY_ID = "id";
    private static final String KEY_USER = "user";
    private static final String KEY_PASS = "pass";
    private static final String KEY_NAME = "name";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_POST = "post";
    private static final String KEY_RATE = "rate";

    // table friend column names
    private static final String KEY_USER_ID = "user_id";
    private static final String KEY_FRIEND_ID = "friend_id";
    private static final String KEY_FRIEND_POST = "friend_post";

    //table interest column names
    //reuse for sales columns
    private static final String KEY_INTEREST_NAME = "name";
    private static final String KEY_INTEREST_CATEGORY = "category";
    private static final String KEY_INTEREST_PRICE = "price";
    private static final String KEY_INTEREST_OWNER = "owner";

    private static final String KEY_SALE_LOCATION = "location";


    /*
     * Constructor for the database handler.
     *
     * @param context the android context creating the databasehandler
     *
     */
    public UserDBHandler(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        //forceUpdate();
    }

    /*
     * Function called on the creation of the database.
     *
     * @param db the database to execute SQL commands on
     *
     */
    //Create tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE_USERS = "CREATE TABLE "
                + TABLE_USERS + "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_USER
                + " TEXT," + KEY_PASS + " TEXT," + KEY_NAME + " TEXT," + KEY_EMAIL
                + " TEXT," + KEY_POST + " INTEGER," + KEY_RATE + " INTEGER" + ")";
        String CREATE_TABLE_FRIEND = "CREATE TABLE "
                + TABLE_FRIEND + "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_USER_ID
                + " INTEGER," + KEY_FRIEND_ID + " INTEGER," + KEY_FRIEND_POST
                + " INTEGER" + ")";
        String CREATE_TABLE_INTEREST = "CREATE TABLE "
                + TABLE_INTEREST + "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_INTEREST_NAME
                + " TEXT," + KEY_INTEREST_CATEGORY + " TEXT," + KEY_INTEREST_PRICE
                + " INTEGER, " + KEY_INTEREST_OWNER + " INTEGER" + ")";
        String CREATE_TABLE_SALES = "CREATE TABLE "
                + TABLE_SALES + "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_INTEREST_NAME
                + " TEXT," + KEY_SALE_LOCATION + " TEXT," + KEY_INTEREST_PRICE
                + " INTEGER, " + KEY_INTEREST_OWNER + " INTEGER" +")";
        db.execSQL(CREATE_TABLE_USERS);
        db.execSQL(CREATE_TABLE_FRIEND);
        db.execSQL(CREATE_TABLE_INTEREST);
        db.execSQL(CREATE_TABLE_SALES);
    }
    
    /*
     * Upgrades the database to a new version.
     *
     * @param db the database to execute SQL commands on
     * @param oldV the old database version number
     * @param newV the new database version number
     *
     */
    //For upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldV, int newV) {
        //Drop table if it exists
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FRIEND);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_INTEREST);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SALES);

        //make new table
        onCreate(db);
    }

    /**
     * add user's information to database
     * @param user username of the new user
     * @param pass password of the new user
     * @param name name of the new user
     * @param email email of the new user
     * @return user's id
     */
    public long addUser(String user, String pass, String name, String email) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_USER, user);
        values.put(KEY_PASS, pass);
        values.put(KEY_NAME, name);
        values.put(KEY_EMAIL, email);
        values.put(KEY_POST, 0);
        values.put(KEY_RATE, 0);

        long userID = db.insert(TABLE_USERS, null, values);
        db.close();
        return userID;
    }

    /**
     * get user by it's name or email or username
     * @param information information of the user
     * @param type type of the information
     * @return user's data
     */
    public UserData getUser(String information, String type) {
        String selectQuery;
        if (type.equals("name")) {
            selectQuery = "SELECT  * FROM " + TABLE_USERS + " WHERE "
                    + KEY_NAME + " = '" + information + "';";
        } else if (type.equals("email")) {
            selectQuery = "SELECT  * FROM " + TABLE_USERS + " WHERE "
                    + KEY_EMAIL + " = '" + information + "';";
        }else if (type.equals("user")) {
            selectQuery = "SELECT  * FROM " + TABLE_USERS + " WHERE "
                    + KEY_USER + " = '" + information + "';";
        } else {
            throw new IllegalArgumentException("type is illegal");
        }
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);
        if (c.moveToFirst() == false) {
            db.close();
            return null;
        }
        UserData user = new UserData();
        user.setId(c.getInt((c.getColumnIndex(KEY_ID))));
        user.setUser((c.getString(c.getColumnIndex(KEY_USER))));
        user.setPass(c.getString(c.getColumnIndex(KEY_PASS)));
        user.setName(c.getString(c.getColumnIndex(KEY_NAME)));
        user.setEmail(c.getString(c.getColumnIndex(KEY_EMAIL)));
        db.close();
        return user;
    }

    /**
     * get user by it's id
     * @param user_id id of the user
     * @return user's data
     */
    public UserData getUser(long user_id) {
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT  * FROM " + TABLE_USERS + " WHERE "
                + KEY_ID + " = " + user_id;
        
        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null) {
            c.moveToFirst();
        } else {
            db.close();
            return null;
        }

        UserData user = new UserData();
        user.setId(c.getInt((c.getColumnIndex(KEY_ID))));
        user.setUser((c.getString(c.getColumnIndex(KEY_USER))));
        user.setPass(c.getString(c.getColumnIndex(KEY_PASS)));
        user.setName(c.getString(c.getColumnIndex(KEY_NAME)));
        user.setEmail(c.getString(c.getColumnIndex(KEY_EMAIL)));
        db.close();
        return user;
    }

    /**
     * check to see if two users are friend or not
     * @param user user1
     * @param friend user2
     * @return true if they are friend, false otherwise
     */
    public boolean isFriend(UserData user, UserData friend) {
        String selectQuery = "SELECT  * FROM " + TABLE_FRIEND + " WHERE "
                + KEY_USER_ID + " = '" + user.getId() + "' AND " + KEY_FRIEND_ID
                + " = '" + friend.getId() + "';";

        Log.e(LOG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        if (c.moveToFirst()) {
            db.close();
            return true;
        }
        db.close();
        return false;
    }

    /**
     * return list of user's friend
     * @param user current user
     * @return list of user's friend
     */
    public List<UserData> getFriends(UserData user) {
        int userId = user.getId();
        List<UserData> friendList = new ArrayList<>();
        String selectQuery = "SELECT  * FROM " + TABLE_USERS + " u, "
                + TABLE_FRIEND + " f WHERE f."
                + KEY_USER_ID + " = '" + userId + "'" + " AND f." + KEY_FRIEND_ID
                + " = " + "u." + KEY_ID;

        Log.e(LOG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            int i = 0;
            do {
                UserData u = new UserData();

                u.setId(c.getInt(c.getColumnIndex(KEY_ID)));
                u.setUser((c.getString(c.getColumnIndex(KEY_USER))));
                u.setPass(c.getString(c.getColumnIndex(KEY_PASS)));
                u.setName(c.getString(c.getColumnIndex(KEY_NAME)));
                u.setEmail(c.getString(c.getColumnIndex(KEY_EMAIL)));
                u.setRate(c.getInt(c.getColumnIndex(KEY_RATE)));
                u.setPost(c.getInt(c.getColumnIndex(KEY_POST)));

                // adding to USER list
                friendList.add(i, u);
                i++;
            } while (c.moveToNext());
        }
        db.close();
        return friendList;
    }

    /**
     * add friends' information to database
     * @param user_id user of the new friend relation
     * @param friend_id friend of the new friend relation
     * @return friend table's id
     */
    public long addFriend(long user_id, long friend_id) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_USER_ID,user_id);
        values.put(KEY_FRIEND_ID, friend_id);
        values.put(KEY_FRIEND_POST, 0);

        long id = db.insert(TABLE_FRIEND, null, values);

        ContentValues values2 = new ContentValues();
        values2.put(KEY_USER_ID,friend_id);
        values2.put(KEY_FRIEND_ID, user_id);
        values2.put(KEY_FRIEND_POST, 0);
        db.insert(TABLE_FRIEND, null, values2);
        db.close();
        return id;
    }

    /**
     * get pair of the friends
     * @param user user1
     * @param friend user2
     * @return list of the pair
     */
    public List<Integer> getFriendPair(int user, int friend) {
        List<Integer> pair = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + TABLE_FRIEND + " WHERE "
                + KEY_USER_ID + " = '" + user + "' AND " + KEY_FRIEND_ID
                + " = '" + friend + "';";
        Log.e(LOG, selectQuery);
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                pair.add(cursor.getInt(cursor.getColumnIndex(KEY_ID)));
            } while (cursor.moveToNext());
        }
        db.close();
        return pair;
    }

    /**
     * delete relations of two users
     * @param user user1
     * @param friend user2
     */
    public void deleteFriend(int user, int friend) {
        List<Integer> pair = getFriendPair(user, friend);
        SQLiteDatabase db = this.getWritableDatabase();
        for (int i:pair) {
            db.delete(TABLE_FRIEND, KEY_ID + " = ?",
                    new String[]{String.valueOf(i)});
        }
        db.close();
    }

    /**
     * add interest's information to database
     * @param name name of the new interest
     * @param category category of the new interest
     * @param price price of the new interest
     * @param owner owner of the new interest
     * @return interest's id
     */
    public long addInterest(String name, String category, String price, int owner) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_INTEREST_NAME, name);
        values.put(KEY_INTEREST_CATEGORY, category);
        values.put(KEY_INTEREST_PRICE, price);
        values.put(KEY_INTEREST_OWNER, owner);

        long interestID = db.insert(TABLE_INTEREST, null, values);
        db.close();
        return interestID;
    }

    /**
     * find interest by it's name
     * @param name name of the new interest
     * @return list of interest of the interest name
     */
    public List<Interest> findInterestByName(String name) {
        List<Interest> interests = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + TABLE_INTEREST + " WHERE "
                + KEY_INTEREST_NAME + " = '" + name + "';";
        Log.e(LOG, selectQuery);
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                String category = cursor.getString(cursor.getColumnIndex(KEY_INTEREST_CATEGORY));
                int price = cursor.getInt(cursor.getColumnIndex(KEY_INTEREST_PRICE));
                int owner = cursor.getInt(cursor.getColumnIndex(KEY_INTEREST_OWNER));
                Interest interest = new Interest(name, category, price, owner);
                interests.add(interest);
            } while (cursor.moveToNext());
        }
        db.close();
        return interests;
    }

    /**
     * find interest by it's owner's id
     * @param id id of the owner
     * @return list of interest
     */
    public List<Interest> findInterestByOwner(int id) {
        List<Interest> interests = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + TABLE_INTEREST + " WHERE "
                + KEY_INTEREST_OWNER + " = " + id + ";";
        Log.e(LOG, selectQuery);
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                String name = cursor.getString(cursor.getColumnIndex(KEY_INTEREST_NAME));
                String category = cursor.getString(cursor.getColumnIndex(KEY_INTEREST_CATEGORY));
                int price = cursor.getInt(cursor.getColumnIndex(KEY_INTEREST_PRICE));
                Interest interest = new Interest(name, category, price, id);
                interests.add(interest);
            } while (cursor.moveToNext());
        }
        db.close();
        return interests;
    }

    /**
     * add a sales report to the database
     * @param name name of the new sale
     * @param category category of the new sale
     * @param price price of the new sale
     * @param location location of the new sale
     * @param owner owner of the new interest
     * @return sale's id
     */
    public long addSale(String name, String category, String price, String location, int owner) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_INTEREST_NAME, name);
        //values.put(KEY_INTEREST_CATEGORY, category);
        values.put(KEY_INTEREST_PRICE, price);
        values.put(KEY_INTEREST_OWNER, owner);
        values.put(KEY_SALE_LOCATION, location);

        long interestID = db.insert(TABLE_SALES, null, values);
        db.close();
        return interestID;
    }

    /**
     * find sales by name
     * @param name name of the new sale
     * @param price price boundary
     * @return list of sales by that name
     */
    public List<Sale> findSales(String name, int price) {
        List<Sale> temp = findSaleByName(name);
        List<Sale> sales = new ArrayList<>();
        //only add sales below or at price
        for(Sale s : temp) {
            if (s.getPrice() <= price) {
                sales.add(s);
            }
        }

        return sales;
    }

    /**
     * find sales by name
     * @param name name of the new sale
     * @return list of sales by that name
     */
    public List<Sale> findSaleByName(String name) {
        List<Sale> sales = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + TABLE_SALES + " WHERE "
                + KEY_INTEREST_NAME + " = '" + name + "';";
        Log.e(LOG, selectQuery);
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                //String category = cursor.getString(cursor.getColumnIndex(KEY_INTEREST_CATEGORY));
                String location = cursor.getString(cursor.getColumnIndex(KEY_SALE_LOCATION));
                int price = cursor.getInt(cursor.getColumnIndex(KEY_INTEREST_PRICE));
                int owner = cursor.getInt(cursor.getColumnIndex(KEY_INTEREST_OWNER));
                Sale sale = new Sale(name, "", price, location, owner);
                sales.add(sale);
            } while (cursor.moveToNext());
        }
        db.close();
        return sales;
    }
    
    /**
     * Should not be called!
     * Used only by me to clear out the database forcefully
     * -Marcus
     */
    public void forceUpdate() {
        SQLiteDatabase db = this.getReadableDatabase();
        onUpgrade(db, 1, 1);
    }


}
