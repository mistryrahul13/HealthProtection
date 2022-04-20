package com.example.healthprotection.health_static;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.healthprotection.R;
import com.example.healthprotection.databinding.FragmentForegetBinding;


public class ForegetFrag extends Fragment {

    private FragmentForegetBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding= FragmentForegetBinding.inflate(getLayoutInflater());
        return (binding.getRoot());
    }
}