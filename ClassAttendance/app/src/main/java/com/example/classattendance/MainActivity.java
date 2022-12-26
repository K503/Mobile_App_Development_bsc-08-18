package com.example.classattendance;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
        Button logout;
        Button viewClass, createClass;
        Button viewStudent;
        Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //new Teacher.Builder().build();
       // myClass = (Button) findViewById(R.id.createClass);
        viewClass = (Button) findViewById(R.id.viewClasses);
        viewStudent = (Button) findViewById(R.id.buttonViewstudent);
        createClass = (Button) findViewById(R.id.createClass);
        logout = (Button) findViewById(R.id. buttonlogout);


        viewClass.setOnClickListener(view -> startActivity(new Intent(MainActivity.this, viewClasses.class)));
        viewStudent.setOnClickListener(view -> startActivity(new Intent(MainActivity.this, StudentActivity.class)));
        createClass.setOnClickListener(view -> startActivity(new Intent(this, viewClasses.class)));
        setToolbar();
    }

    public void logout(View view){
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(getApplicationContext(),Login.class));
        finish();
    }

    private void setToolbar() {
        toolbar = findViewById(R.id.toolBar);
        TextView title = toolbar.findViewById(R.id.titleToolBar);
        TextView subtitle = toolbar.findViewById(R.id.subtitleToolBar);
        ImageButton back = toolbar.findViewById(R.id.back);
        ImageButton save = toolbar.findViewById(R.id.save);

        title.setText("ATTENDANCE APP");
        subtitle.setVisibility(View.GONE);
        back.setVisibility(View.INVISIBLE);
        save.setVisibility(View.INVISIBLE);
    }

}