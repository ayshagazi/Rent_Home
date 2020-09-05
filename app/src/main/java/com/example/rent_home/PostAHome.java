package com.example.rent_home;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class PostAHome extends AppCompatActivity {

    String[] DivisionsString;
    String[] DistrictString;
    String[] AreaString;
    String[] RentString;
    String[] RoomString;
    NavigationView sidenav;
    ActionBarDrawerToggle toggle;
    DrawerLayout drawerLayout;

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



        Toolbar toolbar2;
        toolbar2 = (Toolbar) findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar2);
        sidenav = (NavigationView) findViewById(R.id.sidenavmenu);
        drawerLayout = (DrawerLayout) findViewById(R.id.draw);
        toggle = new ActionBarDrawerToggle(this,
                drawerLayout,
                toolbar2,
                R.string.open,
                R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        sidenav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.profileSN:
                        //Toast.makeText(getApplicationContext(), "Profile will Open", Toast.LENGTH_LONG).show();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        Intent intent= new Intent(PostAHome.this,Profile.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        finish();
                        break;
                    case R.id.mypostsSN:
                        //Toast.makeText(getApplicationContext(), "Myposts will Open", Toast.LENGTH_LONG).show();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        Intent intent1= new Intent(PostAHome.this,MyPosts.class);
                        intent1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent1);
                        finish();
                        break;
                    case R.id.notificationSN:
                        //Toast.makeText(getApplicationContext(), "Notifications will Open", Toast.LENGTH_LONG).show();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        Intent intent2= new Intent(PostAHome.this,Notifications.class);
                        intent2.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent2);
                        finish();
                        break;
                    case R.id.settingsSN:
                        //Toast.makeText(getApplicationContext(), "Settings will Open", Toast.LENGTH_LONG).show();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        Intent intent3= new Intent(PostAHome.this,Settings.class);
                        intent3.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent3);
                        finish();
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
                        //Toast.makeText(getApplicationContext(), "About Us will Open", Toast.LENGTH_LONG).show();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        Intent intent4= new Intent(PostAHome.this,Profile.class);
                        intent4.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent4);
                        finish();
                        break;

                }
                return true;
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
