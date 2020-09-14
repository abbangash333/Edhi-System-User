package com.example.finalyearprojectuser.home.homedashboardslider;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import com.example.finalyearprojectuser.R;
import com.example.finalyearprojectuser.home.missingRecycleView.MissingAdapterR;
import com.example.finalyearprojectuser.home.missingRecycleView.MissingPersonR;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class HomeDashBoardSlider extends AppCompatActivity {
    private ViewFlipper simpleViewFlipper;
    // array of images
    int[] images = {R.drawable.flipperimage1, R.drawable.flipperimage2};
    public static final String[] names= {"Breaking Bad","Rick and Morty"};
    RecyclerView recyclerViewMisingImage;
    MissingAdapterR missingAdapterR;
    List<MissingPersonR> missingPersonRArrayList;
    FirebaseDatabase firebaseDatabaseM;
    DatabaseReference databaseReferenceM;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homedashboardslider);
        getSupportActionBar().setTitle("EDHI Welfare Trust");
        simpleViewFlipper =  findViewById(R.id.frontPageViewFlipper); // get the reference of ViewFlipper
        recyclerViewMisingImage = findViewById(R.id.recent_missing_recycleView);
        firebaseDatabaseM = FirebaseDatabase.getInstance();
        databaseReferenceM = FirebaseDatabase.getInstance().getReference("missing_requests");
        RecyclerView.LayoutManager recycleManager= new LinearLayoutManager(HomeDashBoardSlider.this);

        // loop for creating ImageView's
        for (int i = 0; i < images.length; i++) {
            // create the object of ImageView
            ImageView imageView = new ImageView(this);
            imageView.setImageResource(images[i]); // set image in ImageView
            simpleViewFlipper.addView(imageView); // add the created ImageView in ViewFlipper
            missingPersonRArrayList = new ArrayList<>();
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
       databaseReferenceM.addValueEventListener(new ValueEventListener() {
           @Override
           public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
               for(DataSnapshot missingPosts : dataSnapshot.getChildren()){
                   MissingPersonR missingPersonR = missingPosts.getValue(MissingPersonR.class);
                   missingPersonRArrayList.add(missingPersonR);

               }
               missingAdapterR = new MissingAdapterR(getApplicationContext(),missingPersonRArrayList);
               recyclerViewMisingImage.setAdapter(missingAdapterR);
           }

           @Override
           public void onCancelled(@NonNull DatabaseError databaseError) {

           }
       });

        LinearLayoutManager horizontalMn = new LinearLayoutManager(HomeDashBoardSlider.this,LinearLayoutManager.HORIZONTAL,false);
        recyclerViewMisingImage.setLayoutManager(horizontalMn);
        recyclerViewMisingImage.setNestedScrollingEnabled(false);
        recyclerViewMisingImage.setItemAnimator(new DefaultItemAnimator());

    }

}
