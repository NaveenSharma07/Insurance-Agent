package com.example.insuranceagent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.insuranceagent.adapters.adapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class rm_by_user extends AppCompatActivity {
    RecyclerView recyclerview;
    DatabaseReference reference;
    adapter Adapter;
    ArrayList<userdata> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rm_by_user);

        recyclerview = findViewById(R.id.userList);
        reference = FirebaseDatabase.getInstance().getReference().child("Add User");
        list = new ArrayList<>();
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        Adapter = new adapter(this,list);
        recyclerview.setAdapter(Adapter);

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    userdata name = dataSnapshot.getValue(userdata.class);
                    list.add(name);
                }
                Adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}