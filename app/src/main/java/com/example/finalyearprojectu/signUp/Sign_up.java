package com.example.finalyearprojectu.signUp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
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
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
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
                UserInfoUpload();
                showProgressBar();
                sendUserToHome();

            }

        });
    }

    private void showProgressBar() {
        //signUpProgressBar.setVisibility(View.VISIBLE);
        Toast.makeText(getApplicationContext(), "Profile created Successfully ", Toast.LENGTH_SHORT).show();
    }

    private void sendUserToHome() {
        Intent intent = new Intent(getApplicationContext(),HomeDashBoardSlider.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }

    private void UserInfoUpload() {
        if (FilePathUri != null) {
            String uName = userName.getText().toString().trim();
            String uEmail = userEmail.getText().toString();
            String getId = firebaseAuth.getInstance().getCurrentUser().getUid();
            StorageReference storageReference2 = storageReference.child(System.currentTimeMillis() + "." + GetFileExtension(FilePathUri));
            storageReference2.putFile(FilePathUri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            @SuppressWarnings("VisibleForTests")
                            UploadUserInfo imageUploadInfo = new UploadUserInfo(uName,uEmail, taskSnapshot.getUploadSessionUri().toString());
                            //String ImageUploadId = databaseReference.push().getKey();
                            Log.d("mes","we are in just above uploading method");
                            databaseReference.child("users").child(getId).setValue(imageUploadInfo);
                        }
                    });
        }
        else {

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
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri)) ;

    }
}
