package com.example.user.binarybeast.view;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.user.binarybeast.R;
/**
 * Created by Yan on 2015/2/24.
 * @author Yan Chen
 * @version 1.0
 */
public class InterestAddActivity extends ActionBarActivity {
    EditText itemName;
    EditText category;
    EditText price;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interest_add);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        itemName = (EditText) findViewById(R.id.itemName);
        category = (EditText) findViewById(R.id.category);
        price = (EditText) findViewById(R.id.price);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_interest_add, menu);
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
     * submit and add interest
     * @param view current view
     */
    public void submitInterest(View view) {
        String iName = itemName.getText().toString();
        String cName = category.getText().toString();
        String pName = price.getText().toString();

        if (MainActivity.helper.addInterest(iName, cName, pName)) {
            Toast.makeText(InterestAddActivity.this, "Interest added", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(InterestAddActivity.this, "Add Interest fail", Toast.LENGTH_LONG).show();
        }
    }
}
