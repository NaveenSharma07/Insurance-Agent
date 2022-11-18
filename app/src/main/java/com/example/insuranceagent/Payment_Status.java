package com.example.insuranceagent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class Payment_Status extends AppCompatActivity {
    Spinner spinner_policy,spinner_user,spinner_status;
    Button add;
    String Category[];
    FirebaseDatabase firebaseDatabase;
    DatabaseReference reference,Reference,status_reference;
    ArrayList<String> list,List,status_list;
    ArrayAdapter<String> adapter,Adapter,status_adapter;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_status);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        add = findViewById(R.id.add);
        spinner_policy = findViewById(R.id.spinner_policy);
        spinner_user = findViewById(R.id.spinner_user);
        spinner_status = findViewById(R.id.spinner_status);

        reference = FirebaseDatabase.getInstance().getReference().child("Add Policy");
        Reference = FirebaseDatabase.getInstance().getReference().child("Add User");
        status_reference = FirebaseDatabase.getInstance().getReference().child("Payment Status");

        list = new ArrayList<String>();
        List = new ArrayList<String>();
        status_list = new ArrayList<String>();

        adapter = new ArrayAdapter<String>(Payment_Status.this, android.R.layout.simple_spinner_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);

        Adapter = new ArrayAdapter<String>(Payment_Status.this, android.R.layout.simple_spinner_item, List);
        Adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);

        status_adapter = new ArrayAdapter<String>(Payment_Status.this, android.R.layout.simple_spinner_item, status_list);
        status_adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);

        fetchdata();
        spinner_policy.setAdapter(adapter);
        spinner_user.setAdapter(Adapter);
        spinner_status.setAdapter(status_adapter);

        spinner_policy.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String value = spinner_policy.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        spinner_user.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String value = spinner_user.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        spinner_status.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String value = spinner_status.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HashMap<String, Object> m = new HashMap<String, Object>();
                m.put("Policy", spinner_policy.getSelectedItem().toString());
                m.put("Name", spinner_user.getSelectedItem().toString());
                m.put("status", spinner_status.getSelectedItem().toString());

                FirebaseDatabase.getInstance().getReference().child("Status").push().setValue(m);
                adapter.notifyDataSetChanged();
                Toast.makeText(Payment_Status.this, "Status Updated Successfully", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(Payment_Status.this, drawer.class);
                startActivity(i);
            }
        });
    }
    public void fetchdata() {
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot categoryData : snapshot.getChildren()) {
                    String category = categoryData.child("Policy").getValue(String.class);
                    list.add(category);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        Reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot categoryData : snapshot.getChildren()) {
                    String category = categoryData.child("name").getValue(String.class);
                    List.add(category);
                }
                Adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        status_reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot categoryData : snapshot.getChildren()) {
                    String category = categoryData.child("Policy").getValue(String.class);
                    status_list.add(category);
                }
                status_adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}