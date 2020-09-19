package com.example.finalyearprojectu.centerManagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.finalyearprojectu.R;

public class CenterManagementSearch extends AppCompatActivity {
    ListView centerManagementSearchListView;
    TextView textView;
    String[] listOfCenterName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_center_management_search);
        getSupportActionBar().setTitle("Search Center");
        centerManagementSearchListView=findViewById(R.id.search_center_management_listView);
        textView =findViewById(R.id.textView);
        listOfCenterName= getResources().getStringArray(R.array.array_technology);
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.contact_center_list_view,listOfCenterName);
        centerManagementSearchListView.setAdapter(adapter);

        centerManagementSearchListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                // TODO Auto-generated method stub
                String value=adapter.getItem(position);
                Toast.makeText(getApplicationContext(),value,Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(CenterManagementSearch.this, CenterManagementDetailActivity.class);
                intent.putExtra("centerName",value);
                startActivity(intent);

            }
        });
    }
}
