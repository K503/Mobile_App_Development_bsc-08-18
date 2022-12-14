package com.example.classattendance;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class viewClasses extends AppCompatActivity {
    FloatingActionButton createClassBtn;
    RecyclerView recyclerView;
    ClassAdapter classAdapter;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<MyClasses> myClasses = new ArrayList<>();
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_classes);

        createClassBtn = findViewById(R.id.createClassBtn);
        createClassBtn.setOnClickListener(v -> showDialog());

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        classAdapter = new ClassAdapter(this, myClasses);
        recyclerView.setAdapter(classAdapter);
        classAdapter.setOnItemClickListener(position -> gotoitemActivity(position));
        setToolbar();
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

    private void gotoitemActivity(int position) {
        Intent intent = new Intent(this, StudentActivity.class);

        intent.putExtra("courseName", myClasses.get(position).getCourseName());
        intent.putExtra("courseCode", myClasses.get(position).getCourseCode());
        intent.putExtra("position",position);
        startActivity(intent);
    }

    private void showDialog(){
        MyDialog dialog = new MyDialog();
        dialog.show(getSupportFragmentManager(), MyDialog.CLASS_ADD_DIALOG);
        dialog.setListener((courseName,courseCode)->addClass(courseName, courseCode));



//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        View view = LayoutInflater.from(this).inflate(R.layout.dialog, null);
//        //AlertDialog dialog = builder.create();
//        builder.setView(view);
//        AlertDialog dialog = builder.create();
//        dialog.show();
//        //builder.create().show();
//
//
//
//        courseNameEdit = view.findViewById(R.id.courseNameEdit);
//        courseCodeEdit = view.findViewById(R.id.courseCodeEdit);
//        Button cancel = view.findViewById(R.id.cancelBtn);
//        Button addBtn = view.findViewById(R.id.addBtn);
//
//        cancel.setOnClickListener(v -> dialog.dismiss());
//        addBtn.setOnClickListener(v -> {
//            addClass();
//            dialog.dismiss();
//        });
    }

    private void addClass(String courseName, String courseCode) {
        myClasses.add(new MyClasses(courseName, courseCode));
        classAdapter.notifyDataSetChanged();
    }
}
