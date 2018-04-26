package com.example.android.sampleproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class FlagActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flag);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        String URL = extras.getString("FlagURL");
        ImageView flagImage = (ImageView) findViewById(R.id.image);
        Picasso.with(this).load(URL).into(flagImage);
    }
}
