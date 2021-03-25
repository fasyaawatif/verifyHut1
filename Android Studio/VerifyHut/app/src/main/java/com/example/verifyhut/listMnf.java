package com.example.verifyhut;

public class listMnf {
    private String ssmID;
    private String email;
    private String name;
    private String description;
    private String phone;
    private String address;


    public listMnf(String ssmID, String email, String name, String description, String phone, String address){

        this.ssmID = ssmID;
        this.email = email;
        this.name = name;
        this.description = description;
        this.phone = phone;
        this.address = address;

    }
    public String getSsmID() { return ssmID; }
    public String getEmail() { return email; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public String getPhone() { return  phone; }
    public String getAddress() { return address; }

}
