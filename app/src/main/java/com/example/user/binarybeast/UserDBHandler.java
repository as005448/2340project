package com.example.user.binarybeast;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;
import android.content.Context;
import android.content.ContentValues;

import java.util.HashMap;


/*
 *  @author Marcus Godwin
 *  @version 1.0
 *
 */
public class UserDBHandler extends SQLiteOpenHelper {
    //Static vars
    private static final int DATABASE_VERSION = 1;

    //Database name
    private static final String DATABASE_NAME = "registeredUsers";

    //table name
    private static final String TABLE_USERS = "users";

    //column names
    private static final String KEY_USER = "user";
    private static final String KEY_PASS = "pass";
    
    private static UserDBHandler instance = null;
    
    public static Context ctx = null;
    /*
     * Constructor for the database handler.
     *
     * @param context the android context creating the databasehandler
     *
     */
    private UserDBHandler(){
        super(ctx, DATABASE_NAME, null, DATABASE_VERSION);
    }
    
    /*
     * Getter for instance of UserDBHandler
     * @return the UserDBHandler
     */
    public static UserDBHandler getInstance() {
        if (instance == null)
            instance = new UserDBHandler();
        return instance;
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
        String CREATE_USERS_TABLE = "CREATE TABLE " + TABLE_USERS + "(" 
            + KEY_USER + " TEXT," + KEY_PASS + " TEXT" + ")";
        db.execSQL(CREATE_USERS_TABLE);
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
    public void addUser(String user, String pass) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_USER, user);
        values.put(KEY_PASS, pass);

        db.insert(TABLE_USERS, null, values);
        db.close();
    }
    
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
    public boolean isRegistered(String user) {
        boolean res = false;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_USERS, new String[] {
            KEY_USER, KEY_PASS }, KEY_USER + "=?",
            new String[] {user}, null, null, null, null);
        
        res = cursor.moveToFirst();
        return res;
    }
    /*
     *  Returns the all of users and passwords 
     *  registered with the app as a hashmap.
     *  
     *  @return a hashmap of users and their passwords
     *
     */
    public HashMap<String, String> getAllUsers(){
        HashMap<String, String> userList = new HashMap<String, String>();
        String grabQuery = "SELECT * FROM " + TABLE_USERS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(grabQuery, null);

        if(cursor.moveToFirst()) {
            do {
                userList.put(cursor.getString(0),cursor.getString(1));
            } while(cursor.moveToNext());
        }

        return userList;
    }

}
