package com.example.finalyearprojectu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class LogIn extends AppCompatActivity {
   private TextView dontHaveAccount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        dontHaveAccount = findViewById(R.id.dont_have_account);
        Intent intent = new Intent(getApplicationContext(),sign_up.class);
        startActivity(intent);
    }
}
