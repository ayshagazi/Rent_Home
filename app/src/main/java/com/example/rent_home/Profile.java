package com.example.rent_home;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

public class Profile extends AppCompatActivity {
    ImageView pro_picP;
    private TextView addressP,emailP, nameP;
    private TextView phnNoP;


    private FirebaseAuth auth;
    private DatabaseReference databaseReference;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        auth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Users");

        pro_picP = findViewById(R.id.profile_imgP);
        addressP = findViewById(R.id.addressP);
        nameP = findViewById(R.id.nameP);
        emailP = findViewById(R.id.emailP);
        phnNoP = findViewById(R.id.contactNoP);
        retriveData();
    }
    private void retriveData() {
        databaseReference.child(auth.getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                    if (snapshot.hasChild("image")) {
                        String image = snapshot.child("image").getValue().toString();
                        Picasso.get().load(image).into(pro_picP);
                    }

                if (snapshot.hasChild("Address")) {
                    String address = snapshot.child("Address").getValue().toString();
                    addressP.setText(address);
                }
                if (snapshot.hasChild("ContactNo")) {
                    String cn = snapshot.child("ContactNo").getValue().toString();
                    phnNoP.setText(cn);
                }
                if (snapshot.hasChild("Name")) {
                    String cn = snapshot.child("Name").getValue().toString();
                    nameP.setText(cn);
                }
                if (snapshot.hasChild("Email")) {
                    String cn = snapshot.child("Email").getValue().toString();
                    emailP.setText(cn);
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}

