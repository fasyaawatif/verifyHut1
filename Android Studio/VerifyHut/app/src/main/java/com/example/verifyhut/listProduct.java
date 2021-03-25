package com.example.verifyhut;

public class listProduct {

    private String productName;
    private String brand;
    private String ingredient;
    private String halalCertified;
    private String productDesc;
    private String type;
    private String productID;
    private String status;


    public listProduct(String productName, String brand, String ingredient, String halalCertified, String productDesc, String type, String productID, String status){

        this.productName = productName;
        this.brand = brand;
        this.ingredient = ingredient;
        this.halalCertified = halalCertified;
        this.productDesc = productDesc;
        this.type = type;
        this.productID = productID;
        this.status = status;


    }

    public listProduct() {

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

    public String getStatus(){ return status; }

    public void setStatus(String status){ this.status = status;}
}
