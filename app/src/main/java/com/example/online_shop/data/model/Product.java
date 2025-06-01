package com.example.online_shop.data.model;

import com.google.gson.annotations.SerializedName;

public class Product {
    @SerializedName("id")
    private int id;
    @SerializedName("title")
    private String title;
    @SerializedName("description")
    private String description;
    @SerializedName("unit_price")
    private double unitPrice;
    @SerializedName("pricee_with_tax")
    private double priceWithTax;
    @SerializedName("inventory")
    private int inventory;
    @SerializedName("collection")
    private int collectionId;

    public int getId(){
        return id;
    }
    public String getTitle(){
        return title;
    }
    public String getDescription(){
        return description;
    }
    public double getUnitPrice(){
        return unitPrice;
    }
    public double getPriceWithTax(){
        return priceWithTax;
    }
    public int getInventory(){
        return inventory;
    }
    public int getCollectionId(){
        return collectionId;
    }
}
