package com.example.finalyearprojectu.myMissingPost;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.finalyearprojectu.R;

public class MyMissingPost extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_missing_post);
        getSupportActionBar().setTitle("Your Posts");
    }
}