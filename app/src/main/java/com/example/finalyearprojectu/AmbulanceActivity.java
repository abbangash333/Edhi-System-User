package com.example.finalyearprojectu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class AmbulanceActivity extends AppCompatActivity {
   private Button nearestAmbulanceButton;
   private Button findMoreNearestAmbulances;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ambulance);
        nearestAmbulanceButton = findViewById(R.id.notify_nearest_driver);
        findMoreNearestAmbulances = findViewById(R.id.find_more_nearest_ambulances);
        nearestAmbulanceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Driver has been notified",Toast.LENGTH_LONG).show();
            }
        });
        findMoreNearestAmbulances.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotTonearestAmbulanceDetail = new Intent(getApplicationContext(),AmbulanceDetail.class);
                startActivity(gotTonearestAmbulanceDetail);
            }
        });

      
    }
}
