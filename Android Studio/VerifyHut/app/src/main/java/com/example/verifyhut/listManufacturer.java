package com.example.verifyhut;

public class listManufacturer {

    private String name;
    private String email;
    private String description;
    private String address;
    private String phone;
    private String ssmID;

    public listManufacturer(String name, String email, String description, String address, String phone, String ssmID){
        this.name = name;
        this.email = email;
        this.description = description;
        this.address = address;
        this.phone = phone;
        this.ssmID = ssmID;
    }

    public listManufacturer(){

    }

    public String getName() { return name; }

    public void setName(String name){
        this.name = name;
    }

    public String getEmail() { return email; }

    public void setEmail(String email){
        this.email = email;
    }

    public String getDescription() { return description; }

    public void setDescription(String description){
        this.description = description;
    }

    public String getAddress() { return address; }

    public void setAddress(String address){
        this.address = address;
    }

    public String getPhone() { return  phone; }

    public void setPhone(String phone){
        this.phone = phone;
    }

    public String getSsmID() { return ssmID; }

    public void setSsmID(String ssmID){
        this.ssmID = ssmID;
    }

}
