package com.example.user.binarybeast;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.user.binarybeast.view.MainActivity;


public class HomePage extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
    }

    public void back(View view){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }

}
