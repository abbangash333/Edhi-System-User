package com.example.finalyearprojectuser.centerManagement;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.finalyearprojectuser.R;

public class CenterManagementDetailActivity extends AppCompatActivity {
    private TextView centerN;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_center_management_detail);
        getSupportActionBar().setTitle("Center Information");
        centerN = findViewById(R.id.centerName_in_management_Activity);
        Bundle sCenterName = getIntent().getExtras();
        String centerName = sCenterName.getString("centerName");
        if (centerName.equals("Peshawar")) {
            centerN.setText(centerName);
        }
        if (centerName.equals("Kohat")) {
            centerN.setText(centerName);

        }
        if (centerName.equals("Hangu")) {
            centerN.setText(centerName);
        }

    }
}
