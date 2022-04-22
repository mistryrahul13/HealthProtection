package com.example.healthprotection.health_static;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.healthprotection.R;
import com.example.healthprotection.databinding.FragmentLoginFragBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginFrag extends Fragment {

    private FragmentLoginFragBinding binding;
    private FirebaseAuth auth;
    private FirebaseUser user;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentLoginFragBinding.inflate(getLayoutInflater());
        return (binding.getRoot());

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.fgtpswrd.setOnClickListener(view1 -> {
            Navigation.findNavController(getView()).navigate(R.id.action_loginFrag_to_foregetFrag);
        });

        binding.newuser.setOnClickListener(view1 -> {
            Navigation.findNavController(getView()).navigate(R.id.action_loginFrag_to_signUpFrag);
        });

        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();



       binding.btnlogin.setOnClickListener(view1 -> {
           String email = binding.user.getText().toString().trim();
           String password = binding.pswrd.getText().toString().trim();



           if(!email.isEmpty()&& !password.isEmpty()){
               auth.signInWithEmailAndPassword(email,password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                   @Override
                   public void onSuccess(AuthResult authResult) {

                       Toast.makeText(getActivity(), "Welcome", Toast.LENGTH_SHORT).show();
                       Navigation.findNavController(getView()).navigate(R.id.action_loginFrag_to_dashboardActivity);

                   }
               }).addOnFailureListener(new OnFailureListener() {
                   @Override
                   public void onFailure(@NonNull Exception e) {
                       Toast.makeText(getActivity(),e.getMessage(), Toast.LENGTH_SHORT).show();

                   }
               });
           }





       });

    }
}