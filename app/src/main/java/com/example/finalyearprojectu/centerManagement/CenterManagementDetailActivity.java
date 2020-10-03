package com.example.finalyearprojectu.centerManagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.finalyearprojectu.R;

public class CenterManagementDetailActivity extends AppCompatActivity {
    private TextView centerN;
    private TextView childN;
    private TextView widowN;
    private TextView orphanN;
    private TextView aGroup;
    private TextView aPositive;
    private TextView aNegative;
    private TextView aBGroup;
    private TextView oPositive;
    private TextView oNegative;
    private TextView bPositive;
    private TextView bNegative;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_center_management_detail);
        getSupportActionBar().setTitle("Center Information");
        centerN = findViewById(R.id.centerName_in_management_Activity);
        childN = findViewById(R.id.children_txt);
        widowN = findViewById(R.id.widow_txt);
        orphanN = findViewById(R.id.orphan_txt);
        aGroup = findViewById(R.id.a_group_txt);
        aPositive = findViewById(R.id.a_positive_txt);
        aNegative = findViewById(R.id.a_negative_txt);
        aBGroup = findViewById(R.id.ab_group_txt);
        oPositive = findViewById(R.id.o_positive_txt);
        oNegative = findViewById(R.id.o_negative_txt);
        bPositive = findViewById(R.id.b_positive_txt);
        bNegative = findViewById(R.id.b_negative_txt);
        Intent intent = getIntent();
        centerN.setText(intent.getStringExtra("centerName"));
        childN.setText(intent.getStringExtra("children"));
        widowN.setText(intent.getStringExtra("widow"));
        orphanN.setText(intent.getStringExtra("orphan"));
        aGroup.setText(intent.getStringExtra("a"));
        aPositive.setText(intent.getStringExtra("a+"));
        aNegative.setText(intent.getStringExtra("a-"));
        aBGroup.setText(intent.getStringExtra("ab"));
        oPositive.setText(intent.getStringExtra("o+"));
        oNegative.setText(intent.getStringExtra("o-"));
        bPositive.setText(intent.getStringExtra("b+"));
        bNegative.setText(intent.getStringExtra("b-"));


    }
}
