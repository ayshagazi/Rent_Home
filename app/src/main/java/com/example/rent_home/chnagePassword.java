package com.example.rent_home;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class chnagePassword extends AppCompatActivity {
    private EditText newPassword;
    private Button cng;
    ProgressDialog pd;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chnage_password);
        newPassword= findViewById(R.id.newPassword);
        cng= findViewById(R.id.change);
        pd= new ProgressDialog(this);
        auth= FirebaseAuth.getInstance();
        cng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseUser cuser= FirebaseAuth.getInstance().getCurrentUser();
                if(cuser!=null)
                {
                    pd.setMessage("Changing Password");
                    pd.show();
                    cuser.updatePassword(newPassword.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                pd.dismiss();
                                Toast.makeText(chnagePassword.this, "Password Changed Successfully", Toast.LENGTH_SHORT).show();
                                auth.signOut();
                                finish();
                                startActivity(new Intent(chnagePassword.this, Login.class));

                            }
                        }
                    });
                }
            }
        });

    }
}