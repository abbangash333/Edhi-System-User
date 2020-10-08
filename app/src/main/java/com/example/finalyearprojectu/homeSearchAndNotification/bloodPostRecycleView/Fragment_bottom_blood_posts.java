package com.example.finalyearprojectu.homeSearchAndNotification.bloodPostRecycleView;


import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.finalyearprojectu.R;
import com.example.finalyearprojectu.homeSearchAndNotification.postblooddetail.PostBloodDetail;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_bottom_blood_posts extends Fragment {
    List<FragmentBloodModel> bloodList;
    RecyclerView recyclerViewBlood;
    FragmentBloodAdapter fragmentBloodAdapter;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    FloatingActionButton floatingActionButton;

    public Fragment_bottom_blood_posts() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        bloodList = new ArrayList<>();
        View view = inflater.inflate(R.layout.fragment_fragment_bottom_blood_posts, container, false);
        recyclerViewBlood = view.findViewById(R.id.fragment_bottom_blood_posts_recycle_view);
        floatingActionButton = view.findViewById(R.id.floating_action_home_blood);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(),"blood",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), PostBloodDetail.class);
                startActivity(intent);
            }
        });
        loadBloodPosts();
        return view;
    }
// this will load the blood post to the main recycle view
    private void loadBloodPosts() {
        firebaseDatabase = firebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("blood_requests");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                bloodList.clear();
                for (DataSnapshot dataSnapshotB : dataSnapshot.getChildren())
                {
                    FragmentBloodModel fragmentBloodModel = dataSnapshotB.getValue(FragmentBloodModel.class);
                    bloodList.add(fragmentBloodModel);
                }
                 fragmentBloodAdapter = new FragmentBloodAdapter(getActivity(),bloodList);
                 recyclerViewBlood.setAdapter(fragmentBloodAdapter);
            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        // recycle view presentation
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerViewBlood.setLayoutManager(mLayoutManager);
//        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));
        DividerItemDecoration horizontalDecoration = new DividerItemDecoration(getActivity(),
                DividerItemDecoration.VERTICAL);
        Drawable horizontalDivider = ContextCompat.getDrawable(getActivity(), R.drawable.recycle_divider);
        horizontalDecoration.setDrawable(horizontalDivider);
        recyclerViewBlood.addItemDecoration(horizontalDecoration);
        recyclerViewBlood.setNestedScrollingEnabled(false);
        recyclerViewBlood.setItemAnimator(new DefaultItemAnimator());
    }
    public void floatingActionBlood(FloatingActionButton floatingActionButton)
    {

    }

}
