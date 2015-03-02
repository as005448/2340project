package com.example.user.binarybeast.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.user.binarybeast.R;
import com.example.user.binarybeast.model.UserData;

/**
 * Created by Administrator on 2015/2/24.
 */

public class FriendDetail_activity extends ActionBarActivity {
    private TextView textName;
    private TextView textEmail;
    private TextView textRate;
    private TextView textPost;
    private UserData friend;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        setContentView(R.layout.activity_friend_detail);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        initTextViews(name);
    }

    private void initTextViews(String name) {
        textName = (TextView) findViewById(R.id.textView_disName);
        textEmail = (TextView) findViewById(R.id.textView_disEmail);
        textRate = (TextView) findViewById(R.id.textView_disRate);
        textPost = (TextView) findViewById(R.id.textView_disReport);

        friend = MainActivity.helper.findUser(name, "name");
        String rate = " " + friend.getRate();
        String post = " " + friend.getPost();
        textName.setText(name.toCharArray(), 0, name.length());
        textEmail.setText(friend.getEmail().toCharArray(), 0, friend.getEmail().length());
        textRate.setText(rate.toCharArray(), 0, rate.length());
        textPost.setText(post.toCharArray(), 0, post.length());
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_friend_detail, menu);
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
    public void deleteFriend(View view) {
        MainActivity.helper.deleteFriend(friend);
        Intent intent = new Intent(this, MyMain.class);
        startActivity(intent);
    }
}
