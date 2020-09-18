package com.example.rent_home;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Settings extends AppCompatActivity {
    private Button updateProfile;
    private Button changePassword;
    private Button deactive;
    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        updateProfile= findViewById(R.id.editProfile);
        changePassword= findViewById(R.id.changePass);
        deactive= findViewById(R.id.deactive);
        pd= new ProgressDialog(this);

        updateProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(Settings.this, editProfile.class));


            }
        });

        changePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Settings.this, chnagePassword.class));

            }
        });

        deactive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();
                if(user!=null){
                    pd.setMessage("Deactivating");
                    pd.show();
                    user.delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(Settings.this, "Account Deactivated", Toast.LENGTH_SHORT).show();
                                finish();
                                startActivity(new Intent(Settings.this, MainActivity.class));

                            }
                            else {
                                Toast.makeText(Settings.this, "Error 404", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }

            }
        });

    }
}