package com.example.insuranceagent;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Locale;

public class addcategory extends AppCompatActivity {
    EditText Category;
    TextView category1,CompanyName;
    Button addcategory;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference reference;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addcategory);
        Category = findViewById(R.id.Category);
        addcategory = findViewById(R.id.addcategory);

        firebaseDatabase = FirebaseDatabase.getInstance();
        reference = FirebaseDatabase.getInstance().getReference();

        addcategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!Category.getText().toString().isEmpty()) {
                    String category = Category.getText().toString();
                    Member helperclass = new Member(category);
                    reference.child("Add Category").push().setValue(helperclass).addOnCompleteListener(task ->
                    {
                        if (task.isSuccessful()) {
                            Toast.makeText(addcategory.this, "New Category Added Sucessfully", Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(addcategory.this, drawer.class);
                            startActivity(i);
                        } else {
                            Toast.makeText(addcategory.this, "Error", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
                else{
                    Category.setError("Can't be Empty");
                }
            }
        });


    }
}
