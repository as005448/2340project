package com.example.user.binarybeast;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class FriendAdderActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_adder);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_friend_adder, menu);
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



    public void makeNewFriend(View view) {
        EditText friendName = (EditText) findViewById(R.id.r_friendName);
        EditText friendEmail = (EditText) findViewById(R.id.r_friendEmail);
        String name = friendName.getText().toString();
        String email = friendEmail.getText().toString();
        Registration.accounts.addFriend(name, email);
        Toast.makeText(FriendAdderActivity.this, "Friend Added!!!", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this, HubActivity.class);
        startActivity(intent);
    }
}
