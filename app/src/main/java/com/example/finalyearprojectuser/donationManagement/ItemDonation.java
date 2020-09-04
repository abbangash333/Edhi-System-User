package com.example.finalyearprojectuser.donationManagement;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.finalyearprojectuser.R;

public class ItemDonation extends AppCompatActivity {
    private Button sumitDetailOfItem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_donation);
        getSupportActionBar().setTitle("Item Detail");
        sumitDetailOfItem = findViewById(R.id.submit_item_detail);
        sumitDetailOfItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Detail submitted",Toast.LENGTH_SHORT)
                        .show();
            }
        });

    }
}
