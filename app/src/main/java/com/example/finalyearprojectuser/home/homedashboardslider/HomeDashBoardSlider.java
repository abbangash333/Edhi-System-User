package com.example.finalyearprojectuser.home.homedashboardslider;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import com.example.finalyearprojectuser.R;
import com.example.finalyearprojectuser.home.missingRecycleView.MissingAdapterR;
import com.example.finalyearprojectuser.home.missingRecycleView.MissingPersonR;

import java.util.ArrayList;

public class HomeDashBoardSlider extends AppCompatActivity {
    private ViewFlipper simpleViewFlipper;
    // array of images
    int[] images = {R.drawable.flipperimage1, R.drawable.flipperimage2};
    public static final String[] names= {"Breaking Bad","Rick and Morty"};
    RecyclerView recyclerViewMisingImage;
    MissingAdapterR missingAdapterR;
    ArrayList<MissingPersonR> missingPersonRArrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homedashboardslider);
        getSupportActionBar().setTitle("EDHI Welfare Trust");
        simpleViewFlipper =  findViewById(R.id.frontPageViewFlipper); // get the reference of ViewFlipper
        recyclerViewMisingImage = findViewById(R.id.recent_missing_recycleView);
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
//        for(int i=0; i<missingPersonRArrayList.size();i++)
//        {
//            MissingPersonR missingPersonR = new MissingPersonR();
//            missingPersonR.setImgPosition(images[i]);
//            missingPersonR.setName(names[i]);
//            missingPersonRArrayList.add(missingPersonR);
//        }
        MissingPersonR missingPersonR = new MissingPersonR();
        missingPersonR.setImgPosition(R.drawable.missed_person_image);
        missingPersonR.setName("AurangZeb Khan");
        MissingPersonR missingPersonR1 = new MissingPersonR();
        missingPersonR1.setImgPosition(R.drawable.missed_person_image);
        missingPersonR1.setName("A");
        missingPersonRArrayList.add(missingPersonR);
        missingPersonRArrayList.add(missingPersonR1);
        missingAdapterR = new MissingAdapterR(getApplicationContext(),missingPersonRArrayList);
        LinearLayoutManager horizontalMn = new LinearLayoutManager(HomeDashBoardSlider.this,LinearLayoutManager.HORIZONTAL,false);
        recyclerViewMisingImage.setLayoutManager(horizontalMn);
        recyclerViewMisingImage.setNestedScrollingEnabled(false);
        recyclerViewMisingImage.setItemAnimator(new DefaultItemAnimator());
        recyclerViewMisingImage.setAdapter(missingAdapterR);
    }
}
