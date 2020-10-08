package com.example.finalyearprojectu.homeSearchAndNotification.postblooddetail;

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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class PostBloodDetail extends AppCompatActivity implements View.OnClickListener {
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_blood_detail);
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
                    Intent intent = new Intent(getApplicationContext(), HomeButtomNavigation.class);
                    startActivity(intent);
                    break;
                }


            }
        }

    }
    //this method will be used for choosing blood group option
    private void openDialogBoxForSelection() {
        final String[] Options = {"A+", "B+","A-","B-","O+","O-","AB+","AB-","k-"};
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
// method will be call
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
        String cAge = age.getText().toString();
        String bloodFr = bloodFor.getText().toString();
        String fAddress = fullAddress.getText().toString();
        String referCity = cityReference.getText().toString();
       String fireAuth = firebaseAuth.getInstance().getCurrentUser().getUid();
       String number = firebaseAuth.getInstance().getCurrentUser().getPhoneNumber();
       databaseReference =FirebaseDatabase.getInstance().getReference();
        String requestKey = databaseReference.child("blood_requests").push().getKey();
        PostBloodDetailModel postBloodDetail = new PostBloodDetailModel(cAge,bloodFr,bloodGroup,fAddress,gender,number,
                referCity,requestKey,requestFor,fireAuth);

        DatabaseReference.CompletionListener completionListener = new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(@Nullable DatabaseError databaseError, @NonNull DatabaseReference databaseReference) {
                if (databaseError != null) {
                    ToastMethod(getApplicationContext(),"Failed to Upload Data");
                } else {
                    ToastMethod(getApplicationContext(),"Data Uploaded Successfully");
                }
            }
        };
        databaseReference.child("blood_requests").push().setValue(postBloodDetail,completionListener);
    }
}