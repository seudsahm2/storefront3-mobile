package com.example.online_shop.ui.activities;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;

import com.example.online_shop.R;
import com.example.online_shop.databinding.ActivityMainBinding;
import com.example.online_shop.ui.fragments.CartFragment;
import com.example.online_shop.ui.fragments.HomeFragment;
import com.example.online_shop.ui.fragments.OrderFragment;
import com.example.online_shop.ui.fragments.ProfileFragment;
import com.example.online_shop.ui.fragments.WishListFragment;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        loadFragment(new HomeFragment());

        binding.bottomNav.setOnItemSelectedListener(item->{
            Fragment selectedFragment = null;

            if(item.getItemId() == R.id.nav_home){
                selectedFragment = new HomeFragment();
            }else if(item.getItemId() == R.id.nav_cart){
                selectedFragment = new CartFragment();
            }else if(item.getItemId() == R.id.nav_wishlist){
                selectedFragment = new WishListFragment();
            }else if(item.getItemId() == R.id.nav_order){
                selectedFragment = new OrderFragment();
            }else if(item.getItemId() == R.id.nav_profile){
                selectedFragment = new ProfileFragment();
            }
            if (selectedFragment != null){
                loadFragment(selectedFragment);
                return true;
            }

            return true;
        });

    }

    private void loadFragment(Fragment fragment){
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit();
    }
}