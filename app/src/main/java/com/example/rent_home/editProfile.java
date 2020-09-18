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
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.mapbox.core.utils.TextUtils;
import com.rengwuxian.materialedittext.MaterialEditText;
import com.squareup.picasso.Picasso;
import com.theartofdev.edmodo.cropper.CropImage;

import java.util.HashMap;

import de.hdodenhof.circleimageview.CircleImageView;

public class editProfile extends AppCompatActivity {

    private ImageView close;
    private TextView save, C_pic,changePic;
    private CircleImageView pro_pic;
    private MaterialEditText name,username,email,address,contactNo;

    private FirebaseUser cur_user;
    private Uri mImgUri;
    private StorageTask uploadTask;
    private StorageReference stroageRef;
    private Uri imageUri;
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
        contactNo=findViewById(R.id.cntNo);


        stroageRef = FirebaseStorage.getInstance().getReference().child("Uploads");

        cur_user = FirebaseAuth.getInstance().getCurrentUser();
        userinfoDisplay(pro_pic,name,email,username,address,contactNo);


        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        changePic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check = "clicked";

                CropImage.activity(imageUri)
                        .setAspectRatio(1, 1)
                        .start(editProfile.this);
            }
        });

     /*   pro_pic.setOnClickListener(new View.OnClickListener() {
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
*/
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (check.equals("clicked"))
                {
                    userInfoSaved();
                }
                else
                {
                    updateProfile();
                }
               // Toast.makeText(editProfile.this, "Updated", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void updateProfile() {
        HashMap<String,Object> map= new HashMap<>();
        map.put("Name",name.getText().toString());
        map.put("Username",username.getText().toString());
        map.put("Email",email.getText().toString());
        map.put("Address",address.getText().toString());
        map.put("ContactNo",contactNo.getText().toString());
      ///  map.put("ImageUri",downloadUri);

        FirebaseDatabase.getInstance().getReference().child("Users").child(cur_user.getUid()).updateChildren(map);

        startActivity(new Intent(editProfile.this, Profile.class));
        Toast.makeText(editProfile.this, "Profile Info update successfully.", Toast.LENGTH_SHORT).show();
        finish();

    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE  &&  resultCode==RESULT_OK  &&  data!=null)
        {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            imageUri = result.getUri();

            pro_pic.setImageURI(imageUri);
        }
        else
        {
            Toast.makeText(this, "Error 404", Toast.LENGTH_SHORT).show();

            startActivity(new Intent(editProfile.this, editProfile.class));
            finish();
        }
    }

    private void userInfoSaved()
    {
        if (TextUtils.isEmpty(contactNo.getText().toString()))
        {
            Toast.makeText(this, "Please provide your Contact No", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(address.getText().toString()))
        {
            Toast.makeText(this, "Please provide your address", Toast.LENGTH_SHORT).show();
        }

        else if(check.equals("clicked"))
        {
            uploadImage();
        }
    }




    private void uploadImage() {
        final ProgressDialog pd= new ProgressDialog(this);
        pd.setMessage("Uploading");
        pd.show();
        Log.d("Checked", String.valueOf(imageUri));

        if(imageUri !=null) {
            final StorageReference file = stroageRef.child(System.currentTimeMillis() + ".jpg");
            UploadTask uploadTask = file.putFile(imageUri);
            uploadTask = file.putFile(imageUri);

            uploadTask.continueWithTask(new Continuation() {
                @Override
                public Object then(@NonNull Task task) throws Exception
                {
                    if (!task.isSuccessful())
                    {
                        throw task.getException();
                    }

                    return file.getDownloadUrl();
                }
            })
                    .addOnCompleteListener(new OnCompleteListener<Uri>() {
                        @Override
                        public void onComplete(@NonNull Task<Uri> task)
                        {
                            if (task.isSuccessful())
                            {
                                Uri downloadUrl = task.getResult();
                                myUrl = downloadUrl.toString();

                                DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Users");

                                HashMap<String,Object> map= new HashMap<>();
                                map.put("Name",name.getText().toString());
                                map.put("Username",username.getText().toString());
                                map.put("Email",email.getText().toString());
                                map.put("ContactNo",contactNo.getText().toString());
                                map.put("Address",address.getText().toString());
                                map. put("image", myUrl);
                                map.put("Number",String.valueOf(System.currentTimeMillis()));
                                FirebaseDatabase.getInstance().getReference().child("Users").child(cur_user.getUid()).updateChildren(map);

                                pd.dismiss();

                                startActivity(new Intent(editProfile.this, Profile.class));
                                Toast.makeText(editProfile.this, "Profile Info update successfully.", Toast.LENGTH_SHORT).show();
                                finish();
                            }
                            else
                            {
                                pd.dismiss();
                                Toast.makeText(editProfile.this, "Error.", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
        else
        {
            Toast.makeText(this, "image is not selected.", Toast.LENGTH_SHORT).show();
        }
    }

  /*  private void edited() {

        DatabaseReference ref=FirebaseDatabase.getInstance().getReference().child("Users");
        HashMap<String,Object> map= new HashMap<>();
        map.put("Name",name.getText().toString());
        map.put("Username",username.getText().toString());
        map.put("Email",email.getText().toString());
        map.put("Address",address.getText().toString());
        map.put("ImageUri",downloadUri);
        FirebaseDatabase.getInstance().getReference().child("Users").child(cur_user.getUid()).updateChildren(map);
    }

*/
    private void userinfoDisplay(CircleImageView pro_pic, MaterialEditText name, MaterialEditText email, MaterialEditText username, MaterialEditText address,  MaterialEditText contactNo) {
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
                        String contactNo1=snapshot.child("ContactNo").getValue().toString();

                        name.setText(name1);
                        email.setText(email3);
                        username.setText(username1);
                        address.setText(address1);
                        contactNo.setText(contactNo1);
                       Picasso.get().load(image).into(pro_pic);

                        //  Picasso.get().load("https://firebasestorage.googleapis.com/v0/b/rent-home-7a3e6.appspot.com/o/home_pictures%2Fimage%3A3109%2016%2C%20202012%3A%2033%3A%2036%20PM.jpg?alt=media&token=29486027-eafc-45cc-b62a-903132ec3061").into(pro_pic);


//image_add_baki
                    }
                    else{

                        //String image= snapshot.child("ImageUri").getValue().toString();
                        String name1= snapshot.child("Name").getValue().toString();
                        String email3= snapshot.child("Email").getValue().toString();
                        String username1= snapshot.child("Username").getValue().toString();
//                        String address1= snapshot.child("Address").getValue().toString();
                     //   String contactNo1=snapshot.child("ContactNo").getValue().toString();

                        name.setText(name1);
                        email.setText(email3);
                        username.setText(username1);
                     //   address.setText(address1);
                       // contactNo.setText(contactNo1);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    }

