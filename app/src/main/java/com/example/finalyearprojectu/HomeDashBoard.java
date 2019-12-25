package com.example.finalyearprojectu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class HomeDashBoard extends AppCompatActivity {
 private CardView ambulance_cardView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_dash_board);
        ambulance_cardView = findViewById(R.id.ambulance_card_view);
        ambulance_cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent jump_to_ambulanceActivity = new Intent(HomeDashBoard.this,AmbulanceActivity.class);
                startActivity(jump_to_ambulanceActivity);
                Toast.makeText(getApplicationContext(),"ambulances activity started",Toast.LENGTH_SHORT).show();
            }
        });

    }
}
