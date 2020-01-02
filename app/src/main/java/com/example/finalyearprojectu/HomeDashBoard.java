package com.example.finalyearprojectu;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import com.example.finalyearprojectu.edhiBloodBand.BloodBankMainActivity;
import com.example.finalyearprojectu.missingPersonManagement.MissingPersonMain;
import com.example.finalyearprojectu.centersContactInformation.ContactCenters;

public class HomeDashBoard extends AppCompatActivity {
 private CardView ambulance_cardView;
 private CardView bloodbankCardView;
 private CardView missingPersonCardView;
 private CardView centersContactDetail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_dash_board);

        ambulance_cardView = findViewById(R.id.ambulance_card_view);
        bloodbankCardView = findViewById(R.id.edhi_blood_bank_cardView);
        missingPersonCardView = findViewById(R.id.missing_person_cardView);
        centersContactDetail = findViewById(R.id.contactCenter_view);
        ambulance_cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent jump_to_ambulanceActivity = new Intent(HomeDashBoard.this,AmbulanceActivity.class);
                startActivity(jump_to_ambulanceActivity);
                Toast.makeText(getApplicationContext(),"ambulances activity started",Toast.LENGTH_SHORT).show();
            }
        });
        bloodbankCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent jumpToBloodBankcActivity = new Intent(HomeDashBoard.this,BloodBankMainActivity.class);
                startActivity(jumpToBloodBankcActivity);
            }
        });
        missingPersonCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent jumpToMissingPersonManagementActivity = new Intent(HomeDashBoard.this, MissingPersonMain.class);
                startActivity(jumpToMissingPersonManagementActivity);

            }
        });
        centersContactDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent jump_to_centerContactDetail = new Intent( HomeDashBoard.this, ContactCenters.class);
                startActivity(jump_to_centerContactDetail);
            }
        });

    }
}
