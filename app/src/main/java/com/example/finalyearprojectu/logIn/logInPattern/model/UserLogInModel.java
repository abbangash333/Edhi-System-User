package com.example.finalyearprojectu.logIn.logInPattern.model;

public class UserLogInModel implements IUserLogInModel {
    String phonNumber;
    int OTP;

    public UserLogInModel(String phonNumber, int OTP) {
        this.phonNumber = phonNumber;
        this.OTP = OTP;
    }

    @Override
    public String getNumber() {
        return phonNumber;
    }

    @Override
    public int getOTP() {
        return OTP;
    }

    @Override
    public int checkValidity(String phoneNumber, int OTP) {
        if (phoneNumber == null || OTP <4 || !phoneNumber.equals(getNumber()) || OTP != (getOTP())) {
            return -1;
        }
        return 0;
    }
}