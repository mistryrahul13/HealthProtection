package com.example.healthprotection.health_static;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import com.example.healthprotection.databinding.ActivitySplashScreenBinding;
import com.example.healthprotection.health_static.authentication.UserActivity;

public class SplashScreenActivity extends AppCompatActivity {
    private ActivitySplashScreenBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySplashScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().hide();


        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                }catch (Exception e){

                }
                Intent i = new Intent(SplashScreenActivity.this, UserActivity.class);
                startActivity(i);
                finish();
            }
        }).start();
    }
}