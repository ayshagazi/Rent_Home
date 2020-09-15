package com.example.rent_home;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.hendraanggrian.appcompat.widget.SocialAutoCompleteTextView;
import com.theartofdev.edmodo.cropper.CropImage;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class postDetails extends AppCompatActivity {

    private ImageView homeImg;
    private Button upBtn;
    private FirebaseUser cur_user;
    SocialAutoCompleteTextView description;
   private StorageReference picOfPostHome;
   private static final int galleryPic = 1;
   private Uri ImageUri ;
   private String randomKey,downloadUri,iUri;
   private String saveCurrentDate, saveCurrentTime;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_details);

        homeImg= findViewById(R.id.homeImage);
        upBtn= findViewById(R.id.upBtn);
        description= findViewById(R.id.des);
        picOfPostHome= FirebaseStorage.getInstance().getReference().child("home_pictures");
        cur_user = FirebaseAuth.getInstance().getCurrentUser();




        homeImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 CropImage.activity().start(postDetails.this);
                 openGallery();
            }
        });





       // CropImage.activity().start(postDetails.this);


    }

    private void openGallery() {
        Intent galleryIntent= new Intent();
        galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
        galleryIntent.setType("image/*");
        startActivityForResult(galleryIntent, galleryPic);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==galleryPic && resultCode== RESULT_OK && data!=null)
        {
            ImageUri= data.getData();
            homeImg.setImageURI(ImageUri);
        }

        if(ImageUri==null){
            Toast.makeText(this, "Please select image", Toast.LENGTH_SHORT).show();
        }
        else{
            savePicture();
        }
    }

    private void savePicture() {

        Calendar calendar= Calendar.getInstance();
        SimpleDateFormat currentDate= new SimpleDateFormat("MM dd, yyyy");
        saveCurrentDate= currentDate.format(calendar.getTime());

        SimpleDateFormat currentTime= new SimpleDateFormat("HH: mm: ss a");
        saveCurrentTime= currentTime.format(calendar.getTime());

        randomKey= saveCurrentDate+ saveCurrentTime;
        StorageReference file= picOfPostHome.child(ImageUri.getLastPathSegment()+ randomKey + ".jpg");

        final UploadTask uploadTask= file.putFile(ImageUri);




        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e)
            {
                String message = e.toString();
                Toast.makeText(postDetails.this, "Error: " + message, Toast.LENGTH_SHORT).show();
               //loadingBar.dismiss();
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot)
            {
                Toast.makeText(postDetails.this, "Product Image uploaded Successfully...", Toast.LENGTH_SHORT).show();

                Task<Uri> urlTask = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                    @Override
                    public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception
                    {
                        if (!task.isSuccessful())
                        {
                            throw task.getException();
                        }

                        downloadUri = file.getDownloadUrl().toString();
                        return file.getDownloadUrl();
                    }
                }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                    @Override
                    public void onComplete(@NonNull Task<Uri> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(postDetails.this, "Done", Toast.LENGTH_SHORT).show();
                           // UpdateDatabase();
                        }
                    }
                });
            }
        });

    }
/*
    private void UpdateDatabase() {

        HashMap<String, Object> map= new HashMap<>();
        map.put("description",description.getText().toString());
        map.put("image",downloadUri);
        FirebaseDatabase.getInstance().getReference().child("Rent_posts").child(cur_user.getUid()).updateChildren(map);

    }
    */

}