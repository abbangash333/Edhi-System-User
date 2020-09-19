package com.example.finalyearprojectu.centersContactInformation;

public class CenterDetail {
    private int number;
    private String addressOfTheCenter;

    public CenterDetail(int number, String addressOfTheCenter) {
        this.number = number;
        this.addressOfTheCenter = addressOfTheCenter;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getAddressOfTheCenter() {
        return addressOfTheCenter;
    }

    public void setAddressOfTheCenter(String addressOfTheCenter) {
        this.addressOfTheCenter = addressOfTheCenter;
    }
}
