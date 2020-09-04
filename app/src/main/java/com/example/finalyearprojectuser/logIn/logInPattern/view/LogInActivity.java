package com.example.finalyearprojectuser.logIn.logInPattern.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.example.finalyearprojectuser.R;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.finalyearprojectuser.homeDashBoard.HomeDashBoard;
import com.example.finalyearprojectuser.logIn.OtpPattern.OtpActivity;
import com.example.finalyearprojectuser.logIn.logInPattern.presenter.ILogInPresenter;
import com.example.finalyearprojectuser.logIn.logInPattern.presenter.LogInPresenter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.lang.reflect.Type;
import java.util.concurrent.TimeUnit;

public class LogInActivity extends AppCompatActivity implements ILogInView, View.OnClickListener  {
    private FirebaseAuth mAuth;
    private FirebaseUser mCurrentUser;

    private EditText mCountryCode;
    private EditText mPhoneNumber;

    private Button mGenerateBtn;


    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;

    private ILogInPresenter loginPresenter;
    private ProgressBar progressBar;
    private ProgressBar mLoginProgress;

    private TextView mLoginFeedbackText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in2);
        getSupportActionBar().hide();
        mGenerateBtn = this.findViewById(R.id.generate_btn);
        mPhoneNumber = this.findViewById(R.id.phone_number_text);
        progressBar = this.findViewById(R.id.progress_login);
        mGenerateBtn.setOnClickListener(this);
        loginPresenter = new LogInPresenter(this);
        loginPresenter.setProgressBarVisiblity(View.INVISIBLE);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.generate_btn:
                String validCheckNumber = mPhoneNumber.getText().toString();
                if (validCheckNumber.isEmpty()) {
                    Toast.makeText(this, "Enter phone number", Toast.LENGTH_SHORT).show();
                    loginPresenter.setProgressBarVisiblity(View.INVISIBLE);
                    break;
                }
                if (validCheckNumber.length() < 10) {
                    mPhoneNumber.setText("Please Enter Full Number");
                    loginPresenter.setProgressBarVisiblity(View.INVISIBLE);
                    break;
                }
                loginPresenter.setProgressBarVisiblity(View.VISIBLE);
                mGenerateBtn.setEnabled(false);
                loginPresenter.doLogin(mPhoneNumber.getText().toString());
                break;

        }

    }


    @Override
    public void onLoginResult(Boolean result) {
        loginPresenter.setProgressBarVisiblity(View.INVISIBLE);
        mGenerateBtn.setEnabled(true);
        if (result) {
            Toast.makeText(this, "Login Success", Toast.LENGTH_SHORT).show();
            loginPresenter.switchToOTP(this, OtpActivity.class);
        } else
            Toast.makeText(this, "Login Fail, code = ", Toast.LENGTH_SHORT).show();

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onSetProgressBarVisibility(int visibility) {
        progressBar.setVisibility(visibility);

        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        mCurrentUser = mAuth.getCurrentUser();

        mCountryCode = findViewById(R.id.country_code_text);
        mPhoneNumber = findViewById(R.id.phone_number_text);
        mGenerateBtn = findViewById(R.id.generate_btn);
        mLoginProgress = findViewById(R.id.progress_login);
        mLoginFeedbackText = findViewById(R.id.login_form_feedback);
        ProgressBar mprgr = findViewById(R.id.progress_login);
        mprgr.setVisibility(View.INVISIBLE);
        mCountryCode.setText("+92");
        mCountryCode.setInputType(InputType.TYPE_NULL);
        mGenerateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone_number = mPhoneNumber.getText().toString();
                if(phone_number.isEmpty()){
                    mLoginFeedbackText.setText("Please fill in the form to continue.");
                    mLoginFeedbackText.setVisibility(View.VISIBLE);
                }
             if (phone_number.length()<11 || phone_number.length()>11)
                {
                 mLoginFeedbackText.setText("Please enter valid a Phone Number");
                 mLoginFeedbackText.setVisibility(View.VISIBLE);

                }
                else {
                    mLoginProgress.setVisibility(View.VISIBLE);
                    mGenerateBtn.setEnabled(false);
                    mLoginFeedbackText.setText("");
                    //verification();
                    String complete_phone_number =phone_number.substring(1);
                    PhoneAuthProvider.getInstance().verifyPhoneNumber(
                            "+92"+complete_phone_number,
                            60,
                            TimeUnit.SECONDS,
                            LogInActivity.this,
                            mCallbacks
                   );

                }
            }
        });

        mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
           public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
               signInWithPhoneAuthCredential(phoneAuthCredential);
            }

            @Override
            public void onVerificationFailed(FirebaseException e) {

            }

            @Override
            public void onCodeSent(final String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
               super.onCodeSent(s, forceResendingToken);

                new android.os.Handler().postDelayed(
                        new Runnable() {
                            public void run() {
                                Intent otpIntent = new Intent(LogInActivity.this, OtpActivity.class);
                               otpIntent.putExtra("AuthCredentials", s);
                                startActivity(otpIntent);
                            }
                        },
                        1000);
            }
        };


    }

    @Override
   protected void onStart() {
        super.onStart();
        if(mCurrentUser != null){
            sendUserToHome();
        }
    }

 private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(LogInActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                   public void onComplete(@NonNull Task<AuthResult> task) {
                       if (task.isSuccessful()) {
                            sendUserToHome();
                            Log.d("onverification","successful");
                            // ...
                       } else {
                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                              // The verification code entered was invalid
                                mLoginFeedbackText.setVisibility(View.VISIBLE);
                                mLoginFeedbackText.setText("There was an error verifying OTP");
                            }
                        }
                        mLoginProgress.setVisibility(View.INVISIBLE);
                        mGenerateBtn.setEnabled(true);
                    }
                });
    }
// this activity will send us to Home screen
   private void sendUserToHome() {
        Intent homeIntent = new Intent(LogInActivity.this, HomeDashBoard.class);
        homeIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        homeIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(homeIntent);
        Log.d("home","send to home");
        finish();
 }

    @Override
    protected void onResume() {
        super.onResume();
        

    }
}

