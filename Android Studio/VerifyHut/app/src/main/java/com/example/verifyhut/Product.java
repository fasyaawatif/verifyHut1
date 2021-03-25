package com.example.verifyhut;

public class Product {

    private String pproductID;
    private String pproductName;
    private String pbrand;
    private String pproductDesc;
    private String ptype;
    private String pingredient;
    private String phalal;

    public Product(String productID, String productName, String brand, String productDesc, String type, String ingredient, String halalCertified){

        this.pproductID = productID;
        this.pproductName = productName;
        this.pbrand = brand;
        this.pproductDesc =productDesc;
        this.ptype = type;
        this.pingredient = ingredient;
        this.phalal = halalCertified;

    }

    public String getPProductID(){
        return pproductID;
    }

    public String getPProductName(){
        return pproductName;
    }

    public String getPBrand(){
        return pbrand;
    }

    public String getPProductDesc(){
        return pproductDesc;
    }

    public String getPType() { return ptype; }

    public String getPIngredient(){
        return pingredient;
    }

    public String getPHalal() { return phalal; }

}