package com.example.finalyearprojectu.centersContactInformation;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Build;
import android.os.Bundle;
import android.widget.SearchView;
import com.example.finalyearprojectu.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
public class ContactCenters extends AppCompatActivity {
    RecyclerView recyclerView;
    DatabaseReference databaseReference;
    ArrayList<CenterDetailModel> mArrayList;
    SearchView sView;
    String searchViewText;
    CenterContactAdapter centerContactAdapter;
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_center_contact_detail);
        getSupportActionBar().setTitle("Contact Center");
        recyclerView = findViewById(R.id.contact_center_recyclerView);
        sView = findViewById(R.id.center_contact_search_view);
        databaseReference = FirebaseDatabase.getInstance().getReference("center_contact");
        String postId = databaseReference.push().getKey();
        mArrayList = new ArrayList<>();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    CenterDetailModel centerDetailModel = ds.getValue(CenterDetailModel.class);
                    mArrayList.add(centerDetailModel);
                }
                centerContactAdapter = new CenterContactAdapter(getApplicationContext(), mArrayList);
                recyclerView.setAdapter(centerContactAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        LinearLayoutManager horizontalMn = new LinearLayoutManager(ContactCenters.this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(horizontalMn);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        //this code will be executed after searching clicks
        sView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                centerContactAdapter.getFilter().filter(newText);
                return false;
            }
        });


    }
}
