package com.example.finalyearprojectuser.homeSearchAndNotification.homeButtomNavigation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.finalyearprojectuser.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeButtomNavigation extends AppCompatActivity {
    private ActionBar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_buttom_navigation);
        toolbar = getSupportActionBar();
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        toolbar.setTitle("Missing Person Posts");

    }
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment;
            switch (item.getItemId()) {
                case R.id.miising_post_menu:
                    toolbar.setTitle("Missing Person Posts");
                    fragment = new Fragment_bottom_missing_posts();
                    loadFragment(fragment);
                    return true;
                case R.id.blood_post_menu:
                    toolbar.setTitle("Blood Posts");
                    fragment = new Fragment_bottom_blood_posts();
                    loadFragment(fragment);
                    return true;
                case R.id.notifications_post_menu:
                    toolbar.setTitle("Notifications");
                    fragment = new Fragment_bottom_notifications_posts();
                    loadFragment(fragment);
                    return true;
            }
            return false;
        }
    };

    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.bottom_navigation_frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
