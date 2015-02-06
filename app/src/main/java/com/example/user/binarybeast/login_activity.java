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

import java.util.HashMap;


public class login_activity extends Activity {

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
        if (Registration.accounts.existAccount(username)) {
            if (Registration.accounts.verifyAccount(username, password)) {
                //start activity, say you logged in, whatevers
                Intent intent = new Intent(this, HomePage.class);
                startActivity(intent);
            } else {
                Toast.makeText(login_activity.this, "Password incorrect!", Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(login_activity.this, "Username does not exist!", Toast.LENGTH_LONG).show();
        }
    }
    public void cancelLogin(View view){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}
