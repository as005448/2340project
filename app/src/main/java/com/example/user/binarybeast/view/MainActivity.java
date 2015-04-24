package com.example.user.binarybeast.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.user.binarybeast.R;
import com.example.user.binarybeast.helper.Helper;

/**
 * @author Yan Chen
 * @version 1.0
 */

@SuppressWarnings("UnusedParameters")
public class MainActivity extends Activity {
    public static Helper helper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (helper == null) {
            helper = Helper.getInstance(getApplicationContext());
            //helper = new Helper(getApplicationContext());
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
    /**
     * jump to login page
     */
    public void loginPage(View view){
        Intent intent = new Intent(this,login_activity.class);
        startActivity(intent);
    }
    /**
     * jump to register page
     */
    @SuppressWarnings("UnusedParameters")
    public void registerPage(View view) {
        Intent intent = new Intent(this,Registration.class);
        startActivity(intent);
    }

}
