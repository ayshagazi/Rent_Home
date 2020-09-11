package com.example.rent_home;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class PostAHome extends AppCompatActivity {

    String[] DivisionsStringVariable;
    String[] SylhetDivisionDistrictStringVariable;
    String[] DhakaDivisionDistrictStringVariable;
    String[] BarishalDivisionDistrictStringVariable;
    String[] RangpurDivisionDistrictStringVariable;
    String[] RajshahiDivisionDistrictStringVariable;
    String[] MymensinghDivisionDistrictStringVariable;
    String[] ChittagongDivisionDistrictStringVariable;
    String[] KhulnaDivisionDistrictStringVariable;
    String[] dhakaDisAreaStringVariabl;
    String[] gazipurDisAreaVariable;

    String SelectedDivision, SelectDistrict;
    String SelectArea;
    String nameHome,contactNo,beds,price,localArea,area;
    String saveCurrentDate, saveCurrentTime;
     private String randomKey;

    NavigationView sidenav;
    ActionBarDrawerToggle toggle;
    DrawerLayout drawerLayout;
    private Button homePic,tracMap,details;
    private ImageButton postBtn;
    private Spinner DivisionSpinnerVariable,AreaSpinnerVariable;
    private Spinner DistrictSpinnerVariable,subAreaSpinnerVariable;
    private TextView text;
    private EditText homeName,subArea, rent;
    private EditText phoNo, room;
    private ProgressDialog pd;

    private DatabaseReference postDataRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_a_home);

        postDataRef= FirebaseDatabase.getInstance().getReference().child("Rent_posts");

        postBtn = findViewById(R.id.button_post);
        details= findViewById(R.id.details);
        homeName= findViewById(R.id.homeName);
        subArea= findViewById(R.id.subArea);
        rent= findViewById(R.id.rentRange);
        phoNo= findViewById(R.id.phnNo);
        room= findViewById(R.id.room);
        tracMap= findViewById(R.id.mapApi);
        pd= new ProgressDialog(this);

      //  text=(TextView)findViewById(R.id.description);

        homePic = findViewById(R.id.homePic);
        homePic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PostAHome.this, AddAHome.class));

            }
        });

        tracMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PostAHome.this, tracLocation.class));

            }
        });

        details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PostAHome.this, postDetails.class));

            }
        });

        postBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                collectData();
            }
        });

        Toolbar toolbar2;
        toolbar2 = (Toolbar) findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar2);
        sidenav = (NavigationView) findViewById(R.id.sidenavmenu);
        drawerLayout = (DrawerLayout) findViewById(R.id.draw);
        toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar2, R.string.open, R.string.close);
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
                        Toast.makeText(getApplicationContext(), "Logged out", Toast.LENGTH_LONG).show();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        FirebaseAuth.getInstance().signOut();
                        finish();
                        Intent intent5= new Intent(PostAHome.this, MainActivity.class);
                        startActivity(intent5);
                        break;
                    case R.id.aboutusSN:
                        Toast.makeText(getApplicationContext(), "About Us will Open", Toast.LENGTH_LONG).show();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        Intent intent4= new Intent(PostAHome.this,AboutUs.class);
                        intent4.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent4);
                        finish();
                        break;

                }
                return true;
            }
        });

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


        DivisionsStringVariable=getResources().getStringArray(R.array.DivisionsString);// ei variable e values er declare kora string recieve korbe
        DhakaDivisionDistrictStringVariable=getResources().getStringArray(R.array.DhakaDivisionsDistrictsString);//same
        SylhetDivisionDistrictStringVariable=getResources().getStringArray(R.array.SylhetDivisionDistrictString);//same
        BarishalDivisionDistrictStringVariable=getResources().getStringArray(R.array.BarishalDivisionsDistrictsString);//same
        MymensinghDivisionDistrictStringVariable=getResources().getStringArray(R.array.MymensinghDivisionsDistrictsString);//same
        RajshahiDivisionDistrictStringVariable=getResources().getStringArray(R.array.RajshahiDivisionsDistrictsString);
        RangpurDivisionDistrictStringVariable= getResources().getStringArray(R.array.RangpurDivisionsDistrictsString);
        KhulnaDivisionDistrictStringVariable=getResources().getStringArray(R.array.KhulnaDivisionsDistrictsString);
        ChittagongDivisionDistrictStringVariable=getResources().getStringArray(R.array.ChittagongDivisionsDistrictsString);

        dhakaDisAreaStringVariabl=getResources().getStringArray(R.array.dhakaDisArea);
        gazipurDisAreaVariable=getResources().getStringArray(R.array.gazipurDisArea);


        DivisionSpinnerVariable=(Spinner) findViewById(R.id.division); // divison spinner jeta activity_search.xml e ase oita ke variable e set korbe
        DistrictSpinnerVariable =(Spinner) findViewById(R.id.district);//same
        AreaSpinnerVariable= findViewById(R.id.area);

        ArrayAdapter<String> DivisionAdapter = new ArrayAdapter<String>(this, R.layout.spinnerdisplay, R.id.spinnerDisplay, DivisionsStringVariable);// ei adapter division er nam gula ke spinner display layout er maddome adapter e set korbe


        DivisionSpinnerVariable.setAdapter(DivisionAdapter);// set kora divison gulu spinner e show korbe

        refreshContent();
    }

    private void refreshContent() {

        SelectedDivision=DivisionSpinnerVariable.getSelectedItem().toString();
        ArrayAdapter<String> SylhetDivisionAdapter = new ArrayAdapter<String>(this, R.layout.spinnerdisplay, R.id.spinnerDisplay, SylhetDivisionDistrictStringVariable);//same
        ArrayAdapter<String> DhakaDivisionAdapter = new ArrayAdapter<String>(this, R.layout.spinnerdisplay, R.id.spinnerDisplay, DhakaDivisionDistrictStringVariable);//same
        ArrayAdapter<String> RajshahiDivisionAdapter = new ArrayAdapter<String>(this, R.layout.spinnerdisplay, R.id.spinnerDisplay, RajshahiDivisionDistrictStringVariable);//same
        ArrayAdapter<String> MymensinghDivisionAdapter = new ArrayAdapter<String>(this, R.layout.spinnerdisplay, R.id.spinnerDisplay, MymensinghDivisionDistrictStringVariable);//same
        ArrayAdapter<String> KhulnaDivisionAdapter = new ArrayAdapter<String>(this, R.layout.spinnerdisplay, R.id.spinnerDisplay, KhulnaDivisionDistrictStringVariable);//same
        ArrayAdapter<String> ChittagongDivisionAdapter = new ArrayAdapter<String>(this, R.layout.spinnerdisplay, R.id.spinnerDisplay, ChittagongDivisionDistrictStringVariable);//same
        ArrayAdapter<String> BarishalDivisionAdapter = new ArrayAdapter<String>(this, R.layout.spinnerdisplay, R.id.spinnerDisplay, BarishalDivisionDistrictStringVariable);//same
        ArrayAdapter<String> RangpurDivisionAdapter = new ArrayAdapter<String>(this, R.layout.spinnerdisplay, R.id.spinnerDisplay, RangpurDivisionDistrictStringVariable);//same


        if(SelectedDivision.equals("Sylhet")) {
            DistrictSpinnerVariable.setAdapter(SylhetDivisionAdapter);
        }else if(SelectedDivision.equals("Dhaka"))
        {
            DistrictSpinnerVariable.setAdapter(DhakaDivisionAdapter);
            areaRefreshContent();
           // refresh(500);


        }
        else if(SelectedDivision.equals("Rajshahi"))
        {
            DistrictSpinnerVariable.setAdapter(RajshahiDivisionAdapter);
           // areaRefreshContent();

        }
        else if(SelectedDivision.equals("Chittagong"))
        {
            DistrictSpinnerVariable.setAdapter(ChittagongDivisionAdapter);
        }else if(SelectedDivision.equals("Barishal"))
        {
            DistrictSpinnerVariable.setAdapter(BarishalDivisionAdapter);
        }else if(SelectedDivision.equals("Mymensingh"))
        {
            DistrictSpinnerVariable.setAdapter(MymensinghDivisionAdapter);
        }else if(SelectedDivision.equals("Rangpur"))
        {
            DistrictSpinnerVariable.setAdapter(RangpurDivisionAdapter);
        }
        else if(SelectedDivision.equals("Khulna"))
        {
            DistrictSpinnerVariable.setAdapter(KhulnaDivisionAdapter);
          //  areaRefreshContent();

        }

        areaRefreshContent();
        refresh(500);
    }

    private void refresh(int i) {
        final Handler handler = new Handler();

        final Runnable runnable= new Runnable() {
            @Override
            public void run() {
                refreshContent();
            }

        };
        handler.postDelayed(runnable, i);
    }

    private void areaRefreshContent() {
        SelectDistrict= DistrictSpinnerVariable.getSelectedItem().toString();
        ArrayAdapter<String> dhakaDistrictAdapter= new ArrayAdapter<String>(this, R.layout.spinnerdisplay,R.id.spinnerDisplay,dhakaDisAreaStringVariabl);
        ArrayAdapter<String> gazipurDistrictAdapter= new ArrayAdapter<String>(this,R.layout.spinnerdisplay,R.id.spinnerDisplay,gazipurDisAreaVariable);
        if(SelectDistrict.equals("Dhaka")){
            AreaSpinnerVariable.setAdapter(dhakaDistrictAdapter);
        }
        else if(SelectDistrict.equals("Gazipur")){
            AreaSpinnerVariable.setAdapter(gazipurDistrictAdapter);
        }

       // text.setText(SelectDistrict);

        refresh2(500);
    }

    private void refresh2(int i) {
        final Handler handler = new Handler();

        final Runnable runnable= new Runnable() {
            @Override
            public void run() {
        areaRefreshContent();            }

        };
        handler.postDelayed(runnable, i);
    }

    private void collectData() {
        nameHome= homeName.getText().toString();
        contactNo= phoNo.getText().toString();
        beds= room.getText().toString();
        price=rent.getText().toString();
        localArea= subArea.getText().toString();
        area=SelectDistrict.toString();

        if(TextUtils.isEmpty(contactNo))
        {
            Toast.makeText(this, "Please give your Contact No, its mandatory", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(beds))
        {
            Toast.makeText(this, "Please provide all the information", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(price))
        {
            Toast.makeText(this, "Please provide all the information", Toast.LENGTH_SHORT).show();
        }else if(TextUtils.isEmpty(localArea))
        {
            Toast.makeText(this, "Please provide all the information", Toast.LENGTH_SHORT).show();
        }
        else
            storeData();
    }

    private void storeData() {
        pd.setMessage("Posting");
        pd.show();
        Calendar calendar= Calendar.getInstance();
        SimpleDateFormat currentDate= new SimpleDateFormat("MM dd, yyyy");
        saveCurrentDate= currentDate.format(calendar.getTime());

        SimpleDateFormat currentTime= new SimpleDateFormat("HH: mm: ss a");
        saveCurrentTime= currentTime.format(calendar.getTime());

           randomKey= saveCurrentDate+ saveCurrentTime;

        HashMap<String, Object>map= new HashMap<>();
        map.put("pId",randomKey);
        map.put("date",saveCurrentDate);
        map.put("time",saveCurrentTime);
        map.put("homeName",nameHome);
        map.put("contactNo", contactNo);
        map.put("room",beds);
        map.put("area",area);
        map.put("localArea",localArea);
        map.put("rentCost",price);
        map.put("Punlisher", FirebaseAuth.getInstance().getCurrentUser().getUid());


        postDataRef.child(randomKey).updateChildren(map).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){

                    pd.dismiss();
                    Toast.makeText(PostAHome.this, "Posted", Toast.LENGTH_SHORT).show();
                }
                else{
                    pd.dismiss();
                    String msg=task.getException().toString();
                    Toast.makeText(PostAHome.this, "Error"+msg, Toast.LENGTH_SHORT).show();
                }
            }
        });




    }


/*    private void subAreaRefreshContent() {
        SelectArea=AreaSpinnerVariable.getSelectedItem().toString();
        ArrayAdapter<String> mirpurSubAdapter= new ArrayAdapter<String>(this,R.layout.spinnerdisplay,R.id.spinnerDisplay,mirpurSubStringVariable);
        ArrayAdapter<String> mohammadpurSubAdapter= new ArrayAdapter<String>(this,R.layout.spinnerdisplay,R.id.spinnerDisplay,mohammadpurSubStringVariable);
        if(SelectArea.equals("Mirpur")){
            subAreaSpinnerVariable.setAdapter(mirpurSubAdapter);
        }
        else if(SelectArea.equals("Mohammadpur"))
        {
            subAreaSpinnerVariable.setAdapter(mohammadpurSubAdapter);
        }


        refresh3(500);



    }
    private void refresh3(int i) {
        final Handler handler = new Handler();

        final Runnable runnable= new Runnable() {
            @Override
            public void run() {
                subAreaRefreshContent();
            }

        };
        handler.postDelayed(runnable, i);
    }
*/


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


