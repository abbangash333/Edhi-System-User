package com.example.finalyearprojectu.homeSearchAndNotification.postblooddetail;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.finalyearprojectu.R;

public class PostBloodDetail extends AppCompatActivity implements View.OnClickListener {
    private EditText bloodFor;
    private EditText cityReference;
    private EditText fullAddress;
    private EditText age;
    private Button selectGenderBtn;
    private Button requestTypeBtn;
    private Button bloodGrupBtn;
    private Button postBloodRequest;
    private String gender;
    private String requestFor;
    private String bloodGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_blood_detail);
        bloodFor = findViewById(R.id.blood_for);
        cityReference = findViewById(R.id.reference_city);
        fullAddress = findViewById(R.id.full_address);
        age = findViewById(R.id.age_p);
        selectGenderBtn = findViewById(R.id.select_gender);
        requestTypeBtn = findViewById(R.id.select_request_type);
        bloodGrupBtn = findViewById(R.id.select_blood_g);
        postBloodRequest = findViewById(R.id.post_blood_request);
        selectGenderBtn.setOnClickListener(this);
        requestTypeBtn.setOnClickListener(this);
        bloodGrupBtn.setOnClickListener(this);
        postBloodRequest.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.select_blood_g:
            {
                openDialogBoxForSelection();
            }
        }

    }

    private void openDialogBoxForSelection() {

    }
}