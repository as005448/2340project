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
import android.content.Context;

import java.util.HashMap;


public class Registration extends Activity {
    public static UserData accounts = new UserData();

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
    public void rCancel(View view){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
    public void submit(View view){
        EditText usernameEntry = (EditText) findViewById(R.id.r_userName);
        EditText passwordEntry = (EditText) findViewById(R.id.r_passWord);
        EditText vPasswordEntry = (EditText) findViewById(R.id.r_verifyPass);
        String username = usernameEntry.getText().toString();
        String password = passwordEntry.getText().toString();
        String vPassword = vPasswordEntry.getText().toString();
        if (vPassword.equals(password)) {
            if (accounts.existAccount(username)) {
                Toast.makeText(Registration.this, "The username has been used", Toast.LENGTH_LONG).show();
            } else {
                accounts.addAccount(username, password);
                Toast.makeText(Registration.this, "You made it!", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(this,MainActivity.class);
                startActivity(intent);
            }
        } else {
            Toast.makeText(Registration.this,"Password does not match.", Toast.LENGTH_LONG).show();
        }
    }
}
