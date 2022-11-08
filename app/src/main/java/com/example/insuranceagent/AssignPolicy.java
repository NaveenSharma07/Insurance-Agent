package com.example.insuranceagent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
public class AssignPolicy extends AppCompatActivity {
    Spinner spinner, spinner1;
    Button add;
    String policy[];
    String Name[];
    FirebaseDatabase firebaseDatabase;
    DatabaseReference reference, referenceUser;
    ArrayList<String> list,userlist;
    ArrayAdapter<String> adapter , useradapter;
    Button newuser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.assignpolicy);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        spinner = findViewById(R.id.spinner);
        spinner1 = findViewById(R.id.spinner1);
        newuser = findViewById(R.id.newuser);
        reference = FirebaseDatabase.getInstance().getReference().child("Add Policy");
        referenceUser = FirebaseDatabase.getInstance().getReference().child("Add User");
        list = new ArrayList<String>();
        userlist = new ArrayList<String>();
        adapter = new ArrayAdapter<String>(AssignPolicy.this, android.R.layout.simple_spinner_dropdown_item, list);
        useradapter = new ArrayAdapter<String>(AssignPolicy.this, android.R.layout.simple_spinner_dropdown_item, userlist);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        fetchdata();
        spinner.setAdapter(adapter);
        spinner1.setAdapter(useradapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String value = adapterView.getItemAtPosition(i).toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String value = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        newuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(AssignPolicy.this,adduser.class);
                startActivity(i);
            }
        });
    }
    public void fetchdata() {

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot policyData : snapshot.getChildren()) {
                    String policy = policyData.child("Policy").getValue(String.class);
                    list.add(policy);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        referenceUser.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot areaSnapshot : snapshot.getChildren()) {
                    String name = areaSnapshot.child("name").getValue(String.class);
                    userlist.add(name);
                }
                useradapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
}





