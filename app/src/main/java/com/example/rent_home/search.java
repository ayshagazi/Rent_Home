package com.example.rent_home;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class search extends AppCompatActivity {

    String[] DivisionsStringVariable;
    String[] SylhetDivisionDistrictStringVariable;
    String[] DhakaDivisionDistrictStringVariable;
    NavigationView sidenav;
    ActionBarDrawerToggle toggle;
    DrawerLayout drawerLayout;

    String SelectedDivision;
    private TextView text;
    private Spinner DivisionSpinnerVariable;
    private Spinner DistrictSpinnerVariable;
    boolean isActive;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        BottomNavigationView bottomNavigationView=findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.search);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.homePage:
                        startActivity(new Intent(getApplicationContext(), HomePage.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.search:
                        return true;

                    case R.id.postAHome:
                        startActivity(new Intent(getApplicationContext(), PostAHome.class));
                        overridePendingTransition(0,0);
                        return true;

                    /*case R.id.settings:
                        startActivity(new Intent(getApplicationContext(), Settings.class));
                        overridePendingTransition(0,0);
                        return true;*/
                }
                return false;
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
                        Intent intent= new Intent(search.this,Profile.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        finish();
                        break;
                    case R.id.mypostsSN:
                        //Toast.makeText(getApplicationContext(), "Myposts will Open", Toast.LENGTH_LONG).show();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        Intent intent1= new Intent(search.this,MyPosts.class);
                        intent1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent1);
                        finish();
                        break;
                    case R.id.notificationSN:
                        //Toast.makeText(getApplicationContext(), "Notifications will Open", Toast.LENGTH_LONG).show();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        Intent intent2= new Intent(search.this,Notifications.class);
                        intent2.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent2);
                        finish();
                        break;
                    case R.id.settingsSN:
                        //Toast.makeText(getApplicationContext(), "Settings will Open", Toast.LENGTH_LONG).show();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        Intent intent3= new Intent(search.this,Settings.class);
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
                        Intent intent4= new Intent(search.this,Profile.class);
                        intent4.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent4);
                        finish();
                        break;

                }
                return true;
            }
        });


        DivisionsStringVariable=getResources().getStringArray(R.array.DivisionsString);// ei variable e values er declare kora string recieve korbe
        DhakaDivisionDistrictStringVariable=getResources().getStringArray(R.array.DhakaDivisionsDistrictsString);//same
        SylhetDivisionDistrictStringVariable=getResources().getStringArray(R.array.SylhetDivisionDistrictString);//same

        DivisionSpinnerVariable=(Spinner) findViewById(R.id.spinnerDivison); // divison spinner jeta activity_search.xml e ase oita ke variable e set korbe
        DistrictSpinnerVariable =(Spinner) findViewById(R.id.spinnerDistrict);//same

        ArrayAdapter<String> DivisionAdapter = new ArrayAdapter<String>(this, R.layout.spinnerdisplay, R.id.spinnerDisplay, DivisionsStringVariable);// ei adapter division er nam gula ke spinner display layout er maddome adapter e set korbe


        DivisionSpinnerVariable.setAdapter(DivisionAdapter);// set kora divison gulu spinner e show korbe

        refreshContent();

    }
    public void refreshContent(){

        SelectedDivision=DivisionSpinnerVariable.getSelectedItem().toString();
        ArrayAdapter<String> SylhetDivisionAdapter = new ArrayAdapter<String>(this, R.layout.spinnerdisplay, R.id.spinnerDisplay, SylhetDivisionDistrictStringVariable);//same
        ArrayAdapter<String> DhakaDivisionAdapter = new ArrayAdapter<String>(this, R.layout.spinnerdisplay, R.id.spinnerDisplay, DhakaDivisionDistrictStringVariable);//same
        if(SelectedDivision.equals("Sylhet")) {
            DistrictSpinnerVariable.setAdapter(SylhetDivisionAdapter);
        }else if(SelectedDivision.equals("Dhaka"))
        {
            DistrictSpinnerVariable.setAdapter(DhakaDivisionAdapter);
        }
        text=(TextView)findViewById(R.id.textView10);
        text.setText(SelectedDivision);

        refresh(500);
    }

    private void refresh(int milliseconds) {
        final Handler handler = new Handler();

        final Runnable runnable= new Runnable() {
            @Override
            public void run() {
                refreshContent();
            }

        };
        handler.postDelayed(runnable, milliseconds);
    }

}