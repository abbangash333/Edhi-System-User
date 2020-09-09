package com.example.finalyearprojectuser.logIn;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.finalyearprojectuser.R;
import com.example.finalyearprojectuser.homedashboardslider.HomeDashBoardSlider;
import com.example.finalyearprojectuser.signUp.Sign_up;

public class LogIn extends AppCompatActivity {
    private TextView dontHaveAccount;
     private Button signInButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        getSupportActionBar().setTitle("Log In");
        dontHaveAccount = findViewById(R.id.dont_have_account);
        signInButton = findViewById(R.id.signIn_Button);
        dontHaveAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Sign_up.class);
                startActivity(intent);
            }
        });
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), HomeDashBoardSlider.class);
                startActivity(intent);
            }
        });

    }
}
