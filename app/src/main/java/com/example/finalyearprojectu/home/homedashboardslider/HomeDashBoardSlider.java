package com.example.finalyearprojectu.home.homedashboardslider;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.example.finalyearprojectu.R;
import com.example.finalyearprojectu.ambulance.AmbulanceActivity;
import com.example.finalyearprojectu.centerManagement.CenterManagementSearch;
import com.example.finalyearprojectu.centersContactInformation.ContactCenters;
import com.example.finalyearprojectu.donationManagement.DonationMain;
import com.example.finalyearprojectu.home.bloodPostRecycleV.BloodPostR;
import com.example.finalyearprojectu.home.bloodPostRecycleV.BloodPostsAdapter;
import com.example.finalyearprojectu.home.missingRecycleView.MissingAdapterR;
import com.example.finalyearprojectu.home.missingRecycleView.MissingPersonR;
import com.example.finalyearprojectu.homeSearchAndNotification.HomeButtomNavigation;
import com.example.finalyearprojectu.myBloodPost.MyBloodPost;
import com.example.finalyearprojectu.myMissingPost.MyMissingPost;
import com.example.finalyearprojectu.signUp.Sign_up;
import com.example.finalyearprojectu.updateProfile.ProfileUpdateMain;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class HomeDashBoardSlider extends AppCompatActivity implements View.OnClickListener {
    private ViewFlipper simpleViewFlipper;
    // array of images
    int[] images = {R.drawable.flipperimage1, R.drawable.flipperimage2};
    public static final String[] names = {"Breaking Bad", "Rick and Morty"};
    RecyclerView recyclerViewMisingImage;
    RecyclerView recyclerViewBloodPostHome;
    MissingAdapterR missingAdapterR;
    BloodPostsAdapter bloodPostsAdapter;
    ArrayList<MissingPersonR> missingPersonRArrayList;
    List<BloodPostR> bloodPostHomeList;
    FirebaseDatabase firebaseDatabaseM;
    DatabaseReference databaseReferenceM;
    FirebaseDatabase firebaseDatabaseB;
    DatabaseReference databaseReferenceB;
    ProgressDialog progressBarHomeLoading;
    private int backpress;
    private ImageButton homeActivityBtn;
    private ImageButton ambulanceActivityBtn;
    private ImageButton bloodBankActivityBtn;
    private ImageButton missingPersonActivityBtn;
    private ImageButton contactCenterActivityBtn;
    private ImageButton donateActivityBtn;
    private ImageButton centerInformationActivityBtn;
    private ImageButton editProfileBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homedashboardslider);
        getSupportActionBar().setTitle("EDHI Welfare Trust");
        //checkUserProfile();
        progressBarHomeLoading = new ProgressDialog(HomeDashBoardSlider.this);
        progressBarHomeLoading.show();
        progressBarHomeLoading.setContentView(R.layout.progress_br);
        progressBarHomeLoading.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        startProgressBar();
        simpleViewFlipper = findViewById(R.id.frontPageViewFlipper); // get the reference of ViewFlipper
        recyclerViewMisingImage = findViewById(R.id.recent_missing_recycleView);
        recyclerViewBloodPostHome = findViewById(R.id.recent_blood_posts_home);
        firebaseDatabaseM = FirebaseDatabase.getInstance();
        databaseReferenceM = FirebaseDatabase.getInstance().getReference("missing_requests");
        homeActivityBtn = findViewById(R.id.home_btn);
        ambulanceActivityBtn = findViewById(R.id.ambulance_btn);
        bloodBankActivityBtn = findViewById(R.id.blood_bank_btn);
        missingPersonActivityBtn = findViewById(R.id.missing_dtl_btn);
        contactCenterActivityBtn = findViewById(R.id.contact_center_btn);
        donateActivityBtn = findViewById(R.id.donate_btn);
        centerInformationActivityBtn = findViewById(R.id.center_detail_btn);
        editProfileBtn = findViewById(R.id.edit_profile_btn);
        // this will check the user profile
        RecyclerView.LayoutManager recycleManager = new LinearLayoutManager(HomeDashBoardSlider.this);

        // loop for creating ImageView's
        for (int i = 0; i < images.length; i++) {
            // create the object of ImageView
            ImageView imageView = new ImageView(this);
            imageView.setImageResource(images[i]); // set image in ImageView
            simpleViewFlipper.addView(imageView); // add the created ImageView in ViewFlipper
            missingPersonRArrayList = new ArrayList<>();
            bloodPostHomeList = new ArrayList<>();
        }
        // Declare in and out animations and load them using AnimationUtils class
        Animation in = AnimationUtils.loadAnimation(this, android.R.anim.slide_in_left);
        Animation out = AnimationUtils.loadAnimation(this, android.R.anim.slide_out_right);
        // set the animation type's to ViewFlipper
        simpleViewFlipper.setInAnimation(in);
        simpleViewFlipper.setOutAnimation(out);
        // set interval time for flipping between views
        simpleViewFlipper.setFlipInterval(3000);
        // set auto start for flipping between views
        simpleViewFlipper.setAutoStart(true);
        recyclerViewMisingImage.setLayoutManager(recycleManager);

        //this method is used to load the missing person post into the application home screen
        loadMissingPersonPosts();
        //this method is used to load the blood posts into the application home screen
        loadBloodPosts();
        //the click listener implemented for different btn
        homeActivityBtn.setOnClickListener(this);
        ambulanceActivityBtn.setOnClickListener(this);
        bloodBankActivityBtn.setOnClickListener(this);
        missingPersonActivityBtn.setOnClickListener(this);
        contactCenterActivityBtn.setOnClickListener(this);
        donateActivityBtn.setOnClickListener(this);
        ;
        centerInformationActivityBtn.setOnClickListener(this);
        editProfileBtn.setOnClickListener(this);


    }

    private void startProgressBar() {
        Thread timer = new Thread() {
            @Override
            public void run() {
                try {
                    sleep(3000);
                    progressBarHomeLoading.dismiss();
                    super.run();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        timer.start();
    }

    @Override
    protected void onStart() {
        super.onStart();
        //this method is used to load the posts of missing person from firebase
        loadMissingPersonPosts();
        //this method is used to load the posts of blood from firebase
        loadBloodPosts();
    }
//this method will be used for loading missing person post
    private void loadMissingPersonPosts() {
        databaseReferenceM = FirebaseDatabase.getInstance().getReference("missing_requests");
        RecyclerView.LayoutManager recycleManager = new LinearLayoutManager(HomeDashBoardSlider.this);
        databaseReferenceM.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                missingPersonRArrayList.clear();
                for (DataSnapshot missingPosts : dataSnapshot.getChildren()) {
                    MissingPersonR missingPersonR = missingPosts.getValue(MissingPersonR.class);
                    missingPersonRArrayList.add(missingPersonR);

                }
                missingAdapterR = new MissingAdapterR(getApplicationContext(), missingPersonRArrayList);
                recyclerViewMisingImage.setAdapter(missingAdapterR);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        LinearLayoutManager horizontalMn = new LinearLayoutManager(HomeDashBoardSlider.this, LinearLayoutManager.HORIZONTAL, false);
        recyclerViewMisingImage.setLayoutManager(horizontalMn);
        recyclerViewMisingImage.setNestedScrollingEnabled(false);
        recyclerViewMisingImage.setItemAnimator(new DefaultItemAnimator());
    }

    private void loadBloodPosts() {
        databaseReferenceB = FirebaseDatabase.getInstance().getReference("blood_requests");
        RecyclerView.LayoutManager recycleManager = new LinearLayoutManager(HomeDashBoardSlider.this);
        databaseReferenceB.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                bloodPostHomeList.clear();
                for (DataSnapshot bloodPost : dataSnapshot.getChildren()) {
                    BloodPostR bposts = bloodPost.getValue(BloodPostR.class);
                    bloodPostHomeList.add(bposts);

                }
                bloodPostsAdapter = new BloodPostsAdapter(bloodPostHomeList, getApplicationContext());
                recyclerViewBloodPostHome.setAdapter(bloodPostsAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        LinearLayoutManager horizontalMn = new LinearLayoutManager(HomeDashBoardSlider.this, LinearLayoutManager.HORIZONTAL, false);
        recyclerViewBloodPostHome.setLayoutManager(horizontalMn);
        recyclerViewBloodPostHome.setNestedScrollingEnabled(false);
        recyclerViewBloodPostHome.setItemAnimator(new DefaultItemAnimator());
    }

    @Override
    public void onBackPressed() {
        backpress = (backpress + 1);
        Toast.makeText(getApplicationContext(), " Press Back again to Exit ", Toast.LENGTH_SHORT).show();

        if (backpress > 1) {
            this.finish();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.hom_screen_option_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.log_out: {
                FirebaseAuth.getInstance().signOut();
                ToastMessages(getApplicationContext(), "You have logOut");
                this.finishAffinity();
            }
        }
        return true;
    }

    //this method is for showing toast messages
    private void ToastMessages(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    //this method will take btn id, and will guide to other activity
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.home_btn: {
                Intent intent = new Intent(getApplicationContext(), HomeButtomNavigation.class);
                startActivity(intent);
                break;
            }
            case R.id.ambulance_btn: {
                Intent intent = new Intent(getApplicationContext(), AmbulanceActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.blood_bank_btn: {
                Intent intent = new Intent(getApplicationContext(), MyBloodPost.class);
                startActivity(intent);
                break;
            }
            case R.id.missing_dtl_btn: {
                Intent intent = new Intent(getApplicationContext(), MyMissingPost.class);
                startActivity(intent);
                break;
            }
            case R.id.donate_btn: {
                Intent intent = new Intent(getApplicationContext(), DonationMain.class);
                startActivity(intent);
                break;
            }
            case R.id.contact_center_btn: {
                Intent intent = new Intent(getApplicationContext(), ContactCenters.class);
                startActivity(intent);
                break;
            }
            case R.id.center_detail_btn: {
                Intent intent = new Intent(getApplicationContext(), CenterManagementSearch.class);
                startActivity(intent);
                break;
            }
            case R.id.edit_profile_btn: {
                Intent intent = new Intent(getApplicationContext(), ProfileUpdateMain.class);
                startActivity(intent);
                break;
            }
        }
    }
}
