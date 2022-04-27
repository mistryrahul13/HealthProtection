package com.example.healthprotection.health_static;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.healthprotection.R;
import com.example.healthprotection.databinding.ActivityDashboardBinding;
import com.example.healthprotection.health_static.authentication.UserActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DashboardActivity extends AppCompatActivity {
    private ActivityDashboardBinding binding;
    private DatabaseReference ref;
    private FirebaseAuth auth;
    private ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDashboardBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().hide();

        pd = new ProgressDialog(this);
        pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        pd.setTitle("Fetching User Data");
        pd.setMessage("Please Wait");
        pd.show();

        binding.toolbardashboard.ctb.setNavigationOnClickListener(view -> {
            binding.drawercustom.openDrawer(Gravity.LEFT);
        });

        ref = FirebaseDatabase.getInstance().getReference("User");
        auth = FirebaseAuth.getInstance();

        String userId = auth.getCurrentUser().getUid();

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot childSnap : snapshot.getChildren()){
                    Log.i("Myerror",snapshot.toString());

                    User user = childSnap.getValue(User.class);
                    View view = binding.navigationView.getHeaderView(0);
                    TextView name =view.findViewById(R.id.TVname);
                    TextView email = view.findViewById(R.id.TVemail);
                    ImageView imageView = view.findViewById(R.id.ivImage);


                    if (user.uid.equals(userId)){

                        name.setText(user.name);

                        email.setText(user.email);

                        Glide.with(DashboardActivity.this)
                                .load(user.imageUrl).into(imageView);

                        pd.dismiss();

                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        binding.toolbardashboard.ctb.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                switch (item.getItemId()){
                    case R.id.signout:
                        auth.signOut();
                        startActivity(new Intent(DashboardActivity.this, UserActivity.class));
                        break;
                }
                return false;
            }
        });
        binding.doctorcard.setOnClickListener(view -> {
            Intent i = new Intent(this,SeeDocListActivity.class);
            startActivity(i);
        });
    }
}

