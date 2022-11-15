package com.example.insuranceagent;

import androidx.appcompat.app.AppCompatActivity;

public class UserHelperclass {
    String Name , Mobileno , Dateofbirth,Anniversary,Address;

    public UserHelperclass(String name, String Mobileno, String Dateofbirth, String Anniversary,String address) {
        this.Name = name;
        this.Mobileno = Mobileno;
        this.Dateofbirth = Dateofbirth;
        this.Anniversary = Anniversary;
        this.Address = address;

    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        this.Name = name;
    }

    public String getMobileno() {
        return Mobileno;
    }

    public void setMobileno(String mobileno) {
        Mobileno = mobileno;
    }

    public String getDateofbirth() {
        return Dateofbirth;
    }

    public void setDateofbirth(String dateofbirth) {
        Dateofbirth = dateofbirth;
    }

    public String getAnniversary() {
        return Anniversary;
    }

    public void setAnniversary(String anniversary) {
        Anniversary = anniversary;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        this.Address = address;
    }

    }

