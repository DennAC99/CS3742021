package com.example.menuproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    //*  MENU ITEMS *//
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //inflate menu items
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.example_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        //items from menu
        switch (item.getItemId()){
            case R.id.help_menu:
                Toast.makeText(this, "HELP CLICKED", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.settings_menu:
                Toast.makeText(this, "SETTINGS CLICKED", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void launchHelp(MenuItem item) {
        Intent intent = new Intent(this, HelpActivity.class);
        startActivity(intent);
    }
    //*  MENU ITEMS *//

    //* BUTTONS *//
    public void launchNewActivity(View view) {
        Intent intent = new Intent(this, NewActivity.class);
        startActivity(intent);
    }

    public void launchShare(View view) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TITLE, "Share the love");
        startActivity(Intent.createChooser(intent, "Share the love"));
    }

    public void launchMap(View view) {
        String birthPlace = "geo:41.38368167681544, -73.66316737323952";
        //parse the URI and make intent
        Uri addressUri = Uri.parse(birthPlace);
        Intent intent = new Intent(Intent.ACTION_VIEW, addressUri);

        try {
            startActivity(intent);
        } catch (ActivityNotFoundException e) {
            Log.d("MenuProject", "Intent does not work!");
        }
    }

    public void launchWeb(View view) {
        String url = "http://twitter.com";
        //parse the URI and make intent
        Uri webpage = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, webpage);

        try {
            startActivity(intent);
        } catch (ActivityNotFoundException e) {
            Log.d("MenuProject", "Intent does not work!");
        }
    }

    public void launchPhone(View view) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel: 9147332321"));

        try {
            startActivity(intent);
        } catch (ActivityNotFoundException e) {
            Log.d("MenuProject", "Intent does not work!");
        }
    }

    public void launchSMS(View view) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("smsto:" + Uri.encode("9147332321")));
        intent.putExtra("sms_body", "Kristina Mancini");

        try {
            startActivity(intent);
        } catch (ActivityNotFoundException e) {
            Log.d("MenuProject", "Intent does not work!");
        }
    }
    //* BUTTONS *//


}