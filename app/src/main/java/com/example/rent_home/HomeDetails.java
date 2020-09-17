package com.example.rent_home;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import Model.HomeInFeedModel;

public class HomeDetails extends AppCompatActivity {

    private TextView HDhomeName, HDarea, HDrent, HDrooms, HDdescription,HDuserName, HDuserEmail, HDuserContactNo;
    private ImageView HDhomePic;
    private String homeID="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_details);

        homeID=getIntent().getStringExtra("pId");

        HDhomePic=(ImageView)findViewById(R.id.HDhomePic);
        HDhomeName=(TextView)findViewById(R.id.HDhomeName);
        HDarea=(TextView)findViewById(R.id.HDarea);
        HDrent=(TextView)findViewById(R.id.HDrent);
        HDrooms=(TextView)findViewById(R.id.HDrooms);
        HDdescription=(TextView)findViewById(R.id.HDdescription);
        HDuserName=(TextView)findViewById(R.id.HDuserName);
        HDuserEmail=(TextView)findViewById(R.id.HDuserEmail);
        HDuserContactNo=(TextView)findViewById(R.id.HDuserContactNo);

        getHomeDetails(homeID);
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

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}