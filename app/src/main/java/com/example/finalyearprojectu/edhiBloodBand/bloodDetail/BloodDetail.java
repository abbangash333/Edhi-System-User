package com.example.finalyearprojectu.edhiBloodBand.bloodDetail;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.finalyearprojectu.R;

public class BloodDetail extends AppCompatActivity {
    private TextView bloodGroupTxt;
    private TextView bloodFor;
    private TextView bloodType;
    private TextView location;
    private TextView requestType;
    private TextView ageForBlood;
    private TextView genderForBlood;
    private TextView fullAddressForBlood;
    private Button callButtonForBlood;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blood_detail);
        getSupportActionBar().setTitle("Blood Post Detail");
        bloodGroupTxt = findViewById(R.id.blood_group_txt);
        bloodFor = findViewById(R.id.blood_for_name);
        bloodType = findViewById(R.id.blood_type_txt);
        location = findViewById(R.id.location_txt);
        requestType = findViewById(R.id.request_txt);
        ageForBlood = findViewById(R.id.age_of_person_txt);
        genderForBlood = findViewById(R.id.gender_for_blood_txt);
        fullAddressForBlood = findViewById(R.id.full_address_of_person_txt);
        callButtonForBlood = findViewById(R.id.blood_poster_call_btn);

        Intent intent = getIntent();
        //these will take data from recycle view
        String bloodgroup = intent.getStringExtra("blood");
        String bloodForM = intent.getStringExtra("bloodFor");
        String locationN = intent.getStringExtra("location");
        String requestB = intent.getStringExtra("request");
        String ageB = intent.getStringExtra("age");
        String gender = intent.getStringExtra("gender");
        String fullAddress = intent.getStringExtra("fullAddress");
        String phoneNumber = intent.getStringExtra("phoneNumber");

        //these will show data in user interface
        bloodGroupTxt.setText(bloodgroup);
        bloodFor.setText(bloodForM);
        bloodType.setText(bloodgroup);
        location.setText(locationN);
        requestType.setText(requestB);
        ageForBlood.setText(ageB);
        genderForBlood.setText(gender);
        fullAddressForBlood.setText(fullAddress);
        callButtonForBlood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // will prompt the user for calling
                callBloodPoster(phoneNumber);
            }
        });

    }

    private void callBloodPoster(String phoneNumber) {
        int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE);

        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                    this,
                    new String[]{Manifest.permission.CALL_PHONE},
                    Integer.parseInt("123"));
        }
        else {
            startActivity(new Intent(Intent.ACTION_CALL).setData(Uri.parse("tel:"+phoneNumber)));
        }
    }
}