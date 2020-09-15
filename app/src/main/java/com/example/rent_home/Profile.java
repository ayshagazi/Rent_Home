package com.example.rent_home;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class Profile extends AppCompatActivity {
    private Button edit_pro;
    private CircleImageView pro_pic;
    private TextView address, username, name;

    private FirebaseUser cUser;

    String proID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

      //  edit_pro= findViewById(R.id.edit);
        pro_pic=findViewById(R.id.profile_img);
        address=findViewById(R.id.address);
        username=findViewById(R.id.userName);
        name= findViewById(R.id.name);

        cUser= FirebaseAuth.getInstance().getCurrentUser();
        proID= cUser.getUid();




        FirebaseDatabase.getInstance().getReference().child("Users").child(proID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Users us= snapshot.getValue(Users.class);
//                Picasso.get().load(us.getImageUri()).into(pro_pic);
                Picasso.get().load("https://firebasestorage.googleapis.com/v0/b/rent-home-7a3e6.appspot.com/o/Uploads%2F1598119713974.jpeg?alt=media&token=03995246-0bcd-46c9-bc8b-47e43640a5f4").into(pro_pic);

                name.setText(us.getName());
                address.setText(us.getAddress());
                username.setText(us.getUsername());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
}

