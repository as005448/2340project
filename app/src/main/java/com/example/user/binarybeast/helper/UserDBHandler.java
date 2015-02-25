package com.example.user.binarybeast.helper;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;
import android.content.Context;
import android.content.ContentValues;
import android.util.Log;

import com.example.user.binarybeast.model.FriendTable;
import com.example.user.binarybeast.model.UserData;

import java.util.ArrayList;
import java.util.List;


/*
 *  @author Marcus Godwin
 *  @version 1.0
 *
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
    
    private static UserDBHandler instance = null;
    
    public static Context ctx = null;
    /*
     * Constructor for the database handler.
     *
     * @param context the android context creating the databasehandler
     *
     */
    public UserDBHandler(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    
    /*
     * Getter for instance of UserDBHandler
     * @return the UserDBHandler
     */
//    public static UserDBHandler getInstance() {
//        if (instance == null)
//            instance = new UserDBHandler();
//        return instance;
//    }

    /*
     * Function called on the creation of the database.
     *
     * @param db the database to execute SQL commands on
     *
     */
    //Create tables
    @Override
    public void onCreate(SQLiteDatabase db) {
//        String CREATE_USERS_TABLE = "CREATE TABLE " + TABLE_USERS + "("
//            + KEY_USER + " TEXT," + KEY_PASS + " TEXT" + ")";
        String CREATE_TABLE_USERS = "CREATE TABLE "
                + TABLE_USERS + "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_USER
                + " TEXT," + KEY_PASS + " TEXT," + KEY_NAME + " TEXT," + KEY_EMAIL
                + " TEXT," + KEY_POST + " INTEGER," + KEY_RATE + " INTEGER" + ")";
        String CREATE_TABLE_FRIEND = "CREATE TABLE "
                + TABLE_FRIEND + "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_USER_ID
                + " INTEGER," + KEY_FRIEND_ID + " INTEGER," + KEY_FRIEND_POST
                + " INTEGER" + ")";
        db.execSQL(CREATE_TABLE_USERS);
        db.execSQL(CREATE_TABLE_FRIEND);
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

        //make new table
        onCreate(db);
    }


    //Create, Read, Update, and Delete ops
    
    /*
     * Adds the user and password information to the database.
     *
     * @param user the username of the user to add
     * @param pass the password of the user to add
     *
     */
    //Add new users
//    public void addUser(String user, String pass) {
//        SQLiteDatabase db = this.getWritableDatabase();
//
//        ContentValues values = new ContentValues();
//        values.put(KEY_USER, user);
//        values.put(KEY_PASS, pass);
//
//        db.insert(TABLE_USERS, null, values);
//        db.close();
//    }
    public long addUser(String user, String pass, String name, String email) {
        Log.e(LOG,  "" + (this == null));
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
    /*
    * getting all users
    * */

     public List<UserData> getAllUser() {
        List<UserData> users = new ArrayList<UserData>();
        String selectQuery = "SELECT * FROM " + TABLE_USERS;

        Log.e(LOG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        int i = 0;
        if (c.moveToFirst()) {
            do {
                UserData user = new UserData();

                user.setId(c.getInt(c.getColumnIndex(KEY_ID)));
                user.setUser((c.getString(c.getColumnIndex(KEY_USER))));
                user.setPass(c.getString(c.getColumnIndex(KEY_PASS)));
                user.setName(c.getString(c.getColumnIndex(KEY_NAME)));
                user.setEmail(c.getString(c.getColumnIndex(KEY_EMAIL)));
                user.setRate(c.getInt(c.getColumnIndex(KEY_RATE)));
                user.setPost(c.getInt(c.getColumnIndex(KEY_POST)));

                // adding to USER list
                users.add(i, user);
                i++;
            } while (c.moveToNext());
        }
         db.close();
        return users;
    }
    public List<FriendTable> getFriendTable() {
        List<FriendTable> friendTable = new ArrayList<FriendTable>();
        String selectQuery = "SELECT  * FROM " + TABLE_FRIEND;

        Log.e(LOG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                FriendTable friend = new FriendTable();
                friend.setId(c.getInt((c.getColumnIndex(KEY_ID))));
                friend.setUserID(c.getInt((c.getColumnIndex(KEY_USER_ID))));
                friend.setFriendID(c.getInt((c.getColumnIndex(KEY_FRIEND_ID))));
                friend.setPost(c.getInt((c.getColumnIndex(KEY_FRIEND_POST))));

                // adding to USER list
                friendTable.add(friend);
            } while (c.moveToNext());
        }
        db.close();
        return friendTable;
    }
    public long addFriend(long user_id, long friend_id) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_USER_ID,user_id);
        values.put(KEY_FRIEND_ID, friend_id);
        values.put(KEY_FRIEND_POST, 0);

        long id = db.insert(TABLE_FRIEND, null, values);

        ContentValues values2 = new ContentValues();
        values.put(KEY_USER_ID,friend_id);
        values.put(KEY_FRIEND_ID, user_id);
        values.put(KEY_FRIEND_POST, 0);

        db.insert(TABLE_FRIEND, null, values2);
        db.close();
        return id;
    }
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
    public void deleteFriend(int user, int friend) {
        List<Integer> pair = getFriendPair(user, friend);
        SQLiteDatabase db = this.getWritableDatabase();
        for (int i:pair) {
            db.delete(TABLE_FRIEND, KEY_ID + " = ?",
                    new String[]{String.valueOf(i)});
        }
        db.close();
    }
//    public void deleteFriend(long friend_id) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        db.delete(TABLE_FRIEND, KEY_ID + " = ?",
//                new String[] { String.valueOf(friend_id) });
//        db.close();
//    }

    /*
     * Checks the current user against the database to see if they are
     * already registered.
     *
     * @param user the name of the user to check if they are already
     * registered.
     *
     * @return true if the user is registered, otherwise false
     *
     */
//    public boolean isRegistered(String user) {
//        boolean res = false;
//        SQLiteDatabase db = this.getReadableDatabase();
//
//        Cursor cursor = db.query(TABLE_USERS, new String[] {
//            KEY_USER, KEY_PASS }, KEY_USER + "=?",
//            new String[] {user}, null, null, null, null);
//
//        res = cursor.moveToFirst();
//        return res;
//    }
    /*
     *  Returns the all of users and passwords 
     *  registered with the app as a hashmap.
     *  
     *  @return a hashmap of users and their passwords
     *
     */
//    public HashMap<String, String> getAllUsers(){
//        HashMap<String, String> userList = new HashMap<String, String>();
//        String grabQuery = "SELECT * FROM " + TABLE_USERS;
//
//        SQLiteDatabase db = this.getWritableDatabase();
//        Cursor cursor = db.rawQuery(grabQuery, null);
//
//        if(cursor.moveToFirst()) {
//            do {
//                userList.put(cursor.getString(0),cursor.getString(1));
//            } while(cursor.moveToNext());
//        }
//
//        return userList;
//    }

}
