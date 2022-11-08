package com.example.insuranceagent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class LoginActivity extends AppCompatActivity {
    TextView Login, text;
    EditText Mobileno;
    Button Otp;
    ProgressBar progress;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loginactivity);
        initComponent();
    }

    public void initComponent() {
        Mobileno = findViewById(R.id.Mobileno);
        Otp = findViewById(R.id.Otp);
        progress = findViewById(R.id.progress);
        mAuth = FirebaseAuth.getInstance();

        Otp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!Mobileno.getText().toString().trim().isEmpty()) {
                    if ((Mobileno.getText().toString().trim()).length() == 10) {
                        progress.setVisibility(View.VISIBLE);
                        Otp.setVisibility(View.INVISIBLE);

                        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                                "+91" + Mobileno.getText().toString(),
                                60,
                                TimeUnit.SECONDS,
                                LoginActivity.this,
                                new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                                    @Override
                                    public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                                        Otp.setVisibility(View.VISIBLE);
                                        progress.setVisibility(View.GONE);
                                    }

                                    @Override
                                    public void onVerificationFailed(@NonNull FirebaseException e) {
                                        Otp.setVisibility(View.VISIBLE);
                                        progress.setVisibility(View.GONE);
                                        Toast.makeText(LoginActivity.this, "Please Check Internet Connection", Toast.LENGTH_SHORT).show();
                                    }

                                    @Override
                                    public void onCodeSent(@NonNull String backendotp, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                        Otp.setVisibility(View.VISIBLE);
                                        progress.setVisibility(View.GONE);
                                        Intent intent = new Intent(getApplicationContext(), otp.class);
                                        intent.putExtra("mobile", Mobileno.getText().toString());
                                        intent.putExtra("backendotp", backendotp);
                                        startActivity(intent);
                                    }
                                }
                        );
                    } else {
                        Toast.makeText(LoginActivity.this, "Please enter Correct number", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(LoginActivity.this, "Enter Mobile number", Toast.LENGTH_SHORT).show();
                }
            }

        });
    }
        @Override
        protected void onStart() {
            super.onStart();
            FirebaseUser user = mAuth.getCurrentUser();
            if (user != null) {
                Intent intent = new Intent(LoginActivity.this, drawer.class);
                startActivity(intent);
                this.finish();
            }
    }

    }




