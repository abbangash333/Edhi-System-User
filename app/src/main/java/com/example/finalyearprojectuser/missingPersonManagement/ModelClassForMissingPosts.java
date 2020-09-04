package com.example.finalyearprojectuser.missingPersonManagement;

public class ModelClassForMissingPosts {
    private String nameOfPerson;
    private String belongingCity;
    private String dateOfDisappearance;
    private int age;
    private String nameOfCityD;
    private String missingPhoneContacNumber;

    public ModelClassForMissingPosts(String nameOfPerson, String belongingCity, String DOB, int age, String nameOfCityD, String personMissingDate) {
        this.nameOfPerson = nameOfPerson;
        this.belongingCity = belongingCity;
        this.dateOfDisappearance = DOB;
        this.age = age;
        this.nameOfCityD = nameOfCityD;
        this.missingPhoneContacNumber = personMissingDate;
    }

    public String getNameOfPerson() {
        return nameOfPerson;
    }

    public String getBelongingCity() {
        return belongingCity;
    }

    public String getDateOfDisappearance() {
        return dateOfDisappearance;
    }

    public int getAge() {
        return age;
    }

    public String getNameOfCityD() {
        return nameOfCityD;
    }

    public String getMissingPhoneContacNumber() {
        return missingPhoneContacNumber;
    }
}
