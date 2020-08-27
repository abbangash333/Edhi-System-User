package com.example.finalyearprojectu.signUp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.finalyearprojectu.R;

public class sign_up extends AppCompatActivity {
    private ImageView newProfilePicture;
    private final int PICK_IMAGE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        getSupportActionBar().setTitle("Sign Up");
        newProfilePicture = findViewById(R.id.take_profile_picture_fromGallery);
        newProfilePicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent chooseProfilePictureFromGallery = new Intent(Intent.ACTION_GET_CONTENT);
                chooseProfilePictureFromGallery.setType("image/*");
                if(chooseProfilePictureFromGallery.resolveActivity(getApplicationContext().getPackageManager())!=null)
                {
                    startActivityForResult(Intent.createChooser(chooseProfilePictureFromGallery, "Select Picture"), PICK_IMAGE);
                }

            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

    }
}
