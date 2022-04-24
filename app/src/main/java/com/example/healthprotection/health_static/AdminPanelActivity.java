package com.example.healthprotection.health_static;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.healthprotection.databinding.ActivityAdminPanelBinding;

public class AdminPanelActivity extends AppCompatActivity {
    private ActivityAdminPanelBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAdminPanelBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());



    }



}