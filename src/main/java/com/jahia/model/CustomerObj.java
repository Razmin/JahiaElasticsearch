package com.jahia.model;

public class CustomerObj {

    private String id;
    private String fName;
    private String lName;

    public CustomerObj(String id, CustomerModel customerModel) {
        this.id = id;
        this.fName = customerModel.getfName();
        this.lName = customerModel.getlName();
    }

    public CustomerObj() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }
}
