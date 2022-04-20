package com.example.healthprotection.health_static;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
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
import com.example.healthprotection.databinding.FragmentSignUpBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.lang.annotation.Native;


public class SignUpFrag extends Fragment {

    private FragmentSignUpBinding binding;
    private FirebaseAuth auth;
    private DatabaseReference ref;
    private StorageReference storageref;
    private Uri uri=null;
    private String uid;

    private ActivityResultLauncher<String>gallery;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding= FragmentSignUpBinding.inflate(getLayoutInflater());
        return (binding.getRoot());
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.signin.setOnClickListener(view1 -> {
            Navigation.findNavController(getView()).navigate(R.id.action_signUpFrag_to_loginFrag2);
        });

        auth = FirebaseAuth.getInstance();

        ref = FirebaseDatabase.getInstance().getReference("User");


        gallery= registerForActivityResult(new ActivityResultContracts.GetContent(), new ActivityResultCallback<Uri>() {
            @Override
            public void onActivityResult(Uri result) {
                uri = result;
                binding.ivImage.setImageURI(result);

            }

        });

        binding.ivImage.setOnClickListener(view1 -> {
            gallery.launch("image/*");

        });

        binding.Register.setOnClickListener(view1 -> {

            String name = binding.LN.getText().toString();
            String email = binding.Email.getText().toString();
            String password = binding.Pswrd.getText().toString();
            String repeatpassword = binding.Rptpswrd.getText().toString();

            auth.createUserWithEmailAndPassword(email,password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                @Override
                public void onSuccess(AuthResult authResult) {

                    uid = auth.getCurrentUser().getUid();

                    storageref= FirebaseStorage.getInstance().getReference("images").child("userImage").child(uid);
                    storageref.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {

                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                            storageref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {

                                    String userId = uri.toString();

                                    User user = new User(name,email,uid,userId);
                                    ref.push().setValue(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void unused){
                                            Toast.makeText(getActivity(), "Success", Toast.LENGTH_SHORT).show();
                                            Navigation.findNavController(getView()).navigate(R.id.action_signUpFrag_to_loginFrag2);
                                        }
                                    }).addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Log.i("Myerror",e.toString());
                                        }
                                    });

                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.i("Myerror",e.toString());
                                }
                            });
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.i("Myerror",e.toString());

                        }
                    });
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Log.i("Myerror", e.toString());
                }
            });
        });
    }
}