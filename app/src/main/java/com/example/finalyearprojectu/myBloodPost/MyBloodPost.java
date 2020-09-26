package com.example.finalyearprojectu.myBloodPost;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.drawable.Drawable;
import android.os.Bundle;

import com.example.finalyearprojectu.R;
import com.example.finalyearprojectu.homeSearchAndNotification.bloodPostRecycleView.FragmentBloodAdapter;
import com.example.finalyearprojectu.homeSearchAndNotification.bloodPostRecycleView.FragmentBloodModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MyBloodPost extends AppCompatActivity {
    List<MyBloodPostModel> myBloodPostsList;
    List<String> keys;
    RecyclerView myBloodPostRecycleView;
    FirebaseDatabase myBloodPostDatabase;
    DatabaseReference myBloodDatabaseReference;
    String myBloodAuthId;
    MyBloodPostAdapter myBloodPostAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_blood_post);
        getSupportActionBar().setTitle("Your Posts");
        myBloodPostsList = new ArrayList<>();
        myBloodPostRecycleView = findViewById(R.id.my_blood_posts_recycle_view);
        myBloodAuthId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        loadBloodPosts();
    }


    private void loadBloodPosts() {
        keys = new ArrayList<String>();
        myBloodPostDatabase = myBloodPostDatabase.getInstance();
        String uId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        myBloodDatabaseReference = myBloodPostDatabase.getReference("blood_requests");
        Query myBloodPostQuery = myBloodDatabaseReference.orderByChild("user_key").equalTo(uId);
        myBloodPostQuery.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                myBloodPostsList.clear();
                for (DataSnapshot dataSnapshotB : dataSnapshot.getChildren())
                {
                    MyBloodPostModel myBloodPostModel = dataSnapshotB.getValue(MyBloodPostModel.class);
                    myBloodPostsList.add(myBloodPostModel);
                    keys.add(dataSnapshotB.getKey());
                }
                myBloodPostAdapter = new MyBloodPostAdapter(getApplicationContext(),myBloodPostsList,keys);
                myBloodPostRecycleView.setAdapter(myBloodPostAdapter);
            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        myBloodPostRecycleView.setLayoutManager(mLayoutManager);
//        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));
        DividerItemDecoration horizontalDecoration = new DividerItemDecoration(getApplicationContext(),
                DividerItemDecoration.VERTICAL);
        Drawable horizontalDivider = ContextCompat.getDrawable(getApplicationContext(), R.drawable.recycle_divider);
        horizontalDecoration.setDrawable(horizontalDivider);
        myBloodPostRecycleView.addItemDecoration(horizontalDecoration);
        myBloodPostRecycleView.setNestedScrollingEnabled(false);
        myBloodPostRecycleView.setItemAnimator(new DefaultItemAnimator());
    }

}