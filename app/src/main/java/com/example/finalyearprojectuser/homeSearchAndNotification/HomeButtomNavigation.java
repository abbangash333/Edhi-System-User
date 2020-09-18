package com.example.finalyearprojectuser.homeSearchAndNotification;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.finalyearprojectuser.R;
import com.example.finalyearprojectuser.home.homedashboardslider.HomeDashBoardSlider;
import com.example.finalyearprojectuser.homeSearchAndNotification.homeButtomNavigation.bloodPostRecycleView.Fragment_bottom_blood_posts;
import com.example.finalyearprojectuser.homeSearchAndNotification.homeButtomNavigation.missingPersonPostR.Fragment_bottom_missing_posts;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class HomeButtomNavigation extends AppCompatActivity {
    private ActionBar toolbar;
    private FloatingActionButton floatingActionButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_buttom_navigation);
        toolbar = getSupportActionBar();
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        //this method is called when we click on tab
        bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        //this will load the Missing tab
        loadMissingTab();

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
//                case R.id.notifications_post_menu:
//                    toolbar.setTitle("Notifications");
//                    fragment = new Fragment_bottom_notifications_posts();
//                    loadFragment(fragment);
//                    return true;
            }
            return false;
        }
    };
 // this will load the fragment
    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.bottom_navigation_frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
   //load missing person data
    void  loadMissingTab()
    {
        Fragment fragment = new Fragment_bottom_missing_posts();
        toolbar.setTitle("Missing Persons Post");
        loadFragment(fragment);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(), HomeDashBoardSlider.class);
        startActivity(intent);
        finish();
    }
}
