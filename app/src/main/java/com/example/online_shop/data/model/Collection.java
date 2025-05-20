package com.example.online_shop.data.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Collection {
    @SerializedName("id")
    private int id;
    @SerializedName("title")
    private String title;
    @SerializedName("products_count")
    private int productsCount;

    public int getId(){
        return id;
    }

    public String getTitle(){
        return title;
    }

    public int getProductsCount(){
        return productsCount;
    }
}
