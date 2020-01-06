package com.example.finalyearprojectu.edhiBloodBand;

public class ModelClassForBloodNotification {
    private String request;
    private String desc;

    public ModelClassForBloodNotification(String request, String desc) {
        this.request = request;
        this.desc = desc;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
