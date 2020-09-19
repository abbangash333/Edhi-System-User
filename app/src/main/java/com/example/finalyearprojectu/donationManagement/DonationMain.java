package com.example.finalyearprojectu.donationManagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.finalyearprojectu.R;

public class DonationMain extends AppCompatActivity {
 private Button donateItemBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donation_main);
        getSupportActionBar().setTitle("Make Donation");
        donateItemBtn = findViewById(R.id.donate_item_btn);
        donateItemBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent jump_to_item_detail = new Intent(DonationMain.this,ItemDonation.class);
                startActivity(jump_to_item_detail);
            }
        });


    }
}
