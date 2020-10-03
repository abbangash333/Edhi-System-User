package com.example.finalyearprojectu.centerManagement;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.SearchView;

import com.example.finalyearprojectu.R;
import com.example.finalyearprojectu.centersContactInformation.CenterContactAdapter;
import com.example.finalyearprojectu.centersContactInformation.CenterDetailModel;
import com.example.finalyearprojectu.centersContactInformation.ContactCenters;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class CenterManagementSearch extends AppCompatActivity {
    RecyclerView centerManagementRecycleView;
    DatabaseReference cManagementDatabaseRef;
    SearchView cManagementSearchView;
    CenterManagementAdapter centerManagementAdapter;
    ArrayList<CenterManagementModelClass> mArraylist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_center_management_search);
        getSupportActionBar().setTitle("Search Center");
        cManagementDatabaseRef = FirebaseDatabase.getInstance().getReference("center_information");
        centerManagementRecycleView = findViewById(R.id.center_management_recycle_view);
        cManagementSearchView = findViewById(R.id.center_management_search_view);
        mArraylist = new ArrayList<>();
        cManagementDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot dSnapshot : dataSnapshot.getChildren())
                {
                    CenterManagementModelClass centerManagementModelClass = dSnapshot.getValue(CenterManagementModelClass.class);
                    mArraylist.add(centerManagementModelClass);
                }
                centerManagementAdapter = new CenterManagementAdapter(getApplicationContext(),mArraylist);
                centerManagementRecycleView.setAdapter(centerManagementAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        LinearLayoutManager horizontalMn = new LinearLayoutManager(CenterManagementSearch.this, LinearLayoutManager.VERTICAL, false);
        centerManagementRecycleView.setLayoutManager(horizontalMn);
        centerManagementRecycleView.setNestedScrollingEnabled(false);
        centerManagementRecycleView.setItemAnimator(new DefaultItemAnimator());
        cManagementSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                centerManagementAdapter.getFilter().filter(newText);
                return false;
            }
        });




    }
}
