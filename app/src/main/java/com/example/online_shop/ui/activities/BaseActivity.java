package com.example.online_shop.ui.activities;

import android.os.Bundle;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // to draw the layout on th entire screen. including the status bar and the navigation bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        ,WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
    }
}