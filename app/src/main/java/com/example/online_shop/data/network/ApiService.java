package com.example.online_shop.data.network;

import com.example.online_shop.data.model.Collection;
import com.example.online_shop.data.model.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    @GET("products/")
    Call<List<Product>> getProducts();

    @GET("collections/")
    Call<List<Collection>> getCollections();
}
