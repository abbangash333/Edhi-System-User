package com.example.finalyearprojectu.missingPersonManagement;

public class ModelClassForMissingPosts {
    private String nameOfPerson;
    private String belongingCity;
    private String DOB;
    private int age;
    private String nameOfCityD;
    private String personMissingDate;

    public ModelClassForMissingPosts(String nameOfPerson, String belongingCity, String DOB, int age, String nameOfCityD, String personMissingDate) {
        this.nameOfPerson = nameOfPerson;
        this.belongingCity = belongingCity;
        this.DOB = DOB;
        this.age = age;
        this.nameOfCityD = nameOfCityD;
        this.personMissingDate = personMissingDate;
    }

    public String getNameOfPerson() {
        return nameOfPerson;
    }

    public String getBelongingCity() {
        return belongingCity;
    }

    public String getDOB() {
        return DOB;
    }

    public int getAge() {
        return age;
    }

    public String getNameOfCityD() {
        return nameOfCityD;
    }

    public String getPersonMissingDate() {
        return personMissingDate;
    }
}
