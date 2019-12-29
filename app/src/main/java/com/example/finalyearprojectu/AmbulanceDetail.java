package com.example.finalyearprojectu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class AmbulanceDetail extends AppCompatActivity {
    private RecyclerView ambulanceDetailRecycleView;
    String strName[] = {"Dr.Muneer", "Ibrahim", "Muhammad", "Rashid", "Shadman"};
    int[] imageLocation = {R.drawable.location};
    int imageDistance[] = {R.drawable.distance_icon};
    int imageMobile [] ={R.drawable.mobile_icon};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ambulance_detail);
        ambulanceDetailRecycleView = findViewById(R.id.ambulance_detail_recycle_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(AmbulanceDetail.this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        ambulanceDetailRecycleView.setLayoutManager(linearLayoutManager);
        AmbulanceDetailAdapter adapter = new AmbulanceDetailAdapter(AmbulanceDetail.this,strName, imageLocation,imageMobile,imageDistance);
        ambulanceDetailRecycleView.setAdapter(adapter);
    }
}
