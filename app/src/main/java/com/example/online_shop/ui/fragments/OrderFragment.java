package com.example.online_shop.ui.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.online_shop.R;
import com.example.online_shop.databinding.FragmentOrderBinding;

public class OrderFragment extends Fragment {
    private FragmentOrderBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentOrderBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }
}