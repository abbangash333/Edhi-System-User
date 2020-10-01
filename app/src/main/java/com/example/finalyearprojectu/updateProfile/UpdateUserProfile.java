package com.example.finalyearprojectu.updateProfile;

public class UpdateUserProfile {
    public String display_name;
    public String email;
    public String photo_url;
    public String city_name;

    public UpdateUserProfile() {
    }

    public UpdateUserProfile(String display_name, String email, String photo_url, String city_name) {
        this.display_name = display_name;
        this.email = email;
        this.photo_url = photo_url;
        this.city_name = city_name;
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

    public String getCity_name() {
        return city_name;
    }

    public void setCity_name(String city_name) {
        this.city_name = city_name;
    }
}
