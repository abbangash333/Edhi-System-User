package com.example.finalyearprojectu.logIn.logInPattern.presenter;
import com.example.finalyearprojectu.logIn.LogInActivity;
import com.example.finalyearprojectu.logIn.OtpActivity;
import com.example.finalyearprojectu.logIn.logInPattern.model.IUserLogInModel;
import com.example.finalyearprojectu.logIn.logInPattern.model.UserLogInModel;
import com.example.finalyearprojectu.logIn.logInPattern.view.ILogInView;

import android.content.Intent;
import android.os.Handler;
import android.os.Looper;

public class LogInPresenter implements ILogInPresenter {
    ILogInView iLogInView;
    IUserLogInModel user;
    Handler handler;

    public LogInPresenter(ILogInView iLogInView) {
        this.iLogInView = iLogInView;
        initUser();
        handler = new Handler(Looper.getMainLooper());
    }

    @Override
    public void doLogin(String phoneNumber, int OTP) {
        Boolean isLoginSuccess = true;
        final int code = user.checkValidity(phoneNumber,OTP);
        if (code!=0) isLoginSuccess = false;
        final Boolean result = isLoginSuccess;
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                iLogInView.onLoginResult(result, code);
            }
        }, 2000);
    }

    @Override
    public void setProgressBarVisiblity(int visiblity) {
        iLogInView.onSetProgressBarVisibility(visiblity);

    }

    @Override
    public void switchToOTP(LogInActivity logInActivity, Class<OtpActivity> otpActivityClass) {
        Intent intent = new Intent(logInActivity, otpActivityClass);
        logInActivity.startActivity(intent);
    }

    private void initUser()
    {
        user = new UserLogInModel("03339601461",1234);
    }
}
