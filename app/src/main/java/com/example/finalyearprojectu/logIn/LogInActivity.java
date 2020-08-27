package com.example.finalyearprojectu.logIn;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.finalyearprojectu.R;
import com.example.finalyearprojectu.logIn.logInPattern.presenter.ILogInPresenter;
import com.example.finalyearprojectu.logIn.logInPattern.presenter.LogInPresenter;
import com.example.finalyearprojectu.logIn.logInPattern.view.ILogInView;

public class LogInActivity extends AppCompatActivity implements ILogInView, View.OnClickListener {
    private Button mGenerateBtn;
    private ILogInPresenter loginPresenter;
    private ProgressBar progressBar;
    private EditText phoneNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in2);
        getSupportActionBar().hide();
        mGenerateBtn = this.findViewById(R.id.generate_btn);
        phoneNumber = this.findViewById(R.id.phone_number_text);
        progressBar = this.findViewById(R.id.progress_login);
        mGenerateBtn.setOnClickListener(this);
        loginPresenter = new LogInPresenter(this);
        loginPresenter.setProgressBarVisiblity(View.INVISIBLE);



    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.generate_btn:
                loginPresenter.setProgressBarVisiblity(View.VISIBLE);
                mGenerateBtn.setEnabled(false);
                loginPresenter.doLogin(phoneNumber.getText().toString(),1234);

                break;

        }

    }

    @Override
    public void onLoginResult(Boolean result, int code) {
        loginPresenter.setProgressBarVisiblity(View.INVISIBLE);
        mGenerateBtn.setEnabled(true);
        if (result){
            Toast.makeText(this,"Login Success",Toast.LENGTH_SHORT).show();
            loginPresenter.switchToOTP(this,OtpActivity.class);
        }
        else
            Toast.makeText(this,"Login Fail, code = " + code,Toast.LENGTH_SHORT).show();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onSetProgressBarVisibility(int visibility) {
        progressBar.setVisibility(visibility);

    }
}
