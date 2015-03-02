package com.example.user.binarybeast.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.user.binarybeast.R;

import java.util.NoSuchElementException;

/**
 * @author Yan Chen
 * @version 1.0
 */

public class login_activity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_activity);
    }
    /*
     *  authenticate login information
     *
     *  @param view the view of current activity
     *
     */
    public void authenticateUser(View view) {
        EditText usernameEntry = (EditText) findViewById(R.id.usernameText);
        EditText passwordEntry = (EditText) findViewById(R.id.passwordText);
        String username = usernameEntry.getText().toString();
        String password = passwordEntry.getText().toString();
        if (MainActivity.helper.findUser(username, "user") == null) {
            //check if the username does exist
            Toast.makeText(login_activity.this, "Username does not exist!", Toast.LENGTH_LONG).show();
        } else {
            //check password
            if (MainActivity.helper.login(username, password)) {
                Intent intent = new Intent(this, MyMain.class);
                startActivity(intent);
            } else {
                Toast.makeText(login_activity.this, "Password does not match.", Toast.LENGTH_LONG).show();
            }
        }
    }
    /*
     *  cancel login and return to welcome screen
     *
     *  @param view the view of current activity
     *
     */
    public void cancelLogin(View view){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}
