package com.example.user.binarybeast;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.user.binarybeast.helper.Helper;
import com.example.user.binarybeast.helper.UserDBHandler;


public class MainActivity extends Activity {
    public static Helper helper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button login_button = (Button) findViewById(R.id.login_button);
        login_button.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                loginPage();
            }
        });
        if (helper == null) {
            helper = new Helper(getApplicationContext());
        }
        if(UserDBHandler.ctx == null){
            UserDBHandler.ctx = this;
        }
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    public void loginPage(){
        Intent intent = new Intent(this,login_activity.class);
        startActivity(intent);
    }

    public void registerPage(View view) {
        Intent intent = new Intent(this,Registration.class);
        startActivity(intent);
    }

}
