package com.example.online_shop.data.network;

import com.example.online_shop.data.model.Collection;
import com.example.online_shop.data.model.Product;
import com.example.online_shop.data.model.ProductResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;


public interface ApiService {
   @GET("collections/")
   Call<List<Collection>> getCollections();

   @GET("products/")
   Call<ProductResponse> getProducts();
   @GET
   Call<ProductResponse> getProductsByUrl(@Url String url);

}
