package com.example.finalyearprojectuser.home.homedashboardslider;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.example.finalyearprojectuser.R;
import com.example.finalyearprojectuser.home.blooPostRecycleV.BloodPostR;
import com.example.finalyearprojectuser.home.blooPostRecycleV.BloodPostsAdapter;
import com.example.finalyearprojectuser.home.missingRecycleView.MissingAdapterR;
import com.example.finalyearprojectuser.home.missingRecycleView.MissingPersonR;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.ref.PhantomReference;
import java.util.ArrayList;
import java.util.List;

public class HomeDashBoardSlider extends AppCompatActivity {
    private ViewFlipper simpleViewFlipper;
    // array of images
    int[] images = {R.drawable.flipperimage1, R.drawable.flipperimage2};
    public static final String[] names = {"Breaking Bad", "Rick and Morty"};
    RecyclerView recyclerViewMisingImage;
    RecyclerView recyclerViewBloodPostHome;
    MissingAdapterR missingAdapterR;
    BloodPostsAdapter bloodPostsAdapter;
    List<MissingPersonR> missingPersonRArrayList;
    List<BloodPostR> bloodPostHomeList;
    FirebaseDatabase firebaseDatabaseM;
    DatabaseReference databaseReferenceM;
    FirebaseDatabase firebaseDatabaseB;
    DatabaseReference databaseReferenceB;
    ProgressDialog progressBarHomeLoading;
    private int backpress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homedashboardslider);
        getSupportActionBar().setTitle("EDHI Welfare Trust");
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

    }

    private void startProgressBar() {
        Thread timer = new Thread()
        {
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

    private void loadMissingPersonPosts() {
        databaseReferenceM = FirebaseDatabase.getInstance().getReference("missing_requests");
        RecyclerView.LayoutManager recycleManager = new LinearLayoutManager(HomeDashBoardSlider.this);
        databaseReferenceM.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
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
                for (DataSnapshot bloodPost : dataSnapshot.getChildren()) {
                    BloodPostR bposts = bloodPost.getValue(BloodPostR.class);
                    bloodPostHomeList.add(bposts);

                }
                bloodPostsAdapter = new BloodPostsAdapter(bloodPostHomeList,getApplicationContext());
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

        if (backpress>1) {
            this.finish();
        }
    }
}
