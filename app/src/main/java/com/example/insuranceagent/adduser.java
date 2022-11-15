package com.example.insuranceagent;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
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
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.Calendar;

public class adduser extends AppCompatActivity {
    int REQUEST_CODE = 100;
    DatePickerDialog datepickerdialog , datepickerdialog1;
    EditText Name, Mobileno, Address;
    Button user, Dateofbirth, Anniversary, IDProof;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference reference;
    StorageReference storageReference;
    private Uri imageuri;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adduser);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Name = findViewById(R.id.Name);
        Mobileno = findViewById(R.id.Mobileno);
        Address = findViewById(R.id.Address);
        Anniversary = findViewById(R.id.Anniversery);
        IDProof = findViewById(R.id.IDProof);
        user = findViewById(R.id.user);
        Dateofbirth = findViewById(R.id.Dateofbirth);
        firebaseDatabase = FirebaseDatabase.getInstance();
        reference = FirebaseDatabase.getInstance().getReference();
        storageReference = FirebaseStorage.getInstance().getReference();
        Dateofbirth.setText(getTodayDate());
        initDatePicker();
        Anniversary.setText(getTodayDate());
        initDatePicker1();
        CameraPermission();
        IDProof.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startCamera();
            }
        });
        user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!Name.getText().toString().trim().isEmpty()&&!Mobileno.getText().toString().trim().isEmpty()&&!Dateofbirth.getText().toString().trim().isEmpty()&&!Anniversary.getText().toString().trim().isEmpty()&&!Address.getText().toString().trim().isEmpty()){
                    String name = Name.getText().toString();
                    String mobileno = Mobileno.getText().toString();
                    String dateofbirth = Dateofbirth.getText().toString();
                    String anniversary = Anniversary.getText().toString();
                    String address = Address.getText().toString();
                    UserHelperclass helperclass = new UserHelperclass(name, mobileno, dateofbirth, anniversary, address);
                    reference.child("Add User").push().setValue(helperclass).addOnCompleteListener(task ->
                    {
                        if (task.isSuccessful()) {
                            Toast.makeText(adduser.this, "New User Added Sucessfully", Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(adduser.this, drawer.class);
                            startActivity(i);
                        } else {
                            Toast.makeText(adduser.this, "Error", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
                else{
                    Name.setError("Can't be Empty");
                    Mobileno.setError("Can't be Empty");
                    Address.setError("Can't be Empty");
                }
            }
        });
    }
    void CameraPermission(){
        int CameraPermission = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA);
        if(CameraPermission != PackageManager.PERMISSION_GRANTED){
            Log.e("Checking Permission","Not Granted");
            String str[] = new String[1];
            str[0] = Manifest.permission.CAMERA;
            ActivityCompat.requestPermissions(this,str,1);
        }
        else{
            Log.e("Checking Permission","Granted");
            Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show();
        }
    }
    void startCamera(){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent,REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == Activity.RESULT_OK){
            if(resultCode == REQUEST_CODE){
               onCaptureImageResult(data);
            }
        }
    }

    private void onCaptureImageResult(Intent data){
        Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        thumbnail.compress(Bitmap.CompressFormat.PNG,90,bytes);
        byte bb[] = bytes.toByteArray();
        String file = Base64.encodeToString(bb, Base64.DEFAULT);
        uploadToFirebase(bb);
    }

    private void uploadToFirebase(byte[] bb){
        StorageReference sr = storageReference.child("myimages/a.jpg");
        sr.putBytes(bb).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Toast.makeText(adduser.this,"Successfully Uploaded",Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(adduser.this,""+"Failed To Upload",Toast.LENGTH_SHORT).show();
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
                Dateofbirth.setText(date);
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
                Anniversary.setText(date);
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

