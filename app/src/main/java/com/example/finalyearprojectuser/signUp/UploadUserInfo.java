package com.example.finalyearprojectuser.signUp;

public class UploadUserInfo {
    public String display_name;
    public String email;
    public String photo_url;
    public UploadUserInfo(){}

    public UploadUserInfo(String display_name, String email, String photo_url) {
        this.display_name = display_name;
        this.email = email;
        this.photo_url = photo_url;
    }

    public String getDisplay_name() {
        return display_name;
    }

    public void setDisplay_name(String display_name) {
        this.display_name = display_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoto_url() {
        return photo_url;
    }

    public void setPhoto_url(String photo_url) {
        this.photo_url = photo_url;
    }
}
