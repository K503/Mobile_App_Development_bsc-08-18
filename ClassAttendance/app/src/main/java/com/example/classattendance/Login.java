package com.example.classattendance;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {

    MaterialButton login;
    EditText Email, Password;
    TextView createAccount;
    FirebaseAuth authentication = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


            login = (MaterialButton) findViewById(R.id.loginBtn);
            Email = (EditText) findViewById(R.id.email);
            Password = (EditText) findViewById(R.id.password);
            createAccount= (TextView) findViewById(R.id.createAccount);

            login.setOnClickListener(view -> loginUser());
            createAccount.setOnClickListener(view -> startActivity(new Intent(Login.this, createAccount.class)));

//        login.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View view) {
//                if (Email.getText().toString().equals("me@gmail.com") && password.getText().toString().equals("1234")){
//                    //correct
//                    Toast.makeText(loginActivity.this, "login successful", Toast.LENGTH_SHORT).show();
//                }
//                else{
//                    //incorrect
//                    Toast.makeText(loginActivity.this, "login failed", Toast.LENGTH_SHORT).show();
//                }
//            }
//
//
//        });
        }
        void loginUser(){
            //take user information
            String email = Email.getText().toString();
            String password = Password.getText().toString();

            boolean isValidated = validateData(email, password);
            if(!isValidated){
                return;
            }
            loginAccountInFirebase(email,password);
        }

        void loginAccountInFirebase(String email, String password){

            authentication.signInWithEmailAndPassword(email,password).addOnCompleteListener(this, task -> {
            if (task.isSuccessful()){
                //login is successful
                    if(authentication.getCurrentUser().isEmailVerified()){
                        //go to menu
                        startActivity(new Intent(Login.this, MainActivity.class));

                    }else{
                        //email not verified
                        Utility.showToast(Login.this, "the email is not verified");
                    }
            }else{
                //log in failed
                Utility.showToast(Login.this, task.getException().getLocalizedMessage());
            }
            });


        }

        void changeInProgress(boolean inProgress){
            if(inProgress){
                login.setVisibility(View.VISIBLE);
            }else{
                login.setVisibility(View.VISIBLE);
            }
        }

        boolean validateData(String email, String password){
            // this will validate the data for the user logging in
            if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                Email.setError("invalid email");
                return false;
            }
            if(password.length() < 7){
                Password.setError("password length is invalid");
                return false;
            }
            return true;
        }
    }
