package com.example.user.binarybeast;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.user.binarybeast.helper.Helper;
import com.example.user.binarybeast.model.UserData;

import java.util.HashMap;
import java.util.NoSuchElementException;


public class login_activity extends Activity {
    public static Helper helper = new Helper();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_activity);
    }

    public void authenticateUser(View view) {
        EditText usernameEntry = (EditText) findViewById(R.id.usernameText);
        EditText passwordEntry = (EditText) findViewById(R.id.passwordText);
        String username = usernameEntry.getText().toString();
        String password = passwordEntry.getText().toString();
//        if (Registration.accounts.existAccount(username)) {
//            if (Registration.accounts.verifyAccount(username, password)) {
//                //start activity, say you logged in, whatevers
//                Registration.accounts.setCurrentUser(username);
//                Intent intent = new Intent(this, HubActivity.class);
//                startActivity(intent);
//            } else {
//                Toast.makeText(login_activity.this, "Password incorrect!", Toast.LENGTH_LONG).show();
//            }
//        } else {
//            Toast.makeText(login_activity.this, "Username does not exist!", Toast.LENGTH_LONG).show();
//        }
        try {
            if (helper.login(username, password)) {
                Intent intent = new Intent(this, HubActivity.class);
                startActivity(intent);
            } else {
                Toast.makeText(login_activity.this, "Password does not match.", Toast.LENGTH_LONG).show();
            }
        } catch (NoSuchElementException e) {
            Toast.makeText(login_activity.this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
    public void cancelLogin(View view){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}
