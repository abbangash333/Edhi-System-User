package com.example.finalyearprojectuser.home.homeDashBoard;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.finalyearprojectuser.R;
import com.example.finalyearprojectuser.ambulance.AmbulanceActivity;
import com.example.finalyearprojectuser.homeSearchAndNotification.homeButtomNavigation.HomeButtomNavigation;
import com.example.finalyearprojectuser.updateProfile.ProfileUpdateMain;
import com.example.finalyearprojectuser.centerManagement.CenterManagementSearch;
import com.example.finalyearprojectuser.donationManagement.DonationMain;
import com.example.finalyearprojectuser.edhiBloodBand.BloodBankMainActivity;
import com.example.finalyearprojectuser.missingPersonManagement.MissingPersonMain;
import com.example.finalyearprojectuser.centersContactInformation.ContactCenters;

public class HomeDashBoard extends AppCompatActivity {
 private CardView ambulance_cardView;
 private CardView bloodbankCardView;
 private CardView missingPersonCardView;
 private CardView centersContactDetail;
 private CardView dontionManagement;
 private CardView changeProfile;
 private CardView centerDetail;
 private  CardView homePostsCardView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_dash_board);

        ambulance_cardView = findViewById(R.id.ambulance_card_view);
        bloodbankCardView = findViewById(R.id.edhi_blood_bank_cardView);
        missingPersonCardView = findViewById(R.id.missing_person_cardView);
        centersContactDetail = findViewById(R.id.contactCenter_view);
        dontionManagement  = findViewById(R.id.donation_management_cardView);
        centerDetail = findViewById(R.id.center_detail_cardView);
        changeProfile = findViewById(R.id.change_profile_id);
        homePostsCardView = findViewById(R.id.home_posts_cardView);
        ambulance_cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent jump_to_ambulanceActivity = new Intent(HomeDashBoard.this, AmbulanceActivity.class);
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
        dontionManagement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent jump_to_DoantionManagement= new Intent(HomeDashBoard.this, DonationMain.class);
                startActivity(jump_to_DoantionManagement);
            }
        });
        changeProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent jump_to_changeProfile= new Intent(HomeDashBoard.this, ProfileUpdateMain.class);
                startActivity(jump_to_changeProfile);
            }
        });
        centerDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent jump_to_centerManagementSearch= new Intent(HomeDashBoard.this, CenterManagementSearch.class);
                startActivity(jump_to_centerManagementSearch);
            }
        });
        homePostsCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent jump_to_homePosts= new Intent(HomeDashBoard.this, HomeButtomNavigation.class);
                startActivity(jump_to_homePosts);

            }
        });

    }
}
