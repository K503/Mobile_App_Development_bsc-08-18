package com.example.classattendance;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class createAccount extends AppCompatActivity {
    EditText email_Address, Account_Password, Confirm_Password, Full_name;
    TextView login;
    MaterialButton sign_upBtn;
    ImageView back_Arrow;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        email_Address = (EditText) findViewById(R.id.emailAddress);
        Account_Password = (EditText) findViewById(R.id.Accountpassword);
        Confirm_Password = (EditText) findViewById(R.id.Confirmpassword);
        Full_name = (EditText) findViewById(R.id.Fullname);
        sign_upBtn = (MaterialButton) findViewById(R.id.signupBtn);
        back_Arrow = (ImageView) findViewById(R.id.backArrow);
        auth = FirebaseAuth.getInstance();
        login = (TextView) findViewById(R.id.LOGIN);

//        if (auth.getCurrentUser() != null){
//            startActivity(new Intent(getApplicationContext(), MainActivity.class));
//            finish();
//        }


        sign_upBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = email_Address.getText().toString().trim();
                String password = Account_Password.getText().toString().trim();

                if (TextUtils.isEmpty(email)){
                    email_Address.setError("Email is required");
                    return;
                }
                if (TextUtils.isEmpty(password)){
                    Account_Password.setError("password is required");
                    return;
                }
                if (password.length() < 6){
                     Account_Password.setError("password too short");
                }

                auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(createAccount.this, "account created", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        } else{
                            Toast.makeText(createAccount.this, "Error!" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Login.class));
            }
        });
    }
}
