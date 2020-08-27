package com.example.finalyearprojectu.updateProfile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.finalyearprojectu.homeDashBoard.HomeDashBoard;
import com.example.finalyearprojectu.R;

public class ProfileUpdateMain extends AppCompatActivity {
  private Button updateProfileButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_update_main);
        getSupportActionBar().setTitle("Change Profile");
        updateProfileButton = findViewById(R.id.update_profile_btn);
        updateProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Your Profile Updated",Toast.LENGTH_SHORT).show();
                BackToMain();
            }
        });
    }

    private void BackToMain() {
        Intent backTomHomeDashBoard = new Intent(ProfileUpdateMain.this, HomeDashBoard.class);
        startActivity(backTomHomeDashBoard);
    }
}
