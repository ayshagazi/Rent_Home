package com.example.rent_home;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.rengwuxian.materialedittext.MaterialEditText;
import com.squareup.picasso.Picasso;
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
    private StorageTask uploadTask;
    private StorageReference stroageRef;
    private String imgUri;

 //   NavigationView sidenav;
   // ActionBarDrawerToggle toggle;
   // DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

     /*   Toolbar toolbar2;
        toolbar2 = (Toolbar)findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar2);
        sidenav = (NavigationView)findViewById(R.id.sidenavmenu);
        DrawerLayout drawer = (DrawerLayout)findViewById(R.id.drawer);
        toggle =new ActionBarDrawerToggle(this, drawerLayout, toolbar2, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        sidenav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId())
                {
                    case R.id.profileSN:
                        Toast.makeText(getApplicationContext(), "Profile will Open", Toast.LENGTH_LONG).show();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.mypostsSN:
                        Toast.makeText(getApplicationContext(), "Myposts will Open", Toast.LENGTH_LONG).show();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.notificationSN:
                        Toast.makeText(getApplicationContext(), "Notifications will Open", Toast.LENGTH_LONG).show();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.settingsSN:
                        Toast.makeText(getApplicationContext(), "Settings will Open", Toast.LENGTH_LONG).show();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.exitSN:
                        Toast.makeText(getApplicationContext(), "Exit", Toast.LENGTH_LONG).show();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.logoutSN:
                        Toast.makeText(getApplicationContext(), "Logout", Toast.LENGTH_LONG).show();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.aboutusSN:
                        Toast.makeText(getApplicationContext(), "About Us will Open", Toast.LENGTH_LONG).show();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;

                }
                return true;
            }
        });

*/

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
        FirebaseDatabase.getInstance().getReference().child("Users").child(cur_user.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Users us = snapshot.getValue(Users.class);
                name.setText(us.getName());
                email.setText(us.getEmail());
                username.setText(us.getUsername());
                address.setText(us.getAddress());
               // Picasso.get().load(us.getImageUri()).into(pro_pic);

                Picasso.get().load("https://firebasestorage.googleapis.com/v0/b/rent-home-7a3e6.appspot.com/o/home_pictures%2Fimage%3A3109%2016%2C%20202012%3A%2033%3A%2036%20PM.jpg?alt=media&token=29486027-eafc-45cc-b62a-903132ec3061").into(pro_pic);


//image_add_baki
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

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
            }
        });

        pro_pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CropImage.activity().setCropShape(CropImageView.CropShape.OVAL).start(editProfile.this);
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateProfile();
                Toast.makeText(editProfile.this, "Updated", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void updateProfile() {
        HashMap<String,Object> map= new HashMap<>();
        map.put("Name",name.getText().toString());
        map.put("Username",username.getText().toString());
        map.put("Email",email.getText().toString());
        map.put("Address",address.getText().toString());
        map.put("ImageUri",imgUri);

        FirebaseDatabase.getInstance().getReference().child("Users").child(cur_user.getUid()).updateChildren(map);

    }


    private void uploadImage() {
        final ProgressDialog pd= new ProgressDialog(this);
        pd.setMessage("Uploading");
        pd.show();

        if(mImgUri !=null){
             final StorageReference file= stroageRef.child(System.currentTimeMillis()+".jpeg");
            //final StorageReference file= FirebaseStorage.getInstance().getReference("Profiles").child(System.currentTimeMillis()+"."+getFileExtension(String.valueOf(mImgUri)));
            uploadTask= file.putFile(mImgUri);
            uploadTask.continueWithTask(new Continuation() {
                @Override
                public Object then(@NonNull Task task) throws Exception {
                    if(task.isSuccessful())
                    {
                        throw task.getException();
                    }
                    return file.getDownloadUrl();
                }
            }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                @Override
                public void onComplete(@NonNull Task task) {
                    if(task.isSuccessful()) {
                        Uri downladUri = (Uri) task.getResult();
                        imgUri = downladUri.toString();

                      /*  DatabaseReference refre= FirebaseDatabase.getInstance().getReference("Users");
                        HashMap<String,Object> map= new HashMap<>();
                        map.put("Name",name.getText().toString());
                        map.put("Username",username.getText().toString());
                        map.put("Email",email.getText().toString());
                        map.put("Address",address.getText().toString());
                        map.put("ImageUri",imgUri);

                        FirebaseDatabase.getInstance().getReference().child("Users").child(cur_user.getUid()).updateChildren(map);

*/

                        FirebaseDatabase.getInstance().getReference().child("Users").child(cur_user.getUid()).child("ImageUri").setValue(imgUri);



                        pd.dismiss();
                    }
                    else {
                        Toast.makeText(editProfile.this, "Upload Failed", Toast.LENGTH_SHORT).show();
                    }
                }
            });

        }
        else {
            Toast.makeText(this, "Image not selected", Toast.LENGTH_SHORT).show();
        }

    }

        @Override
        protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
            super.onActivityResult(requestCode, resultCode, data);

            if(requestCode==CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE){
                CropImage.ActivityResult result= CropImage.getActivityResult(data);
                mImgUri=result.getUri();
                uploadImage();
            }
            else {
                Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show();
            }


        }


    }

