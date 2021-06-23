package com.example.midterm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private TextView Liters;
    private TextView Gallons;

    private static DecimalFormat df4 = new DecimalFormat("#.####");

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("Gallons",Gallons.getText().toString());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Liters = findViewById(R.id.text_input);
        Gallons = findViewById(R.id.gallons_text);
        if (savedInstanceState != null) {
                Gallons.setText(savedInstanceState.getString("Gallons"));
        }
    }

    public void convertLiters(View view) {
        InputMethodManager inputManager = (InputMethodManager)
                getSystemService(Context.INPUT_METHOD_SERVICE);

        if (inputManager != null) {
            inputManager.hideSoftInputFromWindow(view.getWindowToken(),
                    InputMethodManager.HIDE_NOT_ALWAYS);
        }

        String LitersText;
        double numofL = -1;
        double numofG = 0;
        try {
            LitersText = Liters.getText().toString();
            numofL = Double.parseDouble(LitersText);

        } catch (Exception e) {
            Gallons.setText(R.string.invalid_text);
        }
        if (numofL >= 0) {
            numofG = numofL * 0.264;
            Gallons.setText(new StringBuilder().append(df4.format(numofL)).append(" Liters are \n")
                    .append(df4.format(numofG)).append(" Gallons").toString());
        } else {
            Gallons.setText(R.string.invalid_text);
        }

    }
}