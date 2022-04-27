package com.example.healthprotection.health_static;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Gravity;

import com.example.healthprotection.databinding.ActivityDashboardBinding;

public class DashboardActivityRahul extends AppCompatActivity {
    private ActivityDashboardBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityDashboardBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getSupportActionBar().hide();

//        binding.ab.ctb.setNavigationOnClickListener(view -> {
//            binding.drawercustom.openDrawer(Gravity.LEFT);
//        });





    }
}