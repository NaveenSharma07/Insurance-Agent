package com.example.insuranceagent;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

public class AssignPolicy extends AppCompatActivity {
    Spinner spinner, spinner1;
    Button add, dateofissue, dateofexpiry;
    String policy[];
    String Name[];
    FirebaseDatabase firebaseDatabase;
    DatabaseReference reference, referenceUser,Reference;
    ArrayList<String> list,userlist;
    ArrayAdapter<String> adapter , useradapter;
    Button newuser;
    DatePickerDialog datepickerdialog, datepickerdialog1;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.assignpolicy);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        spinner = findViewById(R.id.spinner);
        spinner1 = findViewById(R.id.spinner1);
        newuser = findViewById(R.id.newuser);
        add = findViewById(R.id.add);
        dateofissue = findViewById(R.id.dateofissue);
        dateofexpiry = findViewById(R.id.dateofexpiryy);
        Reference = FirebaseDatabase.getInstance().getReference();
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
                String value1 = spinner.getSelectedItem().toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String value = adapterView.getItemAtPosition(i).toString();
                String value2 = spinner.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        dateofissue.setText(getTodayDate());
        initDatePicker();
        dateofexpiry.setText(getTodayDate());
        initDatePicker1();
        newuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(AssignPolicy.this,adduser.class);
                startActivity(i);
            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HashMap<String, Object> m = new HashMap<String, Object>();
                m.put("Policy", spinner.getSelectedItem().toString());
                m.put("User", spinner1.getSelectedItem().toString());
                m.put("dateofissue", dateofissue.getText().toString());
                m.put("dateofexpiry", dateofexpiry.getText().toString());
                String Policy = spinner.getSelectedItem().toString();
                String User = spinner1.getSelectedItem().toString();
                String Dateofissue = dateofissue.getText().toString();
                String Dateofexpiry = dateofexpiry.getText().toString();
                FirebaseDatabase.getInstance().getReference().child("Assign Policy").push().setValue(m);
                adapter.notifyDataSetChanged();
                Toast.makeText(AssignPolicy.this, "New Policy Assigned to User", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(AssignPolicy.this,drawer.class);
                startActivity(intent);
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
    private String getTodayDate() {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        month = month + 1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        return makeDateString(day, month, year);
    }

    private void initDatePicker() {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                String date = makeDateString(day, month, year);
                dateofissue.setText(date);
            }
        };
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        int style = AlertDialog.THEME_HOLO_LIGHT;

        datepickerdialog = new DatePickerDialog(this, style, dateSetListener, year, month, day);

    }

    private String makeDateString(int day, int month, int year) {
        return getMonthFormat(month) + " " + day + " " + year;
    }

    private String getMonthFormat(int month) {
        if (month == 1) {
            return "JAN";
        }
        if (month == 2) {
            return "FEB";
        }
        if (month == 3) {
            return "MAR";
        }
        if (month == 4) {
            return "APR";
        }
        if (month == 5) {
            return "MAY";
        }
        if (month == 6) {
            return "JUN";
        }
        if (month == 7) {
            return "JUL";
        }
        if (month == 8) {
            return "AUG";
        }
        if (month == 9) {
            return "SEP";
        }
        if (month == 10) {
            return "OCT";
        }
        if (month == 11) {
            return "NOV";
        }
        if (month == 12) {
            return "DEC";
        }
        return "Jan";
    }

    public void openDatePicker(View view) {
        datepickerdialog.show();
    }

    private String getTodayDate1() {
        Calendar cal = Calendar.getInstance();
        int year1 = cal.get(Calendar.YEAR);
        int month1 = cal.get(Calendar.MONTH);
        month1 = month1 + 1;
        int day1 = cal.get(Calendar.DAY_OF_MONTH);
        return makeDateString1(day1, month1, year1);
    }
    private void initDatePicker1() {
        DatePickerDialog.OnDateSetListener dateSetListener1 = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(  DatePicker datePicker, int year1, int month1, int day1) {
                month1 = month1 + 1;
                String date = makeDateString1(day1, month1, year1);
                dateofexpiry.setText(date);
            }
        };
        Calendar cal = Calendar.getInstance();
        int year1 = cal.get(Calendar.YEAR);
        int month1 = cal.get(Calendar.MONTH);
        int day1 = cal.get(Calendar.DAY_OF_MONTH);
        int style1 = AlertDialog.THEME_HOLO_LIGHT;

        datepickerdialog1 = new DatePickerDialog(this, style1, dateSetListener1, year1, month1, day1);

    }

    private String makeDateString1(int day1, int month1, int year1) {
        return getMonthFormat1(month1) + " " + day1 + " " + year1;
    }

    private String getMonthFormat1(int month1) {
        if (month1 == 1) {
            return "JAN";
        }
        if (month1 == 2) {
            return "FEB";
        }
        if (month1 == 3) {
            return "MAR";
        }
        if (month1 == 4) {
            return "APR";
        }
        if (month1 == 5) {
            return "MAY";
        }
        if (month1 == 6) {
            return "JUN";
        }
        if (month1 == 7) {
            return "JUL";
        }
        if (month1 == 8) {
            return "AUG";
        }
        if (month1 == 9) {
            return "SEP";
        }
        if (month1 == 10) {
            return "OCT";
        }
        if (month1 == 11) {
            return "NOV";
        }
        if (month1 == 12) {
            return "DEC";
        }
        return "Jan";
    }

    public void openDatePicker1(View view) {
        datepickerdialog1.show();
    }
}










