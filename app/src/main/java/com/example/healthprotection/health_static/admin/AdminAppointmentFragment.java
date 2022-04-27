package com.example.healthprotection.health_static.admin;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.healthprotection.R;
import com.example.healthprotection.databinding.FragmentAdminAppointmentBinding;

public class AdminAppointmentFragment extends Fragment {
    private FragmentAdminAppointmentBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentAdminAppointmentBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }
}