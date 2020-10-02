package com.example.finalyearprojectu.signUp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

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
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.finalyearprojectu.R;
import com.example.finalyearprojectu.home.homedashboardslider.HomeDashBoardSlider;
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

public class Sign_up extends AppCompatActivity {
    private ImageView userProfilePicture;
    private EditText userEmail;
    private EditText userName;
    private Button signUpBtn;
    private final int PICK_IMAGE = 1;
    FirebaseAuth firebaseAuth;
    DatabaseReference databaseReference;
    private Uri FilePathUri;
    StorageReference storageReference;
    ProgressBar signUpProgressBar;
    EditText cityOfPerson;
    private int backpress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        getSupportActionBar().setTitle("Sign Up");
        signUpProgressBar = findViewById(R.id.sign_up_progress_bar);
        userProfilePicture = findViewById(R.id.take_profile_picture_fromGallery);
        userEmail = findViewById(R.id.user_email);
        userName = findViewById(R.id.user_name);
        signUpBtn = findViewById(R.id.sign_up_btn);
        cityOfPerson = findViewById(R.id.city_of_person_profile);
        databaseReference = FirebaseDatabase.getInstance().getReference();
        storageReference = FirebaseStorage.getInstance().getReference("profile_images");
        signUpProgressBar.setVisibility(View.GONE);
        userProfilePicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent chooseProfilePictureFromGallery = new Intent(Intent.ACTION_GET_CONTENT);
                chooseProfilePictureFromGallery.setType("image/*");
                if (chooseProfilePictureFromGallery.resolveActivity(getApplicationContext().getPackageManager()) != null) {
                    startActivityForResult(Intent.createChooser(chooseProfilePictureFromGallery, "Select Picture"), PICK_IMAGE);
                }

            }
        });
        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkFields() == true) {
                    UserInfoUpload();
                    showProgressBar();
                    sendUserToHome();
                }


            }

        });
    }

    private void showProgressBar() {
        //signUpProgressBar.setVisibility(View.VISIBLE);
        Toast.makeText(getApplicationContext(), "Profile created Successfully ", Toast.LENGTH_SHORT).show();
    }

    private void sendUserToHome() {
        Intent intent = new Intent(getApplicationContext(), HomeDashBoardSlider.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }

    private void UserInfoUpload() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Uploading");
        progressDialog.show();
        if (FilePathUri != null) {
            String uName = userName.getText().toString().trim();
            String uEmail = userEmail.getText().toString();
            String city = cityOfPerson.getText().toString().trim();
            String getId = firebaseAuth.getInstance().getCurrentUser().getUid();
            StorageReference storageReference2 = storageReference.child(getId + "." + GetFileExtension(FilePathUri));
            storageReference2.putFile(FilePathUri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                              @Override
                                              public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                                  @SuppressWarnings("VisibleForTests")
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

//                                    UploadUserInfo imageUploadInfo = new UploadUserInfo(uName,uEmail, downloadUrl.toString());
                                                          //String ImageUploadId = databaseReference.push().getKey();
                                                          UploadUserInfo imageUploadInfo = new UploadUserInfo(uName, uEmail, downloadUrl.toString(), city);
                                                          Log.d("mes", "we are in just above uploading method");
                                                          databaseReference.child("users").child(getId).setValue(imageUploadInfo);

                                                      }
                                                  });


                                              }
                                          }
                    )
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
        } else {

            Toast.makeText(Sign_up.this, "Please Select Image or Add Image Name", Toast.LENGTH_LONG).show();

        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK && data != null && data.getData() != null) {

            FilePathUri = data.getData();

            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), FilePathUri);
                userProfilePicture.setImageBitmap(bitmap);
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

    //this will be used for checking of avalaibility
    public boolean checkFields() {
        if (userEmail.getText().toString().isEmpty() || userEmail.getText().toString().length() < 6) {
            Toast.makeText(getApplicationContext(), "Please enter email", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (userName.getText().toString().isEmpty() || userName.getText().toString().length() < 4) {
            Toast.makeText(getApplicationContext(), "Please enter email", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (cityOfPerson.getText().toString().isEmpty() || cityOfPerson.getText().toString().length() < 3) {
            Toast.makeText(getApplicationContext(), "Please enter email", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        backpress = (backpress + 1);
        Toast.makeText(getApplicationContext(), " Press Back again to Exit ", Toast.LENGTH_SHORT).show();

        if (backpress > 1) {
            FirebaseAuth.getInstance().signOut();
            this.finishAffinity();   this.finish();
        }

    }
}
