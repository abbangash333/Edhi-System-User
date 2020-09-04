package com.example.finalyearprojectuser.logIn.logInPattern.presenter;
import com.example.finalyearprojectuser.logIn.logInPattern.view.LogInActivity;
import com.example.finalyearprojectuser.logIn.OtpPattern.OtpActivity;
import com.example.finalyearprojectuser.logIn.logInPattern.model.IUserLogInModel;
import com.example.finalyearprojectuser.logIn.logInPattern.model.UserLogInModel;
import com.example.finalyearprojectuser.logIn.logInPattern.view.ILogInView;

import android.content.Intent;
import android.os.Handler;
import android.os.Looper;

public class LogInPresenter implements ILogInPresenter {
    ILogInView iLogInView;
    IUserLogInModel user;
    Handler handler;

    public LogInPresenter(ILogInView iLogInView) {
        this.iLogInView = iLogInView;
//        initUser();
        handler = new Handler(Looper.getMainLooper());
    }

    @Override
    public void doLogin(String phoneNumber) {
//        Boolean isLoginSuccess = true;
//        final int code = user.checkValidity(phoneNumber);
//        if (code!=0) isLoginSuccess = false;
//        final Boolean result = isLoginSuccess;
        final Boolean result = true;
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
               iLogInView.onLoginResult(result);
            }
        }, 2000);
    }

    @Override
    public void initUser(String number) {
        user = new UserLogInModel(number);
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

//    public void initUser()
//    {
//      user = new UserLogInModel("03339601461");
//
//
//    }
}
