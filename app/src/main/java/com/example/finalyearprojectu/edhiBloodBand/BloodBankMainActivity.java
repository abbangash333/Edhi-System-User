package com.example.finalyearprojectu.edhiBloodBand;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.widget.FrameLayout;

import com.example.finalyearprojectu.R;
import com.google.android.material.tabs.TabLayout;

public class BloodBankMainActivity extends AppCompatActivity {
    TabLayout tabLayoutForBloodBank;
    FrameLayout bloodBankFrameLayout;
    Fragment fragment = null;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blood_bank_main);
        getSupportActionBar().setTitle("Blood Blank");
        tabLayoutForBloodBank = findViewById(R.id.blood_bank_TabLayout);
        bloodBankFrameLayout = findViewById(R.id.blood_bank_framelayout);
        fragment = new Donate();
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.blood_bank_framelayout, fragment);
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        fragmentTransaction.commit();
        tabLayoutForBloodBank.addOnTabSelectedListener(new TabLayout.BaseOnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Fragment fragment =null;
                switch (tab.getPosition()) {
                    case 0:
                        fragment = new Donate();
                        break;
                    case 1:
                        fragment = new Request();
                        break;
                    case 2:
                        fragment = new BloodPosts();
                        break;
                }
                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.blood_bank_framelayout, fragment);
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                ft.commit();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

}
