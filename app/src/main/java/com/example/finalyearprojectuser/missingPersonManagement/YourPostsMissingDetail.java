package com.example.finalyearprojectuser.missingPersonManagement;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.finalyearprojectuser.R;

import java.util.ArrayList;
import java.util.List;

public class YourPostsMissingDetail extends Fragment {
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;
    private List<ModelClassForMissingPosts> list;


    public YourPostsMissingDetail() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       final View view = inflater.inflate(R.layout.fragment_missing_your_posts_detail, container, false);
        final FragmentActivity c = getActivity();
        recyclerView =  view.findViewById(R.id.missingPersonPosts_recycle_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(c);
        recyclerView.setLayoutManager(layoutManager);
        list = new ArrayList<>();
        loadRecyclerViewItem();
        return  view;

    }

    private void loadRecyclerViewItem() {
        for (int i = 1; i <= 10; i++) {
            ModelClassForMissingPosts myList = new ModelClassForMissingPosts("Ali","kohat",
                    "dec 20,1990",29,"Peshawar","033396014");
            list.add(myList);

        }

        adapter = new CustomAdapter(list, getContext());
        recyclerView.setAdapter(adapter);
    }

}
