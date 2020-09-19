package com.example.finalyearprojectu.missingPersonManagement.missingPersonDeatail;

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
import android.widget.ImageView;
import android.widget.TextView;

import com.example.finalyearprojectu.R;
import com.squareup.picasso.Picasso;

public class MissingPersonDetail extends AppCompatActivity {
 TextView missingPersonName;
 TextView missingPersonBelongingCity;
 TextView missingFromCity;
 TextView missingStatus;
 TextView missingAge;
 TextView missingGender;
 TextView missingDate;
 TextView missingFullAdress;
 ImageView profileImage;
 Button  callBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_missing_person_detail);
        getSupportActionBar().setTitle("Post Detail");
        missingPersonName = findViewById(R.id.missing_person_name);
        missingPersonBelongingCity = findViewById(R.id.belonging_city);
        missingFromCity = findViewById(R.id.missing_from_city);
        missingStatus = findViewById(R.id.statu_of_missing);
        missingAge = findViewById(R.id.ageOf_person);
        missingGender = findViewById(R.id.gender_of_person);
        missingDate = findViewById(R.id.missingDate_of_person);
        missingFullAdress = findViewById(R.id.full_address_of_person);
        profileImage = findViewById(R.id.profile_image);
        callBtn = findViewById(R.id.call_btn);
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String imageUrl = intent.getStringExtra("imageUrl");
        String mBelonging = intent.getStringExtra("FromCity");
        String mFrom = intent.getStringExtra("MissingFrom");
        String mStatus = intent.getStringExtra("status");
        String mAge = intent.getStringExtra("age");
        String mGender = intent.getStringExtra("gender");
        String mDate = intent.getStringExtra("date");
        String mFullAdress = intent.getStringExtra("fullAddress");
        String mCall = intent.getStringExtra("phoneNumber");

        missingPersonName.setText(name);
        missingPersonBelongingCity.setText(mBelonging);
        missingFromCity.setText(mFrom);
        missingStatus.setText(mStatus);
        missingAge.setText(mAge);
        missingGender.setText(mGender);
        missingDate.setText(mDate);
        missingFullAdress.setText(mFullAdress);
        Picasso.get().load(imageUrl).into(profileImage);
        callBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            //this method will be called for calling purposes
                onCall(mCall);
            }
        });

    }
    public void onCall(String mCallIntent) {
        int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE);

        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                    this,
                    new String[]{Manifest.permission.CALL_PHONE},
                    Integer.parseInt("123"));
        }
        else {
            startActivity(new Intent(Intent.ACTION_CALL).setData(Uri.parse("tel:"+mCallIntent)));
        }
    }
}