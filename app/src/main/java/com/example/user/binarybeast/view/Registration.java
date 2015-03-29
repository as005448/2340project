package com.example.user.binarybeast.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.user.binarybeast.R;
import com.example.user.binarybeast.model.UserData;

import java.util.NoSuchElementException;

/**
 * @author Yan Chen
 * @version 1.0
 */
public class Registration extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        //this.accounts = new UserData(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_registration, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    /*
     *  Cancel registration and return to welcome page
     *
     *  @param view the view of current activity
     *
     */
    @SuppressWarnings("UnusedParameters")
    public void rCancel(View view){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
    /*
     *  submit registration information
     *
     *  @param view the view of current activity
     */
    @SuppressWarnings("UnusedParameters")
    public void submit(View view){
        EditText usernameEntry = (EditText) findViewById(R.id.r_userName);
        EditText passwordEntry = (EditText) findViewById(R.id.r_passWord);
        EditText vPasswordEntry = (EditText) findViewById(R.id.r_verifyPass);
        EditText nameEntry = (EditText) findViewById(R.id.log_name);
        EditText emailEntry = (EditText) findViewById(R.id.log_email);
        String username = usernameEntry.getText().toString();
        String password = passwordEntry.getText().toString();
        String vPassword = vPasswordEntry.getText().toString();
        String name = nameEntry.getText().toString();
        String email = emailEntry.getText().toString();
        if (username.equals("") || password.equals("") || name.equals("") || email.equals("")) {
            //warning if there are information not fill out
            Toast.makeText(Registration.this, "Please fill out all information", Toast.LENGTH_LONG).show();
        } else {
            if (vPassword.equals(password)) {
                //passwords have to match
                if (MainActivity.helper.findUser(username, "user") != null) {
                    //check if username has already been used
                    Toast.makeText(Registration.this, "The username has been used", Toast.LENGTH_LONG).show();
                } else {
                    //add new user and jump to main page
                    MainActivity.helper.addUser(username, password, name, email);
                    Toast.makeText(Registration.this, "You made it!", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(this, MainActivity.class);
                    startActivity(intent);
                }
            } else {
                Toast.makeText(Registration.this, "Password does not match.", Toast.LENGTH_LONG).show();
            }
        }
    }
}
