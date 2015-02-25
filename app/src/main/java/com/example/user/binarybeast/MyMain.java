package com.example.user.binarybeast;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.binarybeast.view.FriendDetail_activity;

import java.util.ArrayList;
import java.util.List;


public class MyMain extends Activity implements View.OnClickListener {
    private ViewPager myViewPager;
    private PagerAdapter myAdapter;
    private List<View> myViews = new ArrayList<>();


    //TAB
    private LinearLayout myTabSales;
    private LinearLayout myTabFriends;
    private LinearLayout myTabSetting;

    private ImageButton mySalesImg;
    private ImageButton myFriendsImg;
    private ImageButton mySettingImg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_main);

        initView();

        initEvents();
    }

    private void initEvents() {
        myTabSales.setOnClickListener(this);
        myTabFriends.setOnClickListener(this);
        myTabSetting.setOnClickListener(this);

        myViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i2) {

            }

            @Override
            public void onPageSelected(int i) {
                int currentItem =  myViewPager.getCurrentItem();
                resetImg();
                switch (currentItem) {
                    case 0:
                        mySalesImg.setImageResource(R.drawable.barbuttonicon_camera_hl);
                        break;
                    case 1:
                        myFriendsImg.setImageResource(R.drawable.contacts_add_friend_hl);
                        break;
                    case 2:
                        mySettingImg.setImageResource(R.drawable.barbuttonicon_set_hl);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }

    private void initView(){
        myViewPager = (ViewPager)findViewById(R.id.id_viewpager);
        //TAB
        myTabSales = (LinearLayout) findViewById(R.id.id_tab_sales);
        myTabFriends = (LinearLayout) findViewById(R.id.id_tab_friends);
        myTabSetting = (LinearLayout) findViewById(R.id.id_tab_setting);
        //ImageButton
        mySalesImg = (ImageButton) findViewById(R.id.id_tab_sales_img);
        myFriendsImg = (ImageButton) findViewById(R.id.id_tab_friends_img);
        mySettingImg = (ImageButton) findViewById(R.id.id_tab_setting_img);

        LayoutInflater myInflater = LayoutInflater.from(this);
        View tab01 = myInflater.inflate(R.layout.tab01, null);
        View tab02 = myInflater.inflate(R.layout.tab02, null);
        initTab2(tab02);
        View tab03 = myInflater.inflate(R.layout.tab03, null);

        myViews.add(tab01);
        myViews.add(tab02);
        myViews.add(tab03);

        myAdapter = new PagerAdapter() {

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView(myViews.get(position));

            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                View view = myViews.get(position);
                container.addView(view);
                return view;
            }

            @Override
            public int getCount() {
                return myViews.size();
            }

            @Override
            public boolean isViewFromObject(View view, Object o) {
                return view == o;
            }
        };

        myViewPager.setAdapter(myAdapter);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_my_main, menu);
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

    @Override
    public void onClick(View v) {
        resetImg();
        switch (v.getId()) {
            case R.id.id_tab_sales:
                myViewPager.setCurrentItem(0);
                mySalesImg.setImageResource(R.drawable.barbuttonicon_camera_hl);
                break;
            case R.id.id_tab_friends:
                myViewPager.setCurrentItem(1);
                myFriendsImg.setImageResource(R.drawable.contacts_add_friend_hl);
                break;
            case R.id.id_tab_setting:
                myViewPager.setCurrentItem(2);
                mySettingImg.setImageResource(R.drawable.barbuttonicon_set_hl);
                break;

            default:
                break;
        }
    }

    private void resetImg() {
        mySalesImg.setImageResource(R.drawable.barbuttonicon_camera_disable);
        myFriendsImg.setImageResource(R.drawable.contacts_add_friend_dis);
        mySettingImg.setImageResource(R.drawable.barbuttonicon_set_dis);
    }
    private void initTab2(View view) {
        ArrayList<String> Friends = login_activity.helper.getFriendName();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, Friends);
        ListView friendList = (ListView) view.findViewById(R.id.tab2_listView);
        friendList.setAdapter(adapter);
        friendList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView textView = (TextView) view;
                String name = textView.getText().toString();
                Intent intent = new Intent(getInstance(), FriendDetail_activity.class);
                intent.putExtra("name", name);
                startActivity(intent);
            }
        });
    }
    private MyMain getInstance() {
        return this;
    }
    public void AddFriend(View view) {
        Intent intent = new Intent(this, FriendAdderActivity.class);
        startActivity(intent);
    }
}
