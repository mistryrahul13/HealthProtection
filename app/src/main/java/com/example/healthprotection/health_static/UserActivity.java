package com.example.healthprotection.health_static;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.healthprotection.databinding.ActivityUserBinding;

public class UserActivity extends AppCompatActivity {
    private ActivityUserBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUserBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getSupportActionBar().hide();




    }
}