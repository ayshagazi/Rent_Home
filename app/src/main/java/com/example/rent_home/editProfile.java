package com.example.rent_home;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.rengwuxian.materialedittext.MaterialEditText;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.util.HashMap;

import de.hdodenhof.circleimageview.CircleImageView;

public class editProfile extends AppCompatActivity {

    private ImageView close;
    private TextView save, C_pic,changePic;
    private CircleImageView pro_pic;
    private MaterialEditText name,username,email,address;

    private FirebaseUser cur_user;
    private Uri mImgUri;
    //private StorageTask uploadTask;
    private StorageReference stroageRef;
    private Uri imgUri;
    private String downloadUri,image,myUrl;
    private String check="";
    private static final int galleryPic = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        close = findViewById(R.id.close);
        save = findViewById(R.id.save);
        pro_pic = findViewById(R.id.pro_pic);
        name = findViewById(R.id.name);
        username = findViewById(R.id.username);
        email = findViewById(R.id.email);
        address = findViewById(R.id.address);
        changePic = findViewById(R.id.cngPic);


        stroageRef = FirebaseStorage.getInstance().getReference().child("Uploads");

        cur_user = FirebaseAuth.getInstance().getCurrentUser();
        userinfoDisplay(pro_pic,name,email,username,address);


        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        changePic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CropImage.activity().setCropShape(CropImageView.CropShape.OVAL).start(editProfile.this);
                uploadImage();
            }
        });

        pro_pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CropImage.activity().setCropShape(CropImageView.CropShape.OVAL).start(editProfile.this);
                Intent galleryIntent= new Intent();
                galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
                galleryIntent.setType("image/*");
                startActivityForResult(galleryIntent, galleryPic);
                uploadImage();
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateProfile();
                uploadImage();

                Toast.makeText(editProfile.this, "Updated", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void userinfoDisplay(CircleImageView pro_pic, MaterialEditText name, MaterialEditText email, MaterialEditText username, MaterialEditText address) {
        FirebaseDatabase.getInstance().getReference().child("Users").child(cur_user.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    if(snapshot.child("ImageUri").exists()){
                        String image= snapshot.child("ImageUri").getValue().toString();
                        String name1= snapshot.child("Name").getValue().toString();
                        String email3= snapshot.child("Email").getValue().toString();
                        String username1= snapshot.child("Username").getValue().toString();
                        String address1= snapshot.child("Address").getValue().toString();

                        name.setText(name1);
                        email.setText(email3);
                        username.setText(username1);
                        address.setText(address1);
//                        Picasso.get().load(image).into(pro_pic);

                        //  Picasso.get().load("https://firebasestorage.googleapis.com/v0/b/rent-home-7a3e6.appspot.com/o/home_pictures%2Fimage%3A3109%2016%2C%20202012%3A%2033%3A%2036%20PM.jpg?alt=media&token=29486027-eafc-45cc-b62a-903132ec3061").into(pro_pic);


//image_add_baki
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==galleryPic && data!=null)
        {

           // CropImage.ActivityResult result= CropImage.getActivityResult(data);
            imgUri=data.getData();
            Log.d("Checked", String.valueOf(imgUri));
            pro_pic.setImageURI(imgUri);

        }

        else{
            Toast.makeText(this, "Error 404", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(editProfile.this, Register.class));

        }

    }

    private void updateProfile() {
        HashMap<String,Object> map= new HashMap<>();
        map.put("Name",name.getText().toString());
        map.put("Username",username.getText().toString());
        map.put("Email",email.getText().toString());
        map.put("Address",address.getText().toString());
        map.put("ImageUri",downloadUri);

        FirebaseDatabase.getInstance().getReference().child("Users").child(cur_user.getUid()).updateChildren(map);

    }



    private void uploadImage() {
        final ProgressDialog pd= new ProgressDialog(this);
        pd.setMessage("Uploading");
        pd.show();
        Log.d("Checked", String.valueOf(imgUri));

        //if(imgUri !=null) {
            final StorageReference file = stroageRef.child(System.currentTimeMillis() + ".jpeg");
            final UploadTask uploadTask = file.putFile(imgUri);
            uploadTask.addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    String message = e.toString();
                    Toast.makeText(editProfile.this, "Error: " + message, Toast.LENGTH_SHORT).show();
                }
            }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Toast.makeText(editProfile.this, "Product Image uploaded Successfully...", Toast.LENGTH_SHORT).show();

                    Task<Uri> urlTask = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                        @Override
                        public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                            if (!task.isSuccessful()) {
                                throw task.getException();
                            }

                            downloadUri = file.getDownloadUrl().toString();
                            return file.getDownloadUrl();
                        }
                    }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                        @Override
                        public void onComplete(@NonNull Task task) {
                            if (task.isSuccessful()) {

                                downloadUri = task.getResult().toString();

                                edited();
                                // FirebaseDatabase.getInstance().getReference().child("Users").child(cur_user.getUid()).child("ImageUri").setValue(imgUri);
                                pd.dismiss();
                            } else {
                                pd.dismiss();
                                Toast.makeText(editProfile.this, "Upload Failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                }

            });
        }


    private void edited() {

        DatabaseReference ref=FirebaseDatabase.getInstance().getReference().child("Users");
        HashMap<String,Object> map= new HashMap<>();
        map.put("Name",name.getText().toString());
        map.put("Username",username.getText().toString());
        map.put("Email",email.getText().toString());
        map.put("Address",address.getText().toString());
        map.put("ImageUri",downloadUri);
        FirebaseDatabase.getInstance().getReference().child("Users").child(cur_user.getUid()).updateChildren(map);
    }
    }

