package com.example.insuranceagent;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class RenewalReminder extends AppCompatActivity {
    TextView rlbyuser,rlbycategory,rlbydate,rlbycompany;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.renewalreminder);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        initview();
    }
    public void initview(){
        rlbyuser=findViewById(R.id.rlbyuser);
        rlbycategory=findViewById(R.id.rlbycategory);
        rlbydate=findViewById(R.id.rlbydate);
        rlbycompany=findViewById(R.id.rlbycompany);

        rlbyuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        rlbycompany.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        rlbycategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        rlbydate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}




