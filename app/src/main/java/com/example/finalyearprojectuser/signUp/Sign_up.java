package com.example.finalyearprojectuser.signUp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.app.AlertDialog;
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
import android.widget.Toast;

import com.example.finalyearprojectuser.R;
import com.example.finalyearprojectuser.homedashboardslider.HomeDashBoardSlider;
import com.example.finalyearprojectuser.logIn.OtpPattern.OtpActivity;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        getSupportActionBar().setTitle("Sign Up");
        userProfilePicture = findViewById(R.id.take_profile_picture_fromGallery);
        userEmail = findViewById(R.id.user_email);
        userName = findViewById(R.id.user_name);
        signUpBtn = findViewById(R.id.sign_up_btn);
        databaseReference = FirebaseDatabase.getInstance().getReference();
        storageReference = FirebaseStorage.getInstance().getReference("profile_images");
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
                sendUserToHome();
            }
        });
    }

    private void sendUserToHome() {
        Intent intent = new Intent(getApplicationContext(),HomeDashBoardSlider.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    private void UserInfoUpload() {
        if (FilePathUri != null) {

            AlertDialog.Builder progressDialog = new AlertDialog.Builder(this);
            progressDialog.setTitle("Image is Uploading...");
            //progressDialog.show();
            String uName = userName.getText().toString().trim();
            String uEmail = userEmail.getText().toString();
            String getId = firebaseAuth.getInstance().getCurrentUser().getUid();
            StorageReference storageReference2 = storageReference.child(System.currentTimeMillis() + "." + GetFileExtension(FilePathUri));
            storageReference2.putFile(FilePathUri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Toast.makeText(getApplicationContext(), "Image Uploaded Successfully ", Toast.LENGTH_LONG).show();
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
