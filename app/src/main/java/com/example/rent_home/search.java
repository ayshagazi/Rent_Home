package com.example.rent_home;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class search extends AppCompatActivity {

    String[] DivisionsStringVariable;
    String[] SylhetDivisionDistrictStringVariable;
    String[] DhakaDivisionDistrictStringVariable;

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