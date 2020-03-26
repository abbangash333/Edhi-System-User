package com.example.finalyearprojectu.logIn;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.finalyearprojectu.R;

public class LogInActivity extends AppCompatActivity {
    private Button mGenerateBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in2);
        getSupportActionBar().hide();
        mGenerateBtn = findViewById(R.id.generate_btn);
        mGenerateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LogInActivity.this,OtpActivity.class);
                startActivity(i);
            }
        });
    }
}
