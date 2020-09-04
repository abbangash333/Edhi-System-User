package com.example.finalyearprojectuser.edhiBloodBand;

public class ModelClassForBloodPosts {
    private String request;
    private String desc;

    public ModelClassForBloodPosts(String request, String desc) {
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
