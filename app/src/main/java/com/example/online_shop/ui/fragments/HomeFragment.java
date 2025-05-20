package com.example.online_shop.ui.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.online_shop.R;
import com.example.online_shop.adapter.CategoryAdapter;
import com.example.online_shop.data.network.ApiService;
import com.example.online_shop.databinding.FragmentHomeBinding;


public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private CategoryAdapter categoryAdapter;

    private ProgressBar progressBarCategory;
    private ProgressBar progressBar;
    private ApiService apiService;



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
        initializeViews();
        setupRetrofit();
        loadData();
    }

    private void initializeViews(){
        binding.catView.setLayoutManager(
                new LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
        );
        categoryAdapter = new CategoryAdapter();
        binding.catView.setAdapter(categoryAdapter);
        progressBarCategory = binding.progressBarCategory;

    }

    private void setupRetrofit(){

    }

    private void loadData(){

    }

    private void fetchCollections(){
        
    }

    private void fetchProducts(){

    }

    private void showError(){

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}