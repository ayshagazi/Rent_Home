package com.example.rent_home;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rengwuxian.materialedittext.MaterialEditText;
import com.squareup.picasso.Picasso;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

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

        edit_pro= findViewById(R.id.edit);
        pro_pic=findViewById(R.id.profile_img);
        address=findViewById(R.id.address);
        username=findViewById(R.id.userName);
        name= findViewById(R.id.name);

        cUser= FirebaseAuth.getInstance().getCurrentUser();
        proID= cUser.getUid();

        edit_pro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Profile.this, editProfile.class));
            }
        });

       // BottomNavigationView bottomNavigationView=findViewById(R.id.bottom_navigation);
      //  bottomNavigationView.setSelectedItemId(R.id.profile);

      //  bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
         /*   @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.profile:
                        //select= new profile();
                       // break;
                         return true;

                    case R.id.search:
                        //select= new search();
                       // break;
                        startActivity(new Intent(getApplicationContext(), search.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.postAHome:
                        //select= new PostAHome();
                        //break;
                       startActivity(new Intent(getApplicationContext(), PostAHome.class));
                        overridePendingTransition(0,0);
                        return true;

                    /*case R.id.settings:
                        //select= new Settings();
                        startActivity(new Intent(getApplicationContext(), Settings.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });
*/
        FirebaseDatabase.getInstance().getReference().child("Users").child(proID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Users us= snapshot.getValue(Users.class);
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

