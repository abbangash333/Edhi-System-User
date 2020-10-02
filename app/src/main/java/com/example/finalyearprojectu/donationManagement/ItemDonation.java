package com.example.finalyearprojectu.donationManagement;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.finalyearprojectu.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ItemDonation extends AppCompatActivity implements View.OnClickListener {
    private Button submitDetailOfItem;
    private EditText cNumber;
    private EditText cFullAddress;
    private EditText iEstimateCost;
    private EditText itemCity;
    private FirebaseAuth fAuth;
    private FirebaseDatabase fDatabase;
    private DatabaseReference dReference;
    private String postId;
    private String number;
    private String address;
    private String estimateCost;
    private String city;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_donation);
        getSupportActionBar().setTitle("Item Detail");
        //id for submit button
        submitDetailOfItem = findViewById(R.id.item_detail_btn);
        // id for contact number
        cNumber = findViewById(R.id.item_donor_number);
        // full address id
        cFullAddress = findViewById(R.id.item_donor_address);
        // estimate cost id;
        iEstimateCost = findViewById(R.id.item_estimate_price);
        //button click event
        submitDetailOfItem.setOnClickListener(this);
        // city id
        itemCity= findViewById(R.id.item_city);
        //these will take data from User Interface


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.item_detail_btn:
                if (checkValidity() == true) {
                    uploadDetail();
                    clearAllFields();
                    finish();

                }
        }
    }


    private void uploadDetail() {
        dReference = FirebaseDatabase.getInstance().getReference("items_detail");
        postId = dReference.push().getKey();
        ItemDonationModel itemDonationModel = new ItemDonationModel(number,address,estimateCost,city);
        dReference.child(postId).setValue(itemDonationModel);
        Toast.makeText(getApplicationContext(),"Detail Submitted",Toast.LENGTH_SHORT).show();
    }

    private boolean checkValidity() {
        number = cNumber.getText().toString();
        address = cFullAddress.getText().toString();
        estimateCost = iEstimateCost.getText().toString().trim();
        city = itemCity.getText().toString().trim();

        if (number.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Please enter correct phone", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (address.isEmpty() || address.length() < 8) {
            Toast.makeText(getApplicationContext(), "Please enter correct address", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (estimateCost.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Please estimate Cost", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (city.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Please enter correct city", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
    private void clearAllFields() {
        cNumber.setText("");
        cFullAddress.setText("");
        itemCity.setText("");
        iEstimateCost.setText("");
    }
}
