package com.example.healthprotection.health_static.authentication;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.healthprotection.R;
import com.example.healthprotection.databinding.FragmentLoginFragBinding;
import com.example.healthprotection.health_static.DashboardActivity;
import com.example.healthprotection.health_static.Doctor;
import com.example.healthprotection.health_static.admin.AdminActivity;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginFrag extends Fragment {

    private FragmentLoginFragBinding binding;
    private FirebaseAuth auth;
    private FirebaseUser user;
    private DatabaseReference ref;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentLoginFragBinding.inflate(getLayoutInflater());
        return (binding.getRoot());

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        auth =FirebaseAuth.getInstance();
        ref = FirebaseDatabase.getInstance().getReference("Doctor");

        if(FirebaseAuth.getInstance().getCurrentUser()!=null){
            startActivity(new Intent(getActivity(), DashboardActivity.class));
            getActivity().finish();
        }

        binding.fgtpswrd.setOnClickListener(view1 -> {
            Navigation.findNavController(getView()).navigate(R.id.action_loginFrag_to_foregetFrag);
        });

        binding.newuser.setOnClickListener(view1 -> {
            Navigation.findNavController(getView()).navigate(R.id.action_loginFrag_to_signUpFrag);
        });

        user = auth.getCurrentUser();



       binding.btnlogin.setOnClickListener(view1 -> {
           String email = binding.user.getText().toString().trim();
           String password = binding.pswrd.getText().toString().trim();

           if(email.equals("rm@gmail.com") && password.equals("123456")) {

               Intent i = new Intent(getActivity(), AdminActivity.class);
               startActivity(i);

           }


           if (!email.isEmpty() && !password.isEmpty()){
               auth.signInWithEmailAndPassword(email,password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                   @Override
                   public void onSuccess(AuthResult authResult) {

                       ref.addValueEventListener(new ValueEventListener() {
                           @Override
                           public void onDataChange(@NonNull DataSnapshot snapshot) {
                               boolean bool = false;

                               for(DataSnapshot childSnap : snapshot.getChildren()){

                                   Doctor d = childSnap.getValue(Doctor.class);


                                   if(auth.getCurrentUser().getUid().equals(d.getUid())) {

                                       // Doctor Activity
                                       bool = true;
                                       break;

                                   }
                               }

                               if(bool){

                                   Toast.makeText(getActivity(), "Doctor Login", Toast.LENGTH_SHORT).show();
                                   Navigation.findNavController(getView()).navigate(R.id.action_loginFrag_to_doctorActivity);
                                   getActivity().finish();

                               }else{

                                   Toast.makeText(getActivity(), "Welcome", Toast.LENGTH_SHORT).show();
                                   Navigation.findNavController(getView()).navigate(R.id.action_loginFrag_to_dashboardActivity);
                                   getActivity().finish();
                               }
                           }

                           @Override
                           public void onCancelled(@NonNull DatabaseError error) {
                               Log.i("authError",error.toString());
                           }
                       });

                   }
               }).addOnFailureListener(new OnFailureListener() {
                   @Override
                   public void onFailure(@NonNull Exception e) {
                       Toast.makeText(getActivity(),e.getMessage(), Toast.LENGTH_SHORT).show();

                   }
               });

           }else{
               binding.user.setError("Fill the gmail");
           }
           clear();
       });

    }
    void clear(){
        binding.user.setText("");
        binding.pswrd.setText("");
    }
}