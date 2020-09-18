package com.example.finalyearprojectuser.homeSearchAndNotification.homeButtomNavigation.missingPersonPostR;


import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.finalyearprojectuser.R;
import com.example.finalyearprojectuser.home.homedashboardslider.HomeDashBoardSlider;
import com.example.finalyearprojectuser.home.missingRecycleView.MissingAdapterR;
import com.example.finalyearprojectuser.home.missingRecycleView.MissingPersonR;
import com.example.finalyearprojectuser.homeSearchAndNotification.homeButtomNavigation.missingPersonPostR.MissingPersonHomM;
import com.example.finalyearprojectuser.homeSearchAndNotification.homeButtomNavigation.missingPersonPostR.MissingPersonHomeAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_bottom_missing_posts extends Fragment {
   FloatingActionButton homeFloatingActionBtn;
   RecyclerView recyclerView;
   MissingPersonHomeAdapter missingPersonHomeAdapter;
   List<MissingPersonHomM> listHM;
   DatabaseReference databaseReference;
    private FirebaseDatabase firebaseInstance;
    MissingPersonHomM missingPersonHomM;

    public Fragment_bottom_missing_posts() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        missingPersonHomM = new MissingPersonHomM();
        listHM = new ArrayList<>();
        firebaseInstance = FirebaseDatabase.getInstance();
        databaseReference = firebaseInstance.getReference("missing_requests");
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fragment_bottom_missing_posts, container, false);
        recyclerView = view.findViewById(R.id.fragment_bottom_missing_posts_recycle_view);
        homeFloatingActionBtn = view.findViewById(R.id.floating_action_home_missing);
        homeFloatingActionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(),"missing",Toast.LENGTH_SHORT).show();
            }
        });
        loadMissingPersonPosts();
        return view;

    }
    private void loadMissingPersonPosts() {
        databaseReference = FirebaseDatabase.getInstance().getReference("missing_requests");
        RecyclerView.LayoutManager recycleManager = new LinearLayoutManager(getActivity());
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot missingPosts : dataSnapshot.getChildren()) {
                    MissingPersonHomM missingPersonHomM = missingPosts.getValue(MissingPersonHomM.class);
                    listHM.add(missingPersonHomM);

                }
                missingPersonHomeAdapter = new MissingPersonHomeAdapter(listHM, getActivity());
                recyclerView.setAdapter(missingPersonHomeAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
//        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));
        DividerItemDecoration horizontalDecoration = new DividerItemDecoration(getActivity(),
                DividerItemDecoration.VERTICAL);
        Drawable horizontalDivider = ContextCompat.getDrawable(getActivity(), R.drawable.recycle_divider);
        horizontalDecoration.setDrawable(horizontalDivider);
        recyclerView.addItemDecoration(horizontalDecoration);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }

}
