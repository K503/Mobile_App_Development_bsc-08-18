package com.example.classattendance;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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

            createAccount.setOnClickListener(view -> startActivity(new Intent(Login.this, createAccount.class)));

            login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String email = Email.getText().toString().trim();
                    String password = Password.getText().toString().trim();

                    if (TextUtils.isEmpty(email)){
                        Email.setError("Email is required");
                        return;
                    }
                    if (TextUtils.isEmpty(password)){
                        Password.setError("password is required");
                        return;
                    }
                    if (password.length() < 6){
                        Password.setError("password too short");
                    }

                    // authentication
                    authentication.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                           if(task.isSuccessful()){
                               Toast.makeText(Login.this, "successfully logged in", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        }
                        else{
                               Toast.makeText(Login.this, "Error!" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                           }
                        }

                    });
                }
            });
    }
}

