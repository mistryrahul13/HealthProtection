package com.example.healthprotection.health_static.admin.test;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.healthprotection.R;
import com.example.healthprotection.databinding.FragmentNewAdminTestrpBinding;

public class NewAdminTestrpFragment extends Fragment {
    private FragmentNewAdminTestrpBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentNewAdminTestrpBinding.inflate(getLayoutInflater());
        // Inflate the layout for this fragment
        return binding.getRoot();
    }
}