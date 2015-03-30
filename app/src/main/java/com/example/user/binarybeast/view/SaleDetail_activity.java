package com.example.user.binarybeast.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.user.binarybeast.R;
import com.example.user.binarybeast.model.Sale;

public class SaleDetail_activity extends ActionBarActivity {
    private String name;
    private String location;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        name = intent.getStringExtra("name");
        setContentView(R.layout.activity_sale_detail_activity);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        initTextViews(name);
    }
    private void initTextViews(String name) {
        TextView textName = (TextView) findViewById(R.id.itemName);
        TextView textCategory = (TextView) findViewById(R.id.category);
        TextView textPrice = (TextView) findViewById(R.id.price);
        TextView textLocation = (TextView) findViewById(R.id.location);

        Sale sale = MainActivity.helper.getSaleByName(name);
        String cname = " " + name;
        String category = " " + sale.getCategory();
        String price = " " + sale.getPrice();
        location = " " + sale.getLocation();
        textName.setText(cname.toCharArray(), 0, cname.length());
        textCategory.setText(category.toCharArray(), 0, category.length());
        textPrice.setText(price.toCharArray(), 0, price.length());
        textLocation.setText(location.toCharArray(), 0, location.length());
    }

    @SuppressWarnings("UnusedParameters")
    public void goMap(View v) {
        Intent i = new Intent(this,MapsActivity.class);
        i.putExtra("location",location);
        i.putExtra("description",name);
        startActivity(i);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_sale_detail_activity, menu);
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
}
