package com.example.rent_home;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class Register extends AppCompatActivity {


    private EditText password, confirm_password,email,username,name;
    private ImageButton Register;
    private TextView lgUser;
    private FirebaseAuth a;

    private DatabaseReference rootRef;

    ProgressDialog pd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        password= findViewById(R.id.pass);
        confirm_password= findViewById(R.id.c_pass);
        email= findViewById(R.id.email);
        Register= findViewById(R.id.reg);
        username= findViewById(R.id.username);
        name=findViewById(R.id.name);
        lgUser= findViewById(R.id.loginUser);

        a= FirebaseAuth.getInstance();
        rootRef= FirebaseDatabase.getInstance().getReference();

        pd= new ProgressDialog(this);

        lgUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Register.this, Login.class));
            }
        });

        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user= username.getText().toString();
                String nam= name.getText().toString();
                String text_email = email.getText().toString();
                String pass = password.getText().toString();
                String c_p = confirm_password.getText().toString();


                if (pass.equals(c_p)) {


                    if (TextUtils.isEmpty(text_email) || TextUtils.isEmpty(pass) || TextUtils.isEmpty(c_p) ||TextUtils.isEmpty(user)|| TextUtils.isEmpty(nam)) {
                        Toast.makeText(Register.this, "Please give all the information", Toast.LENGTH_SHORT).show();
                    } else if (pass.length() < 5) {
                        password.setError("Password is too short");
                        password.requestFocus();
                        return;
                       // Toast.makeText(Register.this, "Password is too short", Toast.LENGTH_SHORT).show();
                    } else {
                        registerUser(user, nam, text_email, pass, c_p);
                    }
                } else {
                    confirm_password.setError("Password is not matching");
                    confirm_password.requestFocus();
                    return;

                  //  Toast.makeText(Register.this, "Password Not matching", Toast.LENGTH_SHORT).show();
                }


            }
        });

    }

    private void registerUser(final String user, final String nam, final String email, final String pass, String c_p) {
        pd.setMessage("Please wait");
        pd.show();

        a.createUserWithEmailAndPassword(email,pass).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                HashMap<String, Object> map= new HashMap<>();
                map.put("Username",user);
                map.put("Name",nam);
                map.put("Email",email);
                map.put("Password",pass);
                map.put("id",a.getCurrentUser().getUid());


                rootRef.child("Users").child(a.getCurrentUser().getUid()).setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            pd.dismiss();
                            Intent intent= new Intent(Register.this, Login.class);
                            Toast.makeText(Register.this, "Successfully Registerd, Please login now", Toast.LENGTH_SHORT).show();
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent);
                            finish();
                        }
                    }
                });
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                pd.dismiss();
                Toast.makeText(Register.this, e.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });


        }
    }



