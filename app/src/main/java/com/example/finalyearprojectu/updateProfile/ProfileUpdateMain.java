package com.example.finalyearprojectu.updateProfile;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import de.hdodenhof.circleimageview.CircleImageView;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.finalyearprojectu.R;
import com.example.finalyearprojectu.home.homedashboardslider.HomeDashBoardSlider;
import com.example.finalyearprojectu.signUp.UploadUserInfo;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.io.IOException;

public class ProfileUpdateMain extends AppCompatActivity implements View.OnClickListener {
    StorageReference stReferenceUpdate;
    FirebaseAuth fAuthUpdate;
    FirebaseDatabase fDatabaseUpdate;
    DatabaseReference databaseReference;
    String userIdUpdate;
    private Uri FilePathUri;
    private final int PICK_IMAGE = 1;
    private Button updateProfileButton;
    private EditText city;
    private EditText userName;
    private EditText userEmail;
    private CircleImageView imageView;
    private String url;
    private ProgressDialog progressBarHomeLoading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_update_main);
        getSupportActionBar().setTitle("Change Profile");
        updateProfileButton = findViewById(R.id.update_current_date_btn);
        city = findViewById(R.id.update_current_city);
        userName = findViewById(R.id.update_user_name);
        userEmail = findViewById(R.id.update_email);
        imageView = findViewById(R.id.take_profile_update_picture);
        databaseReference = FirebaseDatabase.getInstance().getReference("users");
        stReferenceUpdate = FirebaseStorage.getInstance().getReference("profile_images");
        progressBarHomeLoading = new ProgressDialog(ProfileUpdateMain.this);
        progressBarHomeLoading.show();
        progressBarHomeLoading.setContentView(R.layout.progress_br);
        progressBarHomeLoading.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        startProgressBar();
        updateProfileButton.setOnClickListener(this);
        imageView.setOnClickListener(this);
        loadUserInfo();


    }

    //this will take the button ids for action
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.update_current_date_btn: {
                if (FilePathUri != null) {
                    deleteUserImage();
                    UserInfoUpdate();
                }
                else
                {
                    userInfoUpdateWithoutImage();

                }
                break;

            }
            case R.id.take_profile_update_picture: {
                chooseImage();

            }
        }

    }

    private void deleteUserImage() {
        StorageReference mr = FirebaseStorage.getInstance().getReferenceFromUrl(url);
        mr.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(getApplicationContext(), "Previous Image Deleted", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void chooseImage() {
        Intent chooseProfilePictureFromGallery = new Intent(Intent.ACTION_GET_CONTENT);
        chooseProfilePictureFromGallery.setType("image/*");
        if (chooseProfilePictureFromGallery.resolveActivity(getApplicationContext().getPackageManager()) != null) {
            startActivityForResult(Intent.createChooser(chooseProfilePictureFromGallery, "Select Picture"), PICK_IMAGE);
        }
    }


    private void UserInfoUpdate() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Updating");
        progressDialog.show();
        String uName = userName.getText().toString().trim();
        String uEmail = userEmail.getText().toString();
        String cityUpdate = city.getText().toString().trim();
        String getId = fAuthUpdate.getInstance().getCurrentUser().getUid();
        StorageReference storageReference2 = stReferenceUpdate.child(getId + "." + GetFileExtension(FilePathUri));
        storageReference2.putFile(FilePathUri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                progressDialog.setProgress(0);
                            }
                        }, 500);
                        storageReference2.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                Uri downloadUrl = uri;
                                UploadUserInfo imageUploadInfo = new UploadUserInfo(uName,uEmail, downloadUrl.toString(),cityUpdate);
                                Log.d("mes","we are in just above uploading method");
                                databaseReference.child(getId).setValue(imageUploadInfo);
                                Toast.makeText(getApplicationContext(),"Data Updated successfully",Toast.LENGTH_SHORT).show();
                                progressDialog.dismiss();


                            }
                        });
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
                        progressDialog.setMessage("Updating " + ((int) progress) + "%...");
                    }
                });

    }
    private void userInfoUpdateWithoutImage()
    {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Updating");
        progressDialog.show();
        String uName = userName.getText().toString().trim();
        String uEmail = userEmail.getText().toString();
        String cityUpdate = city.getText().toString().trim();
        String getId = fAuthUpdate.getInstance().getCurrentUser().getUid();
        UploadUserInfo imageUploadInfo = new UploadUserInfo(uName,uEmail, url,cityUpdate);
        Log.d("mes","we are in just above uploading method");
        databaseReference.child(getId).setValue(imageUploadInfo);
        Toast.makeText(getApplicationContext(),"Your Data updated",Toast.LENGTH_SHORT).show();
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                progressDialog.dismiss();
            }
        }, 500);


    }

    private void sendUserToHome() {
        Intent intent = new Intent(getApplicationContext(), HomeDashBoardSlider.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK && data != null && data.getData() != null) {

            FilePathUri = data.getData();

            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), FilePathUri);
                imageView.setImageBitmap(bitmap);
            } catch (IOException e) {

                e.printStackTrace();
            }

        }
    }

    public String GetFileExtension(Uri uri) {

        ContentResolver contentResolver = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri));

    }

    private void loadUserInfo() {
        String getUserId = fAuthUpdate.getInstance().getCurrentUser().getUid();

       DatabaseReference dCommand = databaseReference.child(getUserId);
        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    String pName = dataSnapshot.child("display_name").getValue(String.class);
                    String pUserEmail = dataSnapshot.child("email").getValue(String.class);
                    String pPhotoUrl = dataSnapshot.child("photo_url").getValue(String.class);
                    url = pPhotoUrl;
                    String pCity = dataSnapshot.child("city_name").getValue(String.class);
                    Picasso.get().load(pPhotoUrl).into(imageView);
                    userName.setText(pName);
                    userEmail.setText(pUserEmail);
                    city.setText(pCity);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        dCommand.addListenerForSingleValueEvent(valueEventListener);
    }
    private void startProgressBar() {
        Thread timer = new Thread()
        {
            @Override
            public void run() {
                try {
                    sleep(3000);
                    progressBarHomeLoading.dismiss();
                    super.run();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        timer.start();
    }
}
