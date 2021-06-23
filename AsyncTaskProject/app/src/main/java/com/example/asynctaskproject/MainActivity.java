package com.example.asynctaskproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

// My image link: https://user-images.githubusercontent.com/78169473/123000819-08170b00-d37e-11eb-8bb1-cf42535431ac.jpg
public class MainActivity extends AppCompatActivity {

    private TextView mLoadText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mLoadText=findViewById(R.id.tv_loading);
    }


    public void loadImg(View view) {
        String url = "https://user-images.githubusercontent.com/78169473/123000819-08170b00-d37e-11eb-8bb1-cf42535431ac.jpg";

        new ImageDownloader(com.example.asynctaskproject.MainActivity.this).execute(url);
    }
}