package com.example.rent_home;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class PostAHome extends AppCompatActivity {

    String[] DivisionsString;
    String[] DistrictString;
    String[] AreaString;
    String[] RentString;
    String[] RoomString;

    private Spinner division,district,area,rent,room;
    private Button homePic;
    private ImageButton postBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_a_home);


        DivisionsString= getResources().getStringArray(R.array.DivisionsString);
       // DistrictString= getResources().getStringArray(R.array.)
        division= findViewById(R.id.Divison);
        postBtn = findViewById(R.id.button_post);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.spinnerdisplay,R.id.spinnerDisplay,DivisionsString);
        division.setAdapter(adapter);

        homePic=findViewById(R.id.homePic);

        homePic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PostAHome.this, AddAHome.class));

            }
        });









/*
        imaged_added= findViewById(R.id.image_added);
        close= findViewById(R.id.close);
        description= findViewById(R.id.description);
        post= findViewById(R.id.post);

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PostAHome.this, Profile.class));
                finish();
            }
        });

        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                upload();
            }
        });

        CropImage.activity().start(PostAHome.this);
 */

        BottomNavigationView bottomNavigationView=findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.postAHome);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.homePage:
                        startActivity(new Intent(getApplicationContext(), HomePage.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.search:
                        startActivity(new Intent(getApplicationContext(), search.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.postAHome:
                        return true;

                }
                return false;
            }
        });
    }

/*
    private void upload() {
        pd= new ProgressDialog(this);
        pd.setMessage("Uploading");
        pd.show();

        if(image_uri != null){
            final StorageReference file = FirebaseStorage.getInstance().getReference("Posts").child(System.currentTimeMillis() + "."+ getFileExtension(image_uri));
            StorageTask uptask= file.putFile(image_uri);

            uptask.continueWithTask(new Continuation() {
                @Override
                public Object then(@NonNull Task task) throws Exception {
                    if(!task.isSuccessful()){
                        throw task.getException();
                    }
                    return file.getDownloadUrl();
                }
            }).addOnCompleteListener(new OnCompleteListener() {
                @Override
                public void onComplete(@NonNull Task task) {
                    Uri down_uri= (Uri) task.getResult();
                    imgUrl= down_uri.toString();

                    DatabaseReference refre= FirebaseDatabase.getInstance().getReference("posts");
                    String postID = refre.push().getKey();

                    HashMap<String, Object> map= new HashMap<>();
                    map.put("Post Id",postID);
                    map.put("Image Url",imgUrl);
                    map.put("Description",description.getText().toString());
                    map.put("Punlisher", FirebaseAuth.getInstance().getCurrentUser().getUid());

                    refre.child(postID).setValue(map);

                    DatabaseReference hasHTagRef= FirebaseDatabase.getInstance().getReference().child("HashTags");
                    List<String> hashtags= description.getHashtags();
                    if(!hashtags.isEmpty()){
                        for (String tag: hashtags){
                            map.clear();
                            map.put("tag",tag.toLowerCase());
                            map.put("Post ID", postID);
                            hasHTagRef.child(tag.toLowerCase()).setValue(map);
                        }
                    }
                    pd.dismiss();
                    startActivity(new Intent(PostAHome.this, Profile.class));
                    finish();

                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(PostAHome.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

            }

        else {
            Toast.makeText(this, "Image missing", Toast.LENGTH_SHORT).show();
        }

    }

    private String getFileExtension(Uri uri) {
      return MimeTypeMap.getSingleton().getExtensionFromMimeType(this.getContentResolver().getType(uri));
    }
 */
   /* @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(Objects.equals(requestCode,CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) ){
            CropImage.ActivityResult result= CropImage.getActivityResult(data);
            image_uri= result.getUri();
            imaged_added.setImageURI(image_uri);
            Toast.makeText(this, "hoise", Toast.LENGTH_SHORT).show();
        }
        else if( Objects.equals(requestCode,RESULT_OK)){
            Toast.makeText(this, "hoy nai", Toast.LENGTH_SHORT).show();
        }

        else
            {
                Toast.makeText(this, "Please try again", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(PostAHome.this, Profile.class));
                finish();
            }
    }
    */
}
