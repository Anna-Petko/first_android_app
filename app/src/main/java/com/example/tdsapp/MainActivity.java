package com.example.tdsapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity
{

    private EditText email;
    private EditText password;
    private Button btnSignIn;
    private Button btnSignUp;

    private FirebaseAuth mAuth;
    private ProgressDialog mDialog;

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();

        mDialog = new ProgressDialog(this);

        email = findViewById(R.id.email_login);
        password = findViewById(R.id.password_login);

        btnSignIn = findViewById(R.id.btnsignin);
        btnSignUp = findViewById(R.id.btnsignup);

        btnSignIn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String mEmail = email.getText().toString().trim();
                String mPass = password.getText().toString().trim();

                if (TextUtils.isEmpty(mEmail))
                {
                    email.setError("This field is required.");
                    return;
                }
                if (TextUtils.isEmpty(mPass))
                {
                    password.setError("This field is required.");
                    return;
                }

            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(getApplicationContext(), RegistrationActivity.class));

            }
        });
    }
}



