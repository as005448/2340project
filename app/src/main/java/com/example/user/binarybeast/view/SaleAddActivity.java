package com.example.user.binarybeast.view;

import android.annotation.TargetApi;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
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
    private CheckBox share;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sale_add);

        itemName = (EditText) findViewById(R.id.itemName);
        category = (EditText) findViewById(R.id.category);
        price = (EditText) findViewById(R.id.price);
        location = (EditText) findViewById(R.id.location);
        share = (CheckBox) findViewById(R.id.checkBox2);
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
    @TargetApi(16)
    public void submitSale(View view) {
        String iName = itemName.getText().toString();
        String cName = category.getText().toString();
        String pName = price.getText().toString();
        String lName = location.getText().toString();

        if (MainActivity.helper.addSale(iName, cName, pName, lName)) {
            Toast.makeText(SaleAddActivity.this, "Sale added", Toast.LENGTH_LONG).show();

            /*
            ** Notifications!!!!!
             */
            NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this).setSmallIcon(R.drawable.ic_launcher).setContentTitle("Sale item added!!!").setContentText(iName + " has been placed on sale!!!");
            Intent resultIntent = new Intent(this, MyMain.class);
            TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
            stackBuilder.addParentStack(MyMain.class);
            stackBuilder.addNextIntent(resultIntent);
            PendingIntent pendInt = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
            mBuilder.setContentIntent(pendInt);
            NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.notify(0,mBuilder.build());

            /*
            ** Facebook sharing?
             */
            if(share.isChecked()) {
                postFacebookSale(view, iName);
            }


        } else {
            Toast.makeText(SaleAddActivity.this, "Add Sale fail", Toast.LENGTH_LONG).show();
        }
    }

    public void postFacebookSale(View view, String saleName) {

        try {
            Intent intent1 = new Intent(Intent.ACTION_SEND);
            intent1.setClassName("com.facebook.katana", "com.facebook.katana.activity.composer.ImplicitShareIntentHandler");
            intent1.setType("text/plain");
            intent1.putExtra("android.intent.extra.TEXT", "Hey friends, " + saleName + "is now on sale!!!!");
            startActivity(intent1);
        } catch (Exception e) {
            Toast.makeText(SaleAddActivity.this, "You need to have facebook installed to share!!!!", Toast.LENGTH_LONG).show();
            /*
            // If we failed (not native FB app installed), try share through SEND
            Intent intent = new Intent(Intent.ACTION_SEND);
            String sharerUrl = "https://www.facebook.com/sharer/sharer.php?u=" + "Hey friends, " + saleName + "is now on sale!!!!";
            intent = new Intent(Intent.ACTION_VIEW, Uri.parse(sharerUrl));
            startActivity(intent);*/
        }
    }
}
