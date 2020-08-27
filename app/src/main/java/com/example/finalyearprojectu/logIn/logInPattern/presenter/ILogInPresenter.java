package com.example.finalyearprojectu.logIn.logInPattern.presenter;

import com.example.finalyearprojectu.logIn.LogInActivity;
import com.example.finalyearprojectu.logIn.OtpActivity;

public interface ILogInPresenter {
    void doLogin(String name, int OTP);
    void setProgressBarVisiblity(int visiblity);
    void switchToOTP(LogInActivity logInActivity, Class<OtpActivity> otpActivityClass);

}
