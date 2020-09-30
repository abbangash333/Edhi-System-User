package com.example.finalyearprojectu.myMissingPost;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;

import com.example.finalyearprojectu.R;
import com.example.finalyearprojectu.homeSearchAndNotification.missingPersonPostR.MissingPersonHomM;
import com.example.finalyearprojectu.homeSearchAndNotification.missingPersonPostR.MissingPersonHomeAdapter;
import com.example.finalyearprojectu.homeSearchAndNotification.postblooddetail.PostBloodDetail;
import com.example.finalyearprojectu.homeSearchAndNotification.postmissingdetail.PostMissingDetailP;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MyMissingPost extends AppCompatActivity {
    RecyclerView recyclerView;
    MyMissingPostAdapter myMissingPostAdapter;
    List<MyMissingPostModel> listMyMissing;
    List<String> keys;
    DatabaseReference databaseReference;
    private FirebaseDatabase firebaseInstance;
    MyMissingPostModel myMissingPostModel;
    FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_missing_post);
        getSupportActionBar().setTitle("Your Posts");
        String uId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        myMissingPostModel= new MyMissingPostModel();
        listMyMissing = new ArrayList<>();
        keys = new ArrayList<String>();
        floatingActionButton = findViewById(R.id.my_missing_floating_action_btn);
        firebaseInstance = FirebaseDatabase.getInstance();
        databaseReference = firebaseInstance.getReference("missing_requests");
        recyclerView = findViewById(R.id.my_missing_post_recycle_view);
        databaseReference = FirebaseDatabase.getInstance().getReference("missing_requests");
        Query myMissingQuery = databaseReference.orderByChild("user_key").equalTo(uId);
        myMissingQuery.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listMyMissing.clear();
                for (DataSnapshot missingPosts : dataSnapshot.getChildren()) {
                    MyMissingPostModel myMissingPostModel = missingPosts.getValue(MyMissingPostModel.class);
                    listMyMissing.add(myMissingPostModel);
                    keys.add(missingPosts.getKey());

                }
                myMissingPostAdapter = new MyMissingPostAdapter(listMyMissing, getApplicationContext(), keys);
                recyclerView.setAdapter(myMissingPostAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
//        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));
        DividerItemDecoration horizontalDecoration = new DividerItemDecoration(getApplicationContext(),
                DividerItemDecoration.VERTICAL);
        Drawable horizontalDivider = ContextCompat.getDrawable(getApplicationContext(), R.drawable.recycle_divider);
        horizontalDecoration.setDrawable(horizontalDivider);
        recyclerView.addItemDecoration(horizontalDecoration);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), PostMissingDetailP.class);
                startActivity(intent);
            }
        });
    }
}