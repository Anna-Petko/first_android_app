package com.example.tdsapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegistrationActivity extends AppCompatActivity {
    private EditText email;
    private EditText pass;
    private Button btnSignup;
    private Button btnSignin;


    //Firebase

   private FirebaseAuth mAuth;
   private ProgressDialog mDialog;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

       mAuth = FirebaseAuth.getInstance();
       mDialog = new ProgressDialog(this);


        email = findViewById(R.id.email_reg);
        pass = findViewById(R.id.password_reg);
        btnSignin = findViewById(R.id.btnsignin_reg);
        btnSignup = findViewById(R.id.btnsignup_reg);


        //for login

        btnSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));

            }
        });


        //for sign up

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mEmail = email.getText().toString().trim();
                String mPass = pass.getText().toString().trim();

                if(TextUtils.isEmpty(mEmail)){
                    email.setError("Required field");
                    return;
                }

                if(TextUtils.isEmpty(mPass)){
                    pass.setError("Required field");
                    return;
                }

                mDialog.setMessage("Processing...");
                mDialog.show();


                mAuth.createUserWithEmailAndPassword(mEmail,mPass)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {

                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d("Check", "88");
                        if (task.isSuccessful()) {
                            mDialog.dismiss();
                            startActivity(new Intent(getApplicationContext(), HomeActivity.class));

                        }

                    }
                });
            }
        });

    }
}
