package com.example.finalyearprojectuser.homeSearchAndNotification.postmissingdetail;

public class PostMissingModel {
    public String address;
    public  String age;
    public  String city;
    public String disappeared_date;
    public String dissappeared_city;
    public String gender;
    public String image_url;
    public String missing_key;
    public String missing_name;
    public String missing_status;
    public String phone_number;
    public String user_key;

    public PostMissingModel() {
    }

    public PostMissingModel(String address, String age, String city,
                            String disappeared_date, String dissappeared_city,
                            String gender, String image_url,
                            String missing_key, String missing_name,
                            String missing_status,
                            String phone_number,
                            String user_key) {
        this.address = address;
        this.age = age;
        this.city = city;
        this.disappeared_date = disappeared_date;
        this.dissappeared_city = dissappeared_city;
        this.gender = gender;
        this.image_url = image_url;
        this.missing_key = missing_key;
        this.missing_name = missing_name;
        this.missing_status = missing_status;
        this.phone_number = phone_number;
        this.user_key = user_key;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDisappeared_date() {
        return disappeared_date;
    }

    public void setDisappeared_date(String disappeared_date) {
        this.disappeared_date = disappeared_date;
    }

    public String getDissappeared_city() {
        return dissappeared_city;
    }

    public void setDissappeared_city(String dissappeared_city) {
        this.dissappeared_city = dissappeared_city;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getMissing_key() {
        return missing_key;
    }

    public void setMissing_key(String missing_key) {
        this.missing_key = missing_key;
    }

    public String getMissing_name() {
        return missing_name;
    }

    public void setMissing_name(String missing_name) {
        this.missing_name = missing_name;
    }

    public String getMissing_status() {
        return missing_status;
    }

    public void setMissing_status(String missing_status) {
        this.missing_status = missing_status;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getUser_key() {
        return user_key;
    }

    public void setUser_key(String user_key) {
        this.user_key = user_key;
    }
}
