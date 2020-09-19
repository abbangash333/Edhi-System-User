package com.example.finalyearprojectuser.homeSearchAndNotification.postmissingdetail;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.example.finalyearprojectuser.R;
import com.example.finalyearprojectuser.signUp.Sign_up;
import com.example.finalyearprojectuser.signUp.UploadUserInfo;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;

public class PostMissingDetailP extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {
    private ImageView personPicture;
    private Button selectGender;
    private Button pStatus;
    private final int PICK_IMAGE = 1;
    private Uri FilePathUri;
    private String genderT;
    private String statusT;
    private Button UButton;
    private EditText pName;
    private  EditText pAge;
    private  EditText pBelong;
    private  EditText pDisappearedCity;
    private  EditText pDisappearedDate;
    private EditText pFullAddress;
    private  EditText pNumber;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;
    private StorageReference storageReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_missing_detail_p);
        getSupportActionBar().setTitle("Person Detail");
        selectGender = findViewById(R.id.select_gender_d);
        personPicture = findViewById(R.id.person_picture);
        pStatus = findViewById(R.id.select_status_d);
        UButton = findViewById(R.id.d_button);
        pName = findViewById(R.id.person_nameP);
        pAge = findViewById(R.id.person_ageP);
        pBelong = findViewById(R.id.b_city);
        pDisappearedCity = findViewById(R.id.d_city);
        pDisappearedDate = findViewById(R.id.d_date);
        pFullAddress = findViewById(R.id.d_full_address);
        databaseReference = FirebaseDatabase.getInstance().getReference();
        storageReference = FirebaseStorage.getInstance().getReference("missing_images");
        pNumber = findViewById(R.id.d_phone);
        selectGender.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(PostMissingDetailP.this,v, Gravity.RIGHT);
                popupMenu.setOnMenuItemClickListener(PostMissingDetailP.this);
                popupMenu.inflate(R.menu.pop_menu_gender);
                popupMenu.show();
            }
        });
        pStatus.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {
                PopupMenu popupMenuS = new PopupMenu(PostMissingDetailP.this,v,Gravity.RIGHT);
                popupMenuS.setOnMenuItemClickListener(PostMissingDetailP.this);
                popupMenuS.inflate(R.menu.pop_menu_status);
                popupMenuS.show();
            }
        });
        personPicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent chooseProfilePictureFromGallery = new Intent(Intent.ACTION_GET_CONTENT);
                chooseProfilePictureFromGallery.setType("image/*");
                if (chooseProfilePictureFromGallery.resolveActivity(getApplicationContext().getPackageManager()) != null) {
                    startActivityForResult(Intent.createChooser(chooseProfilePictureFromGallery, "Select Picture"), PICK_IMAGE);
                }
            }
        });
        UButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                detailInfoUpload();
//                showToast();
                  sendUserBack();
                 clearAllField();

            }
        });
    }

    private void clearAllField() {
    }

    private void showToast() {
        Toast.makeText(getApplicationContext(), "Information Uploaded Successfully ", Toast.LENGTH_SHORT).show();
    }

    private void sendUserBack() {
    }

    private void detailInfoUpload() {
        if (FilePathUri != null) {
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Uploading");
            progressDialog.show();
            String uName = pName.getText().toString().trim();
            String uAge = pAge.getText().toString();
            String uBelong = pBelong.getText().toString().trim();
            String uDC = pDisappearedCity.getText().toString().trim();
            String uDD = pDisappearedDate.getText().toString().trim();
            String uAdd = pFullAddress.getText().toString();
            String uPh = pNumber.getText().toString().trim();
            String uId = firebaseAuth.getInstance().getCurrentUser().getUid();
           databaseReference = FirebaseDatabase.getInstance().getReference();
           String postid = databaseReference.child("missing_requests").push().getKey();
            StorageReference storageReference2 = storageReference.child(postid+ "." + GetFileExtension(FilePathUri));
            storageReference2.putFile(FilePathUri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            progressDialog.dismiss();
                            PostMissingModel postMissingModel = new PostMissingModel(uAdd,uAge, uBelong,
                                    uDD,uDC,genderT,taskSnapshot.getUploadSessionUri().toString(),postid,uName,statusT,uPh,uId);
                            //String ImageUploadId = databaseReference.push().getKey();
                            Log.d("mes","we are in just above uploading method");
                            databaseReference.child("missing_requests").push().setValue(postMissingModel);
                            showToast();
                            finish();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception exception) {
                            progressDialog.dismiss();
                            Toast.makeText(getApplicationContext(), exception.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    })

                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            //displaying the upload progress
                            double progress = (100.0 * taskSnapshot.getBytesTransferred()) / taskSnapshot.getTotalByteCount();
                            progressDialog.setMessage("Uploaded " + ((int) progress) + "%...");
                        }
                    });

        }
        else {

            Toast.makeText(PostMissingDetailP.this, "Please Select Image or Add Image Name", Toast.LENGTH_LONG).show();

        }

    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.pop_male:
            {
                selectGender.setText("Male");
                genderT ="male";
                return true;
            }
            case R.id.pop_female:
            {
                selectGender.setText("Female");
                genderT ="female";
                return true;
            }
            case R.id.pop_lost:
            {
                pStatus.setText("Lost");
                statusT ="lost";
                return true;
            }
            case R.id.pop_receiver:
                {
                    pStatus.setText("Found");
                    statusT = "found";
                return true;
            }
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK && data != null && data.getData() != null) {

            FilePathUri = data.getData();

            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), FilePathUri);
                personPicture.setImageBitmap(bitmap);
            } catch (IOException e) {

                e.printStackTrace();
            }

        }
    }
    public String GetFileExtension(Uri uri) {

        ContentResolver contentResolver = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri)) ;

    }
}