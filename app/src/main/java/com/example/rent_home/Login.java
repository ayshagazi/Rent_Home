package com.example.rent_home;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {

    private EditText email, password;
    private Button login;
    private TextView regUser;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email= findViewById(R.id.email);
        password= findViewById(R.id.password);
        login= findViewById(R.id.lg);
        regUser= findViewById(R.id.user_reg);

        auth= FirebaseAuth.getInstance();

        regUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login.this, Register.class));

            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email_1= email.getText().toString();
                String password_1= password.getText().toString();
                if(TextUtils.isEmpty(email_1)){
                    email.setError("Write your email");
                    email.requestFocus();
                    return;
                }

               else if(TextUtils.isEmpty(password_1)){
                    password.setError("Write your password");
                    password.requestFocus();
                    return;
                }
               else {
                    loginUser(email_1,password_1);

                }

            }


        });
    }

    private void loginUser(String email, String password) {
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(Login.this, "Update the profile for better experience", Toast.LENGTH_SHORT).show();
                    Intent intent= new Intent(Login.this, home.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    finish();

                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(Login.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}