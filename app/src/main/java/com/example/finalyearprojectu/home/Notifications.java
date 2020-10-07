package com.example.finalyearprojectu.home;

public class Notifications {
    public Double lati;
    public Double longi;

    public Notifications() {
    }

    public Notifications(Double lati, Double longi) {
        this.lati = lati;
        this.longi = longi;
    }

    public Double getLati() {
        return lati;
    }

    public void setLati(Double lati) {
        this.lati = lati;
    }

    public Double getLongi() {
        return longi;
    }

    public void setLongi(Double longi) {
        this.longi = longi;
    }
}
