package com.example.healthprotection.health_static.userAppoinment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.example.healthprotection.R;
import com.example.healthprotection.databinding.FragmentUserAcceptAppointmentBinding;
import com.example.healthprotection.health_static.Appointment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class UserAcceptAppointmentFragment extends Fragment {
    private FragmentUserAcceptAppointmentBinding binding;
    private DatabaseReference ref;
    private FirebaseAuth auth;
    private ArrayList<Appointment> list;
    private ArrayList<String> keys;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentUserAcceptAppointmentBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        list = new ArrayList<>();
        keys = new ArrayList<>();

        ArrayAdapter<Appointment> adapter = new ArrayAdapter<>(getActivity(), R.layout.fragment_add_doctor_details,list);


        binding.listViewUserAppointmentacc.setAdapter(adapter);
        auth = FirebaseAuth.getInstance();
        ref = FirebaseDatabase.getInstance().getReference("appointment");

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();
                keys.clear();
                for(DataSnapshot childSnap : snapshot.getChildren()){

                    Appointment appointment = childSnap.getValue(Appointment.class);

                    if(auth.getCurrentUser().getUid().equals(appointment.getPatientId()) && appointment.getStatus().equals("approved")){

                        list.add(appointment);

                    }
                    keys.add(childSnap.getKey());
                }
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // log lelo
            }
        });
        binding.listViewUserAppointmentacc.setOnItemClickListener((adapterView, view1, i, l) -> {

            String key = keys.get(i);
            new AlertDialog.Builder(getActivity())
                    .setTitle("Delete this appointment ?")
                    .setCancelable(false)
                    .setPositiveButton("Delete",(dialogInterface, i1) -> {
                        new AlertDialog.Builder(getActivity())
                                .setTitle("Sure you delete this appointment?")
                                .setCancelable(false)
                                .setPositiveButton("Delete",(dialogInterface1, i2) ->{
                                    Toast.makeText(getActivity(), "Delete", Toast.LENGTH_SHORT).show();
                                    ref.child(key).removeValue();
                                }).setNegativeButton("cancel",(dialogInterface1, i2) -> {
                            dialogInterface.dismiss();
                        }).create().show();
                    }).setNegativeButton("cancel",(dialogInterface, i1) -> {
                dialogInterface.dismiss();
            }).create().show();

        });

    }
}


//        new AlertDialog.Builder(getActivity())
//        .setTitle("Delete this appointment ?")
//        .setPositiveButton("Delete", new DialogInterface.OnClickListener() {
//@Override
//public void onClick(DialogInterface dialogInterface, int i) {
//
//        }
//        }).setNegativeButton("cancel", new DialogInterface.OnClickListener() {
//@Override
//public void onClick(DialogInterface dialogInterface, int i) {
//
//        }
//        }).create().show();