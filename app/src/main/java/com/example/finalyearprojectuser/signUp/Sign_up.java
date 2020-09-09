package com.example.finalyearprojectuser.signUp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.finalyearprojectuser.R;
import com.example.finalyearprojectuser.homedashboardslider.HomeDashBoardSlider;
import com.example.finalyearprojectuser.logIn.OtpPattern.OtpActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

public class Sign_up extends AppCompatActivity {
    private ImageView userProfilePicture;
    private EditText phonNumberForRegistration;
    private EditText userName;
    private EditText userCity;
    private Button signUpBtn;
    private final int PICK_IMAGE = 1;
    FirebaseAuth firebaseAuth;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        getSupportActionBar().setTitle("Sign Up");
        userProfilePicture = findViewById(R.id.take_profile_picture_fromGallery);
        phonNumberForRegistration = findViewById(R.id.phone_number);
        userName = findViewById(R.id.user_name);
        userCity = findViewById(R.id.user_city);
        signUpBtn = findViewById(R.id.sign_up_btn);
        userProfilePicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent chooseProfilePictureFromGallery = new Intent(Intent.ACTION_GET_CONTENT);
                chooseProfilePictureFromGallery.setType("image/*");
                if (chooseProfilePictureFromGallery.resolveActivity(getApplicationContext().getPackageManager()) != null) {
                    startActivityForResult(Intent.createChooser(chooseProfilePictureFromGallery, "Select Picture"), PICK_IMAGE);
                }

            }
        });
    }
}
