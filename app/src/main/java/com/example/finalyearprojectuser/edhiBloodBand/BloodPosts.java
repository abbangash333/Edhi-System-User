package com.example.finalyearprojectuser.edhiBloodBand;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.finalyearprojectuser.R;

import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class BloodPosts extends Fragment{
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;
    private List<ModelClassForBloodPosts> list;

    public BloodPosts() {
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
     final  View view = inflater.inflate(R.layout.fragment_blood_posts, container, false);
        final FragmentActivity c = getActivity();
        recyclerView = view.findViewById(R.id.update_blood_post);
        LinearLayoutManager layoutManager = new LinearLayoutManager(c);
        recyclerView.setLayoutManager(layoutManager);
        list = new ArrayList<>();
        loadRecyclerViewItem();
        return  view;
    }

    private void loadRecyclerViewItem() {
        for (int i = 1; i <= 10; i++) {
          ModelClassForBloodPosts myList = new ModelClassForBloodPosts(
                    "Request " + i,
                    "A+"
            );
            list.add(myList);
        }

        adapter = new CustomAdapter(list, getContext());
        recyclerView.setAdapter(adapter);
    }
}