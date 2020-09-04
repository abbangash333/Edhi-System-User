package com.example.finalyearprojectuser.logIn.logInPattern.model;

public class UserLogInModel implements IUserLogInModel {
    String phoneNumber;
    int OTP;

    public UserLogInModel(String phonNumber) {
        this.phoneNumber = phonNumber;
    }

    @Override
    public String getNumber() {
        return phoneNumber;
    }

    @Override
    public int getOTP() {
        return OTP;
    }


    @Override
    public int checkValidity(String phoneNumber) {
        if (phoneNumber.isEmpty()||phoneNumber.length()<10 ) {
            return -1;
        }
        return 0;
    }
}