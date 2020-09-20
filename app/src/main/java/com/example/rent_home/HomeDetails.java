package com.example.rent_home;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import Model.HomeInFeedModel;

public class HomeDetails extends AppCompatActivity {

    private TextView HDhomeName, HDarea, HDrent, HDrooms, HDdescription,HDuserName, HDuserEmail, HDuserContactNo;
    private ImageView HDhomePic;
    private String homeID="";
    private ImageButton rent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_details);
        rent=(ImageButton) findViewById(R.id.rent);

        homeID=getIntent().getStringExtra("pId");

        HDhomePic=(ImageView)findViewById(R.id.HDhomePic);
        HDhomeName=(TextView)findViewById(R.id.HDhomeName);
        HDarea=(TextView)findViewById(R.id.HDarea);
        HDrent=(TextView)findViewById(R.id.HDrent);
        HDrooms=(TextView)findViewById(R.id.HDrooms);
        HDdescription=(TextView)findViewById(R.id.HDdescription);


        getHomeDetails(homeID);

        rent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeDetails.this, confirmRent.class));

            }
        });

    }

    private void getHomeDetails(String homeID) {
        DatabaseReference homeRef = FirebaseDatabase.getInstance().getReference().child("Rent_posts");
        homeRef.child(homeID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists())
                {
                    HomeInFeedModel home =  snapshot.getValue(HomeInFeedModel.class);
                    HDhomeName.setText(home.getHomeName());
                    HDdescription.setText(home.getDescription());
                    HDrent.setText(home.getRentCost());
                    HDrooms.setText(home.getRoom());
                    HDarea.setText(home.getLocalArea());
                    Picasso.get().load(home.getImage()).into(HDhomePic);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}