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
 * Created by Marcus on 2015/3/10.
 * @author Marcus Godwin
 * @version 1.0
 */
public class SaleAddActivity extends ActionBarActivity {
    private EditText itemName;
    private EditText category;
    private EditText price;
    private EditText location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sale_add);

        itemName = (EditText) findViewById(R.id.itemName);
        category = (EditText) findViewById(R.id.category);
        price = (EditText) findViewById(R.id.price);
        location = (EditText) findViewById(R.id.location);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_sale_add, menu);
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
    @SuppressWarnings("UnusedParameters")
    public void submitSale(View view) {
        String iName = itemName.getText().toString();
        String cName = category.getText().toString();
        String pName = price.getText().toString();
        String lName = location.getText().toString();

        if (MainActivity.helper.addSale(iName, cName, pName, lName)) {
            Toast.makeText(SaleAddActivity.this, "Sale added", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(SaleAddActivity.this, "Add Sale fail", Toast.LENGTH_LONG).show();
        }
    }
}
