package com.example.finalyearprojectu.myBloodPost;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.finalyearprojectu.R;
import com.example.finalyearprojectu.homeSearchAndNotification.HomeButtomNavigation;
import com.example.finalyearprojectu.homeSearchAndNotification.postblooddetail.PostBloodDetailModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class MyBloodPostEdit extends AppCompatActivity implements View.OnClickListener {
    private EditText bloodFor;
    private EditText cityReference;
    private EditText fullAddress;
    private EditText age;
    private Button selectGenderBtn;
    private Button requestTypeBtn;
    private Button bloodGroupBtn;
    private Button postBloodRequest;
    private String gender;
    private String requestFor;
    private String bloodGroup;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    String requestKey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_blood_post_edit);
        bloodFor = findViewById(R.id.blood_for);
        cityReference = findViewById(R.id.reference_city);
        fullAddress = findViewById(R.id.full_address);
        age = findViewById(R.id.age_p);
        selectGenderBtn = findViewById(R.id.select_gender);
        requestTypeBtn = findViewById(R.id.select_request_type);
        bloodGroupBtn = findViewById(R.id.select_blood_g);
        postBloodRequest = findViewById(R.id.post_blood_request);
        selectGenderBtn.setOnClickListener(this);
        requestTypeBtn.setOnClickListener(this);
        bloodGroupBtn.setOnClickListener(this);
        postBloodRequest.setOnClickListener(this);
        Intent intent = getIntent();
        //these will take data from recycle view
        bloodGroup = intent.getStringExtra("blood");
        String bloodForM = intent.getStringExtra("bloodFor");
        bloodFor.setText(bloodForM);
        String locationN = intent.getStringExtra("location");
        cityReference.setText(locationN);
        String requestB = intent.getStringExtra("request");
        requestFor = requestB;
        String ageB = intent.getStringExtra("age");
        age.setText(ageB);
        gender = intent.getStringExtra("gender");
        String fullAdd = intent.getStringExtra("fullAddress");
        fullAddress.setText(fullAdd);
        String phoneNumber = intent.getStringExtra("phoneNumber");
        bloodFor.setText(bloodForM);
        cityReference.setText(locationN);
        fullAddress.setText(fullAdd);
        age.setText(ageB);
        selectGenderBtn.setText(gender);
        requestTypeBtn.setText(requestB);
        bloodGroupBtn.setText(bloodGroup);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.select_blood_g:
            {
                openDialogBoxForSelection();
                break;
            }
            case R.id.select_request_type:
            {
                openDialogBoxForRequestType();
                break;
            }
            case R.id.select_gender:
            {
                openDialogBoxForGenderSelection();
                break;
            }
            case R.id.post_blood_request:
            {
                if(checkValidity()==true)
                {
                    uploadBloodPostData();
                    Intent intent = new Intent(getApplicationContext(), MyBloodPost.class);
                    startActivity(intent);
                    break;
                }


            }
        }
    }
    private void openDialogBoxForSelection() {
        final String[] Options = {"A+", "B+","A-","B-","O+","O-","AB+","AB-"};
        AlertDialog.Builder window;
        window = new AlertDialog.Builder(this);
        window.setTitle("SELECT GROUP");
        window.setItems(Options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(which == 0){
                    bloodGroupBtn.setText("A+");
                    bloodGroup = "A+";

                }
                else if(which == 1){
                    //second option clicked, do this...
                    bloodGroupBtn.setText("B+");
                    bloodGroup = "B+";
                }
                else if(which == 2){
                    //second option clicked, do this...
                    bloodGroupBtn.setText("A-");
                    bloodGroup = "A-";
                }
                else if(which == 3){
                    //second option clicked, do this...
                    bloodGroupBtn.setText("B-");
                    bloodGroup = "B-";
                }
                else if(which == 4){
                    //second option clicked, do this...
                    bloodGroupBtn.setText("O+");
                    bloodGroup = "O+";
                }
                else if(which == 5){
                    //second option clicked, do this...
                    bloodGroupBtn.setText("O-");
                    bloodGroup = "O-";
                }
                else if(which == 6){
                    //second option clicked, do this...
                    bloodGroupBtn.setText("AB+");
                    bloodGroup = "AB+";

                }
                else if(which == 7){
                    //second option clicked, do this...
                    bloodGroupBtn.setText("AB-");
                    bloodGroup = "AB-";

                }

            }
        });

        window.show();
    }
    private void openDialogBoxForGenderSelection() {
        final String[] Options = {"MALE","FEMALE"};
        AlertDialog.Builder window;
        window = new AlertDialog.Builder(this);
        window.setTitle("SELECT GENDER");
        window.setItems(Options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(which == 0){
                    selectGenderBtn.setText("Male");
                    gender = "Male";

                }
                else if(which == 1){
                    //second option clicked, do this...
                    selectGenderBtn.setText("Female");
                    gender = "Female";
                }
            }
        });

        window.show();
    }
    private void openDialogBoxForRequestType()
    {
        final String[] Options = {"DONOR","RECEIVER"};
        AlertDialog.Builder window;
        window = new AlertDialog.Builder(this);
        window.setTitle("SELECT REQUEST TYPE");
        window.setItems(Options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(which == 0){
                    requestTypeBtn.setText("Donor");
                    requestFor = "Donor";

                }
                else if(which == 1){
                    //second option clicked, do this...
                    requestTypeBtn.setText("Receiver");
                    requestFor = "Receiver";
                }
            }
        });

        window.show();

    }

    //this method will check the validity of fields
    private boolean checkValidity()
    {
        String bFor = bloodFor.getText().toString();
        String cRP = cityReference.getText().toString();
        String fullA = fullAddress.getText().toString();
        String bAge = age.getText().toString();
        if(bFor.isEmpty())
        {
            ToastMethod(getApplication(),"Please give a name");
            return false;
        }
        if(cRP.isEmpty())
        {
            ToastMethod(getApplication(),"Please give city Reference");
            return false;
        }
        if(fullA.isEmpty())
        {
            ToastMethod(getApplication(),"Please give full address");
            return false;
        }
        if(bAge.isEmpty())
        {
            ToastMethod(getApplication(),"Please give age");
            return false;
        }
        return true;
    }
    //this method is used for toast Messages
    private void ToastMethod(Context context, String message) {
        Toast.makeText(context,message,Toast.LENGTH_SHORT).show();

    }
    //this method will upload data to firebase
    private void uploadBloodPostData() {
        Intent intent = getIntent();
        requestKey = intent.getStringExtra("keyName");
        String cAge = age.getText().toString();
        String bloodFr = bloodFor.getText().toString();
        String fAddress = fullAddress.getText().toString();
        String referCity = cityReference.getText().toString();
        String fireAuth = firebaseAuth.getInstance().getCurrentUser().getUid();
        String number = firebaseAuth.getInstance().getCurrentUser().getPhoneNumber();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("blood_requests").child(requestKey);
        MyBloodPostModel myBloodPost =new MyBloodPostModel(cAge,bloodFr,bloodGroup,fAddress,gender
                ,number,referCity,requestKey,requestFor,fireAuth);
        ref.setValue(myBloodPost);
        Toast.makeText(getApplicationContext(),"Data Updated Successfully!",Toast.LENGTH_SHORT).show();
    }
}