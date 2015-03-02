package com.example.user.binarybeast.view;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.user.binarybeast.R;
import com.example.user.binarybeast.model.Interest;

import java.util.ArrayList;
import java.util.List;
/**
 * Created by Yan on 2015/2/24.
 * @author Yan Chen
 * @version 1.0
 */
public class InterestListActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interest_list);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        initListView();
    }
    /**
     * initialize interest list to show all interest of the user
     */
    private void initListView() {
        List<Interest> interests = MainActivity.helper.getInterestListByOwner(MainActivity.helper.currUser.getId());
        List<String> interestNames = new ArrayList<>();
        //get all interest information
        for (Interest i:interests) {
            interestNames.add("Name: " + i.getName() + "  Category: " + i.getCategory() + "  Price: " + i.getPrice());
        }
        //set up listview adapter
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, interestNames);
        ListView interestListView = (ListView) findViewById(R.id.interestList);
        interestListView.setAdapter(adapter);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_interest_list, menu);
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
