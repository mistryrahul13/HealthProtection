package com.example.healthprotection.health_static.doctor_activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.healthprotection.databinding.ActivityDoctorBinding;

public class DoctorActivity extends AppCompatActivity {
    private ActivityDoctorBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDoctorBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}