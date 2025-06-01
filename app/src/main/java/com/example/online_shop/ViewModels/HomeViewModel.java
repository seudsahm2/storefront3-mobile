package com.example.online_shop.ViewModels;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.online_shop.data.model.Collection;
import com.example.online_shop.data.model.Product;
import com.example.online_shop.data.model.ProductResponse;
import com.example.online_shop.data.network.ApiService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeViewModel extends ViewModel {
    private final MutableLiveData<List<Product>> product = new MutableLiveData<>();
    private final MutableLiveData<List<Collection>> collection = new MutableLiveData<>();
    private final MutableLiveData<String> errorMessage = new MutableLiveData<>();

    private ApiService apiService;
    private String nextPageUrl = null;
    private String prevPageUrl = null;


    public void setApiService(ApiService apiService){
        this.apiService = apiService;
    }

    public void fetchProducts() {
        apiService.getProducts().enqueue(new Callback<ProductResponse>() {
            @Override
            public void onResponse(Call<ProductResponse> call, Response<ProductResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    nextPageUrl = response.body().getNext();
                    prevPageUrl = response.body().getPrevious();
                    product.setValue(response.body().getResults());
                } else {
                    errorMessage.setValue("Failed to load Products");
                }
            }

            @Override
            public void onFailure(Call<ProductResponse> call, Throwable t) {
                errorMessage.setValue("Error: " + t.getMessage());
            }
        });
    }

    public void loadNextPage() {
        if (nextPageUrl != null) {
            apiService.getProductsByUrl(nextPageUrl).enqueue(new Callback<ProductResponse>() {
                @Override
                public void onResponse(Call<ProductResponse> call, Response<ProductResponse> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        nextPageUrl = response.body().getNext();
                        prevPageUrl = response.body().getPrevious();
                        product.setValue(response.body().getResults());
                    }
                }

                @Override
                public void onFailure(Call<ProductResponse> call, Throwable t) {
                    errorMessage.setValue("Error: " + t.getMessage());
                }
            });
        }
    }

    public void loadPreviousPage() {
        if (prevPageUrl != null) {
            apiService.getProductsByUrl(prevPageUrl).enqueue(new Callback<ProductResponse>() {
                @Override
                public void onResponse(Call<ProductResponse> call, Response<ProductResponse> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        nextPageUrl = response.body().getNext();
                        prevPageUrl = response.body().getPrevious();
                        product.setValue(response.body().getResults());
                    }
                }

                @Override
                public void onFailure(Call<ProductResponse> call, Throwable t) {
                    errorMessage.setValue("Error: " + t.getMessage());
                }
            });
        }
    }




    public void fetchCollections(){
        apiService.getCollections().enqueue(new Callback<List<Collection>>() {
            @Override
            public void onResponse(Call<List<Collection>> call, Response<List<Collection>> response) {
                if(response.isSuccessful() && response != null){
                    collection.setValue(response.body());
                }else{
                    errorMessage.setValue("Failed to load Collections");
                }
            }

            @Override
            public void onFailure(Call<List<Collection>> call, Throwable t) {
                errorMessage.setValue("Error: "+ t.getMessage());
            }
        });
    }

    public LiveData<List<Product>> getProducts(){
        return product;
    }

    public LiveData<List<Collection>> getCollections(){
        return collection;
    }

    public LiveData<String> getErrorMessage(){
        return errorMessage;
    }
}
