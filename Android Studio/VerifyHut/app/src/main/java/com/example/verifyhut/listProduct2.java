package com.example.verifyhut;

public class listProduct2 {

    private String productName;
    private String brand;
    private String ingredient;
    private String halalCertified;
    private String productDesc;
    private String type;
    private String productID;



    public listProduct2(String productName, String brand, String ingredient, String halalCertified, String productDesc, String type, String productID){

        this.productName = productName;
        this.brand = brand;
        this.ingredient = ingredient;
        this.halalCertified = halalCertified;
        this.productDesc = productDesc;
        this.type = type;
        this.productID = productID;



    }

    public listProduct2() {

    }


    public String getProductName(){
        return productName;
    }

    public void setProductName(String productName){
        this.productName = productName;
    }

    public String getBrand(){
        return brand;
    }

    public void setBrand(String brand){
        this.brand = brand;
    }

    public String getIngredient(){
        return ingredient;
    }

    public void setIngredient(String ingredient){
        this.ingredient = ingredient;
    }

    public String getHalalCertified(){
        return halalCertified;
    }

    public void setHalalCertified(String halalCertified){
        this.halalCertified = halalCertified;
    }

    public String getProductDesc(){
        return productDesc;
    }

    public void setProductDesc(String productDesc){
        this.productDesc = productDesc;
    }

    public String getType(){
        return type;
    }

    public void setType(String type){
        this.type = type;
    }

    public String getProductID(){
        return productID;
    }

    public void setProductID(String productID){
        this.productID = productID;
    }

}
