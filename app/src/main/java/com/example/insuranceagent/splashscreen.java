package com.example.insuranceagent;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class splashscreen extends AppCompatActivity implements Runnable{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splashscreen);
        Thread t = new Thread(this);
        t.start();
    }

    @Override
    public void run() {
        try{
            Thread.sleep(2000);
            Intent i = new Intent(this, LoginActivity.class);
            startActivity(i);
        }catch(Exception e){
        }
    }
}
