package com.example.insuranceagent;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

public class otp extends AppCompatActivity {
    EditText box1,box2,box3,box4,box5,box6;
    TextView Otp,text,showno;
    Button Submit;
    String Otpbackend;
    ProgressBar progress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.otp);

        box1 = findViewById(R.id.box1);
        box2 = findViewById(R.id.box2);
        box3 = findViewById(R.id.box3);
        box4 = findViewById(R.id.box4);
        box5 = findViewById(R.id.box5);
        box6 = findViewById(R.id.box6);
        showno = findViewById(R.id.showno);
        Otp = findViewById(R.id.Otp);
        Submit = findViewById(R.id.Submit);
        progress = findViewById(R.id.progress);

        showno.setText(String.format(
                "+91-%s",getIntent().getStringExtra("mobile")
        ));
        Otpbackend = getIntent().getStringExtra("backendotp");
        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!box1.getText().toString().trim().isEmpty()&&!box2.getText().toString().trim().isEmpty()&&!box3.getText().toString().trim().isEmpty()&&!box4.getText().toString().trim().isEmpty()&&!box5.getText().toString().trim().isEmpty()&&!box6.getText().toString().trim().isEmpty()){
                    String Otpcode = box1.getText().toString()+
                                     box2.getText().toString()+
                            box3.getText().toString()+
                            box4.getText().toString()+
                            box5.getText().toString()+
                            box6.getText().toString();
                     if(Otpbackend!=null){
                         Submit.setVisibility(View.INVISIBLE);
                         progress.setVisibility(View.VISIBLE);
                         Otp.setVisibility(View.INVISIBLE);

                         PhoneAuthCredential phoneAuthCredential= PhoneAuthProvider.getCredential(
                                 Otpbackend , Otpcode
                         );
                         FirebaseAuth.getInstance().signInWithCredential(phoneAuthCredential)
                             .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                 @Override
                                 public void onComplete(@NonNull Task<AuthResult> task) {
                                     progress.setVisibility(View.GONE);
                                     Submit.setVisibility(View.VISIBLE);
                                     Otp.setVisibility(View.VISIBLE);
                                     if(task.isSuccessful()){
                                         Intent intent = new Intent(getApplicationContext(),drawer.class);
                                         intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                         startActivity(intent);
                                 }
                                     else{
                                         Toast.makeText(otp.this,"Please Enter Correct Otp",Toast.LENGTH_SHORT).show();
                                     }
                             }
                         });
                     }
                     else{
                         Toast.makeText(otp.this,"Please Check Internet Connection",Toast.LENGTH_SHORT).show();
                     }
                    Toast.makeText(otp.this, "OTP verify", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(otp.this, "Please enter all number", Toast.LENGTH_SHORT).show();
                }
            }
        });
        numberotpmove();
    }
    private void numberotpmove(){
        box1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(!showno.toString().trim().isEmpty()){
                    box2.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        box2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(!showno.toString().trim().isEmpty()){
                    box3.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        box3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(!showno.toString().trim().isEmpty()){
                    box4.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        box4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(!showno.toString().trim().isEmpty()){
                    box5.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        box5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(!showno.toString().trim().isEmpty()){
                    box6.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }
}

