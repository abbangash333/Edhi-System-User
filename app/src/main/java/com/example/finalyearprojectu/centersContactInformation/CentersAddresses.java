package com.example.finalyearprojectu.centersContactInformation;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.finalyearprojectu.R;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class CentersAddresses extends AppCompatActivity {
    private TextView centerN;
    private EditText phoneNumber;
    private EditText cAddress;
    private Button callToCenter;
    private String callStoreVarial;
    private  int REQUEST_CALL =1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_centers_addresses);
        getSupportActionBar().setTitle("Center Address");
        centerN = findViewById(R.id.centerName);
        phoneNumber = findViewById(R.id.center_number);
        cAddress = findViewById(R.id.center_address);
        callToCenter = findViewById(R.id.call_to_center);
        disAbleEditText();
        Intent intent = getIntent();
        String name =intent.getStringExtra("name");
        String phone =intent.getStringExtra("phone");
        String address =intent.getStringExtra("address");
        centerN.setText(name);
        phoneNumber.setText(phone);
        cAddress.setText(address);
        callStoreVarial = phone;

        callToCenter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makePhoneCall();
            }
        });
    }

    private void disAbleEditText() {
        phoneNumber.setFocusableInTouchMode(false);
        phoneNumber.setFocusable(false);
        cAddress.setFocusableInTouchMode(false);
        cAddress.setFocusable(false);
    }

    private void makePhoneCall() {
        String number = callStoreVarial;
        if (number.trim().length() > 0) {

            if (ContextCompat.checkSelfPermission(CentersAddresses.this,
                    Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(CentersAddresses.this,
                        new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL);
            } else {
                String dial = "tel:" + number;
                startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dial)));
            }

        } else {
            Toast.makeText(CentersAddresses.this, "Enter Phone Number", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_CALL) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                makePhoneCall();
            } else {
                Toast.makeText(this, "Permission DENIED", Toast.LENGTH_SHORT).show();
            }
        }
    }

}
