package com.example.rent_home;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.hendraanggrian.appcompat.widget.SocialAutoCompleteTextView;
import com.theartofdev.edmodo.cropper.CropImage;

public class postDetails extends AppCompatActivity {

    private ImageView homeImg;
    SocialAutoCompleteTextView description;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_details);

        homeImg= findViewById(R.id.homeImage);
        description= findViewById(R.id.des);



        CropImage.activity().start(postDetails.this);


    }
}