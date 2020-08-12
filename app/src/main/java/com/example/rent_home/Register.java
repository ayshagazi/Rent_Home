package com.example.rent_home;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Register extends AppCompatActivity {


    private EditText password, confirm_password,email;
    private Button Register;
    private FirebaseAuth a;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        password= findViewById(R.id.pass);
        confirm_password= findViewById(R.id.c_pass);
        email= findViewById(R.id.email);
        Register= findViewById(R.id.reg);

        a= FirebaseAuth.getInstance();

        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text_email = email.getText().toString();
                String pass = password.getText().toString();
                String c_p = confirm_password.getText().toString();


                if (pass.equals(c_p)) {


                    if (TextUtils.isEmpty(text_email) || TextUtils.isEmpty(pass) || TextUtils.isEmpty(c_p)) {
                        Toast.makeText(Register.this, "ERROR_404", Toast.LENGTH_SHORT).show();
                    } else if (pass.length() < 7) {
                        Toast.makeText(Register.this, "Password is too short", Toast.LENGTH_SHORT).show();
                    } else {
                        registerUser(text_email, pass, c_p);
                    }
                } else {

                    Toast.makeText(Register.this, "Password Not matching", Toast.LENGTH_SHORT).show();
                }


            }
        });

    }


    private void registerUser(String email, String pass1, String cp) {
        a.createUserWithEmailAndPassword(email, pass1).addOnCompleteListener(Register.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(Register.this, "Registration Complete", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(Register.this, "Failed", Toast.LENGTH_SHORT).show();
                }

            }
        });


    }





    }
