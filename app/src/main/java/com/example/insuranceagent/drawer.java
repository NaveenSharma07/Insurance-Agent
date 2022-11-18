package com.example.insuranceagent;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;

import java.util.ArrayList;

public class drawer extends AppCompatActivity implements View.OnClickListener{
    Toolbar toolbar;
    DrawerLayout drawer;
    ActionBarDrawerToggle toggle;
    TextView addPolicy ,adduser , RenewalReminder , assignpolicy , addcategory ,rladduser,rladdpolicyy, rlrenewalreminder, rladdcategory, rlassignpolicy, rlpaymentstatus;
    private ImageSlider imageslider;
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawer);
        initView();
    }
    public void initView(){
        //dashboard
        rladdpolicyy = findViewById(R.id.rladdpolicyy);
        rladduser = findViewById(R.id.rladduser);
        rlrenewalreminder = findViewById(R.id.rlrenewalreminder);
        rladdcategory = findViewById(R.id.rladdcategory);
        rlassignpolicy = findViewById(R.id.rlassignpolicy);
        imageslider = findViewById(R.id.viewPager);
        rlpaymentstatus = findViewById(R.id.rlpaymentstatus);
        rladdcategory.setOnClickListener(this);
        rladduser.setOnClickListener(this);
        rlrenewalreminder.setOnClickListener(this);
        rladdpolicyy.setOnClickListener(this);
        rlassignpolicy.setOnClickListener(this);
        rlpaymentstatus.setOnClickListener(this);

        ArrayList<SlideModel> slideModels = new ArrayList<>();
        slideModels.add(new SlideModel("https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.freepik.com%2Ffree-photos-vectors%2Fcompany-policies&psig=AOvVaw2BNO6T_IYpr10Sly4fR9oV&ust=1667286731827000&source=images&cd=vfe&ved=0CAwQjRxqFwoTCJjeiZ31ifsCFQAAAAAdAAAAABAJ", ScaleTypes.FIT));
        slideModels.add(new SlideModel("https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.shutterstock.com%2Fsearch%2Fpolicy&psig=AOvVaw2BNO6T_IYpr10Sly4fR9oV&ust=1667286731827000&source=images&cd=vfe&ved=0CAwQjRxqFwoTCJjeiZ31ifsCFQAAAAAdAAAAABAX", ScaleTypes.FIT));
        slideModels.add(new SlideModel("https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.freepik.com%2Ffree-photos-vectors%2Fcompany-policies&psig=AOvVaw2BNO6T_IYpr10Sly4fR9oV&ust=1667286731827000&source=images&cd=vfe&ved=0CAwQjRxqFwoTCJjeiZ31ifsCFQAAAAAdAAAAABAb", ScaleTypes.FIT));
        imageslider.setImageList(slideModels,ScaleTypes.FIT);
        //drawer
        toolbar = findViewById(R.id.toolbar);
        drawer = findViewById(R.id.drawer);
        addPolicy = findViewById(R.id.Policy);
        adduser = findViewById(R.id.Adduser);
        addcategory = findViewById(R.id.addcategory);
        RenewalReminder = findViewById(R.id.Renewals);
        assignpolicy = findViewById(R.id.assignpolicy);
        addPolicy.setOnClickListener(this);
        adduser.setOnClickListener(this);
        addcategory.setOnClickListener(this);
        RenewalReminder.setOnClickListener(this);
        assignpolicy.setOnClickListener(this);
        toggle = new ActionBarDrawerToggle(this,drawer,toolbar,R.string.open,0);
        toggle.syncState();
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.Policy:
                Intent intent = new Intent(drawer.this,addpolicy.class);
                startActivity(intent);
                break;
            case R.id.Adduser:
                Intent i = new Intent(drawer.this,adduser.class);
                startActivity(i);
                break;
            case R.id.addcategory:
                Intent i2 = new Intent(drawer.this,addcategory.class);
                startActivity(i2);
                break;
            case R.id.Renewals:
                Intent I = new Intent(drawer.this, RenewalReminder.class);
                startActivity(I);
                break;
            case R.id.assignpolicy:
                Intent I1 = new Intent(drawer.this, AssignPolicy.class);
                startActivity(I1);
                break;
            case R.id.rladdpolicyy:
                Intent i8 = new Intent(drawer.this, addpolicy.class);
                startActivity(i8);
                break;
            case R.id.rladduser:
                Intent i3 = new Intent(drawer.this, adduser.class);
                startActivity(i3);
                break;
            case R.id.rladdcategory:
                Intent i4 = new Intent(drawer.this, addcategory.class);
                startActivity(i4);
                break;
            case R.id.rlrenewalreminder:
                Intent i5 = new Intent(drawer.this, RenewalReminder.class);
                startActivity(i5);
                break;
            case R.id.rlassignpolicy:
                Intent i6 = new Intent(drawer.this, AssignPolicy.class);
                startActivity(i6);
                break;
            case R.id.rlpaymentstatus:
                Intent i7 = new Intent(drawer.this,Payment_Status.class);
                startActivity(i7);
        }
        if(drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(Gravity.LEFT);
        }
        }
    }

