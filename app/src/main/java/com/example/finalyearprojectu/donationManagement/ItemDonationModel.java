package com.example.finalyearprojectu.donationManagement;

public class ItemDonationModel {
    String donor_number;
    String donor_address;
    String item_price;
    String donor_city;

    public ItemDonationModel() {
    }

    public ItemDonationModel(String donor_number, String donor_address, String item_price, String donor_city) {
        this.donor_number = donor_number;
        this.donor_address = donor_address;
        this.item_price = item_price;
        this.donor_city = donor_city;
    }

    public String getDonor_number() {
        return donor_number;
    }

    public void setDonor_number(String donor_number) {
        this.donor_number = donor_number;
    }

    public String getDonor_address() {
        return donor_address;
    }

    public void setDonor_address(String donor_address) {
        this.donor_address = donor_address;
    }

    public String getItem_price() {
        return item_price;
    }

    public void setItem_price(String item_price) {
        this.item_price = item_price;
    }

    public String getDonor_city() {
        return donor_city;
    }

    public void setDonor_city(String donor_city) {
        this.donor_city = donor_city;
    }
}
