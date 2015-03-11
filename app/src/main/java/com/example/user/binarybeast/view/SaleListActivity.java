package com.example.user.binarybeast.view;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.user.binarybeast.R;
import com.example.user.binarybeast.model.Interest;
import com.example.user.binarybeast.model.Sale;

import java.util.ArrayList;
import java.util.List;
/**
 * Created by Marcus on 2015/3/10.
 * @author Marcus Godwin
 * @version 1.0
 */
public class SaleListActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sale_list);
        initListView();
    }

    private void initListView() {
        List<Interest> interests = MainActivity.helper.getInterestListByOwner(MainActivity.helper.currUser.getId());
        List<Sale> sales = new ArrayList<>();
        //get all valid sales for interests within my price
        for (Interest i:interests) {
            sales.addAll(MainActivity.helper.getSaleByPrice(i.getName(), i.getPrice()));
        }

        List<String> salesNames = new ArrayList<>();
        for(Sale s: sales) {
            salesNames.add("name: "+s.getName()+" price: "+s.getPrice()+" location: "+s.getLocation());
        }
        //set up listview adapter
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, salesNames);
        ListView interestListView = (ListView) findViewById(R.id.saleList);
        interestListView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_sale_list, menu);
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
