package com.example.finalyearprojectu.centerManagement;

public class CenterManagementModelClass {
    private String center_name;
    private String total_children;
    private String total_widows;
    private String total_orphans;
    private String  a_group;
    private String  a_positive_group;
    private String a_negative_group;
    private String ab_group;
    private String o_positive_group;
    private String o_negative_group;
    private String b_positive_group;
    private String b_negative_group;

    public CenterManagementModelClass() {
    }

    public CenterManagementModelClass(String center_name,
                                      String total_children,
                                      String total_widows,
                                      String total_orphans,
                                      String a_group,
                                      String a_positive_group,
                                      String a_negative_group,
                                      String ab_group,
                                      String o_positive_group,
                                      String o_negative_group,
                                      String b_positive_group,
                                      String b_negative_group) {
        this.center_name = center_name;
        this.total_children = total_children;
        this.total_widows = total_widows;
        this.total_orphans = total_orphans;
        this.a_group = a_group;
        this.a_positive_group = a_positive_group;
        this.a_negative_group = a_negative_group;
        this.ab_group = ab_group;
        this.o_positive_group = o_positive_group;
        this.o_negative_group = o_negative_group;
        this.b_positive_group = b_positive_group;
        this.b_negative_group = b_negative_group;
    }

    public String getCenter_name() {
        return center_name;
    }

    public void setCenter_name(String center_name) {
        this.center_name = center_name;
    }

    public String getTotal_children() {
        return total_children;
    }

    public void setTotal_children(String total_children) {
        this.total_children = total_children;
    }

    public String getTotal_widows() {
        return total_widows;
    }

    public void setTotal_widows(String total_widows) {
        this.total_widows = total_widows;
    }

    public String getTotal_orphans() {
        return total_orphans;
    }

    public void setTotal_orphans(String total_orphans) {
        this.total_orphans = total_orphans;
    }

    public String getA_group() {
        return a_group;
    }

    public void setA_group(String a_group) {
        this.a_group = a_group;
    }

    public String getA_positive_group() {
        return a_positive_group;
    }

    public void setA_positive_group(String a_positive_group) {
        this.a_positive_group = a_positive_group;
    }

    public String getA_negative_group() {
        return a_negative_group;
    }

    public void setA_negative_group(String a_negative_group) {
        this.a_negative_group = a_negative_group;
    }

    public String getAb_group() {
        return ab_group;
    }

    public void setAb_group(String ab_group) {
        this.ab_group = ab_group;
    }

    public String getO_positive_group() {
        return o_positive_group;
    }

    public void setO_positive_group(String o_positive_group) {
        this.o_positive_group = o_positive_group;
    }

    public String getO_negative_group() {
        return o_negative_group;
    }

    public void setO_negative_group(String o_negative_group) {
        this.o_negative_group = o_negative_group;
    }

    public String getB_positive_group() {
        return b_positive_group;
    }

    public void setB_positive_group(String b_positive_group) {
        this.b_positive_group = b_positive_group;
    }

    public String getB_negative_group() {
        return b_negative_group;
    }

    public void setB_negative_group(String b_negative_group) {
        this.b_negative_group = b_negative_group;
    }
}
