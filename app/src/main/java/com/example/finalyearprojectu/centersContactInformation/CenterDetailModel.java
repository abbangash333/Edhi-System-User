package com.example.finalyearprojectu.centersContactInformation;

public class CenterDetailModel {
    private String center_number;
    private String center_address;
    public  String name_center;

    public CenterDetailModel() {
    }

    public CenterDetailModel(String center_number, String center_address, String name_center) {
        this.center_number = center_number;
        this.center_address = center_address;
        this.name_center = name_center;
    }

    public String getCenter_number() {
        return center_number;
    }

    public void setCenter_number(String center_number) {
        this.center_number = center_number;
    }

    public String getCenter_address() {
        return center_address;
    }

    public void setCenter_address(String center_address) {
        this.center_address = center_address;
    }

    public String getName_center() {
        return name_center;
    }

    public void setName_center(String name_center) {
        this.name_center = name_center;
    }
}
