package com.example.classattendance;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash2);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();

                if(currentUser==null){

                    startActivity(new Intent(SplashActivity.this, Login.class));
                }else{
                    startActivity(new Intent(SplashActivity.this, MainActivity.class));
                }
                startActivity(new Intent(SplashActivity.this, createAccount.class));
                finish();
            }
        } ,1000);
    }

}