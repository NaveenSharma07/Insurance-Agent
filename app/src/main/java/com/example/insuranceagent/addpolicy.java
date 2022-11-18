package com.example.insuranceagent;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
public class addpolicy extends AppCompatActivity {
    Spinner spinner;
    EditText Amount, duration, Company, Policy;
    Button add;
    String Category[];
    FirebaseDatabase firebaseDatabase;
    DatabaseReference reference;
    ArrayList<String> list;
    ArrayAdapter<String> adapter;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addpolicy);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        spinner = findViewById(R.id.spinner);
        Amount = findViewById(R.id.Amount);
        duration = findViewById(R.id.duration);
        Company = findViewById(R.id.Company);
        Policy = findViewById(R.id.Policy);
        add = findViewById(R.id.add);
        reference = FirebaseDatabase.getInstance().getReference().child("Add Category");
        list = new ArrayList<String>();
        adapter = new ArrayAdapter<String>(addpolicy.this, android.R.layout.simple_spinner_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        fetchdata();
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String value = spinner.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HashMap<String, Object> m = new HashMap<String, Object>();
                m.put("Policy", Policy.getText().toString());
                m.put("Company", Company.getText().toString());
                m.put("duration", duration.getText().toString());
                m.put("Amount", Amount.getText().toString());
                m.put("Category", spinner.getSelectedItem().toString());
                String policy = Policy.getText().toString();
                String company = Company.getText().toString();
                String Duration = duration.getText().toString();
                String amount = Amount.getText().toString();
                String Category = spinner.getSelectedItem().toString();

                FirebaseDatabase.getInstance().getReference().child("Add Policy").push().setValue(m);
                adapter.notifyDataSetChanged();
                Toast.makeText(addpolicy.this, "New Policy Added Sucessfully", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(addpolicy.this, drawer.class);
                startActivity(i);
            }
        });
    }

    public void fetchdata() {
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot categoryData : snapshot.getChildren()) {
                    String category = categoryData.child("category").getValue(String.class);
                    list.add(category);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}