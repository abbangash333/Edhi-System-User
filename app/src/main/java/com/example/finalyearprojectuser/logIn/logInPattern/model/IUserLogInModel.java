package com.example.finalyearprojectuser.logIn.logInPattern.model;

public interface IUserLogInModel {
    String getNumber();
    int  getOTP();
    int checkValidity(String phoneNumber);
}
