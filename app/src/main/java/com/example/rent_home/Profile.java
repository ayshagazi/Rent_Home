package com.example.rent_home;

import android.os.Bundle;
import android.util.Log;
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
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class Profile extends AppCompatActivity {
    private Button edit_pro;
    //private CircleImageView pro_pic;
    ImageView pro_pic;
    private TextView address, username, name;
    private TextView phnNo,mail;

    StorageReference storageReference;
    FirebaseStorage storage;

    private FirebaseUser cUser;

    String proID,homeID="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
       // homeID=getIntent().getStringExtra("pId");

      //  edit_pro= findViewById(R.id.edit);
        pro_pic=findViewById(R.id.profile_img);
        address=findViewById(R.id.address);
        username=findViewById(R.id.userName);
        name= findViewById(R.id.name);
        mail=findViewById(R.id.email);
        phnNo=findViewById(R.id.contactNo);

        storageReference= FirebaseStorage.getInstance().getReference();
        storage=FirebaseStorage.getInstance();

        cUser= FirebaseAuth.getInstance().getCurrentUser();
        proID= cUser.getUid();
       homeID=cUser.getUid();
     //   getContactNo(homeID);


        FirebaseDatabase.getInstance().getReference().child("Users").child(proID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Users us= snapshot.getValue(Users.class);
//                Log.d("Check",us.getImageUri());

             //   Picasso.get().load(us.getImageUri()).into(pro_pic);
               // StorageReference storageRef ;
              //  StorageReference mountainImagesRef = storageReference.getR;\
                Log.d("number",us.getNumber());
              //  StorageReference gsReference = storage.getReferenceFromUrl("gs://rent-home-7a3e6.appspot.com/Uploads/"+us.getNumber()+".jpg");
                  StorageReference gsReference = storage.getReferenceFromUrl("gs://rent-home-7a3e6.appspot.com/Uploads/1600423078829.jpg");



                Glide
                        .with(pro_pic.getContext())
                        .load(gsReference)
                        .into(pro_pic);

                // Picasso.get().load("https://firebasestorage.googleapis.com/v0/b/rent-home-7a3e6.appspot.com/o/Uploads%2F1598119713974.jpeg?alt=media&token=03995246-0bcd-46c9-bc8b-47e43640a5f4").into(pro_pic);

                name.setText(us.getName());
                address.setText(us.getAddress());
                username.setText(us.getUsername());
                mail.setText(us.getEmail());
                phnNo.setText(us.getContactNo());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

    }
/*
    private void getContactNo(String homeID) {
        DatabaseReference homeRef = FirebaseDatabase.getInstance().getReference().child("Rent_posts");
        homeRef.child(homeID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists())
                {
                    HomeInFeedModel home =  snapshot.getValue(HomeInFeedModel.class);
                    phnNo.setText(home.getHomeName());

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    */
}

