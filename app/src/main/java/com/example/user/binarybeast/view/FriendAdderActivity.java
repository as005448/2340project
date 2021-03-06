package com.example.user.binarybeast.view;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.user.binarybeast.R;
import com.example.user.binarybeast.model.UserData;
/**
 * @author Yan Chen
 * @version 1.0
 */
public class FriendAdderActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_adder);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_friend_adder, menu);
        return super.onCreateOptionsMenu(menu);
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
    /**
     * add new friend
     * @param view current view
     */
    @SuppressWarnings("UnusedParameters")
    public void makeNewFriend(View view) {
        EditText friendName = (EditText) findViewById(R.id.r_friendName);
        EditText friendEmail = (EditText) findViewById(R.id.r_friendEmail);
        CheckBox shouldShare = (CheckBox) findViewById(R.id.checkBox);
        Boolean fbShare = shouldShare.isChecked();
        String name = friendName.getText().toString();
        String email = friendEmail.getText().toString();
        UserData addFriendName = MainActivity.helper.findUser(name, "name");
        UserData addFriendEmail = MainActivity.helper.findUser(email, "email");
        //add new friend
        if (MainActivity.helper.currUser.getEmail().equals(email) || MainActivity.helper.currUser.getName().equals(name)) {
            //can not add yourself as friend
            Toast.makeText(FriendAdderActivity.this, "Don't add yourself!", Toast.LENGTH_LONG).show();
        } else if (addFriendName != null) {
            //add friend by name
            if (!MainActivity.helper.addFriend(addFriendName)) {
                Toast.makeText(FriendAdderActivity.this, "the user is already your friend.", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(FriendAdderActivity.this, "friend added", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(this,MyMain.class);
                if(fbShare) {
                    Toast.makeText(FriendAdderActivity.this, "Sharing to Facebook!", Toast.LENGTH_LONG).show();
                    postFacebookFriend(view, name);
                }
                startActivity(intent);
            }
        } else if (addFriendEmail != null) {
            //add friend by email
            if (!MainActivity.helper.addFriend(addFriendEmail)) {
                Toast.makeText(FriendAdderActivity.this, "the user is already your friend.", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(FriendAdderActivity.this, "friend added", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(this,MyMain.class);
                if(fbShare) {
                    Toast.makeText(FriendAdderActivity.this, "Sharing to Facebook!", Toast.LENGTH_LONG).show();
                    postFacebookFriend(view, name);
                }
                startActivity(intent);
            }
        } else {
            Toast.makeText(FriendAdderActivity.this, "Can not find the user", Toast.LENGTH_LONG).show();
        }
    }

    public void postFacebookFriend(View view, String friendName) {

        try {
            Intent intent1 = new Intent(Intent.ACTION_SEND);
            intent1.setClassName("com.facebook.katana", "com.facebook.katana.activity.composer.ImplicitShareIntentHandler");
            intent1.setType("text/plain");
            intent1.putExtra("android.intent.extra.TEXT", "I'm now friends with " + friendName + "!!!!");
            startActivity(intent1);
        } catch (Exception e) {
            Toast.makeText(FriendAdderActivity.this, "You need to install facebook!!!!", Toast.LENGTH_LONG).show();
            // If we failed (not native FB app installed), try share through SEND
            Intent intent = new Intent(Intent.ACTION_SEND);
            String sharerUrl = "https://www.facebook.com/sharer/sharer.php?u=" + "I'm now friends with "+friendName+"!!!!";
            intent = new Intent(Intent.ACTION_VIEW, Uri.parse(sharerUrl));
            startActivity(intent);
        }
    }
}
