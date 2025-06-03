package com.example.online_shop.ui.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.online_shop.R;
import com.example.online_shop.adapter.CategoryAdapter;
import com.example.online_shop.adapter.ProductAdapter;
import com.example.online_shop.data.network.ApiService;
import com.example.online_shop.databinding.FragmentHomeBinding;

import com.example.online_shop.ViewModels.HomeViewModel;
import com.example.online_shop.adapter.CategoryAdapter;

import com.example.online_shop.data.network.ApiService;
import com.example.online_shop.data.model.Product;
import com.example.online_shop.data.model.Collection;
import com.example.online_shop.util.Constants;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private CategoryAdapter categoryAdapter;
    private ProductAdapter productAdapter;
    private ProgressBar progressBarCategory;
    private ProgressBar progressBar;
    private ApiService apiService;
    private HomeViewModel viewModel;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        return binding.getRoot();
//        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        initializeViews();
        setupRetrofit();
        setupViewModel();
        setupRecyclerViews();
        observeViewModel();
        loadData();
        setupPaginationButtons();
    }


    private void setupRetrofit(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiService = retrofit.create(ApiService.class);
    }

    private void setupViewModel(){
        viewModel = new ViewModelProvider(requireActivity()).get(HomeViewModel.class);
        viewModel.setApiService(apiService);
    }

    private void setupRecyclerViews(){
        categoryAdapter = new CategoryAdapter();
        binding.catView.setLayoutManager(
                new LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
        );
        binding.catView.setAdapter(categoryAdapter);

        productAdapter = new ProductAdapter();
        binding.bestDealView.setLayoutManager(
                new GridLayoutManager(requireContext(),2)
        );
        binding.bestDealView.setAdapter(productAdapter);
    }

    private void observeViewModel(){
        viewModel.getCollections().observe(getViewLifecycleOwner(),collections -> {
            binding.progressBarCategory.setVisibility(View.GONE);
            categoryAdapter.setCollections(collections);
        });

        viewModel.getProducts().observe(getViewLifecycleOwner(), products -> {
            binding.progressBarBestDeal.setVisibility(View.GONE);
            productAdapter.setProducts(products);
        });

        viewModel.getErrorMessage().observe(getViewLifecycleOwner(),message ->{
            binding.progressBarCategory.setVisibility(View.GONE);
            Toast.makeText(requireContext(),message,Toast.LENGTH_LONG).show();
        });
    }

    private void loadData() {
        if (viewModel.getCollections().getValue() == null) {
            binding.progressBarCategory.setVisibility(View.VISIBLE);
            viewModel.fetchCollections();
        }

        if (viewModel.getProducts().getValue() == null) {
            binding.progressBarBestDeal.setVisibility(View.VISIBLE);
            viewModel.fetchProducts();
        }
    }


    private void setupPaginationButtons() {
        binding.nextButton.setOnClickListener(v -> {
            viewModel.loadNextPage();
        });

        binding.prevButton.setOnClickListener(v -> {
            viewModel.loadPreviousPage();
        });
    }



    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}