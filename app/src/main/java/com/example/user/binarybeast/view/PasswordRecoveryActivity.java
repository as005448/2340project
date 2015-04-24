package com.example.user.binarybeast.view;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.user.binarybeast.R;
import com.example.user.binarybeast.model.UserData;

import java.net.Inet4Address;

public class PasswordRecoveryActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_recovery);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_password_recovery, menu);
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

    public void cancelRecovery(View view) {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }

    public void recoverPassword(View view) {
        EditText usernameEntry = (EditText) findViewById(R.id.usernameRecovText);
        EditText emailEntry = (EditText) findViewById(R.id.emailRecovEmailText);
        String username = usernameEntry.getText().toString();
        String email = emailEntry.getText().toString();

        UserData userinfo = MainActivity.helper.findUser(username, "user");
        if(userinfo == null) {
            Toast.makeText(PasswordRecoveryActivity.this, "Username does not exist!", Toast.LENGTH_LONG).show();
        } else {
            if(!userinfo.getEmail().equals(email)) {
                Toast.makeText(PasswordRecoveryActivity.this, "Email does not match Username!", Toast.LENGTH_LONG).show();
            } else {
                Intent mailIntent = new Intent(Intent.ACTION_SEND);
                mailIntent.setType("message/rfc822");
                mailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{email});
                mailIntent.putExtra(Intent.EXTRA_SUBJECT, "Password recovery!");
                mailIntent.putExtra(Intent.EXTRA_TEXT, "Don't forget your password next time!!!\n"+userinfo.getPass());
                try {
                    startActivity(Intent.createChooser(mailIntent, "Recover password with???"));
                } catch (ActivityNotFoundException e) {
                    Toast.makeText(PasswordRecoveryActivity.this, "Error, no email app found!!!", Toast.LENGTH_LONG).show();
                }
            }
        }
        //cancelRecovery(view);
    }
}
