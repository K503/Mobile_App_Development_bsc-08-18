package com.example.classattendance;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.database.CursorWindow;
import java.lang.reflect.Field;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

public class StudentActivity extends AppCompatActivity {

    Toolbar toolbar;
    private String courseName;
    private String courseCode;
    private int position;
    private RecyclerView recyclerView;
    private StudentAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<StudentItem> studentItems = new ArrayList<>();
    private DbHelper dbHelper;
    private int class_id;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);

        dbHelper = new DbHelper(this);
        Intent intent = getIntent();
        courseName = intent.getStringExtra("courseName");
        courseCode = intent.getStringExtra("courseCode");
        position = intent.getIntExtra("position", -1);
        class_id =  intent.getIntExtra("class_id", -1);
        loadData();
        recyclerView = findViewById(R.id.student_recycler);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new StudentAdapter(this,studentItems);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(position -> changeStatus(position));

        setToolbar();
    }

    private void loadData() {
        Cursor cursor = dbHelper.getStudentTable(class_id);
        studentItems.clear();
//        while (cursor.moveToNext()){
//            long sid = cursor.getLong(cursor.getColumnIndex(DbHelper.S_ID));
//            int roll = cursor.getInt(cursor.getInt(cursor.getColumnIndex(DbHelper.STUDENT_ROLL_KEY)));
//            String name = cursor.getString(cursor.getColumnIndex(DbHelper.STUDENT_NAME_KEY));
//            studentItems.add(new StudentItem(sid,roll,name));
//        }
        cursor.close();
    }

    private void changeStatus(int position) {
        String status = studentItems.get(position).getStatus();
        if (status.equals("P")) status = "A";

        else status = "P";

        studentItems.get(position).setStatus(status);
        adapter.notifyItemChanged(position);
    }

    private void setToolbar() {
        toolbar = findViewById(R.id.toolBar);
        TextView title = toolbar.findViewById(R.id.titleToolBar);
        TextView subtitle = toolbar.findViewById(R.id.subtitleToolBar);
        ImageButton back = toolbar.findViewById(R.id.back);
        ImageButton save = toolbar.findViewById(R.id.save);

        title.setText(courseName);
        subtitle.setText(courseCode);
        back.setOnClickListener(view ->onBackPressed());

        toolbar.inflateMenu(R.menu.student_menu);
        toolbar.setOnMenuItemClickListener(menuItem -> onMenuItemClick(menuItem));

    }

    private boolean onMenuItemClick(MenuItem menuItem) {
        if (menuItem.getItemId()==R.id.add_student){
            showAddStudentDialogue();
        }
        return true;
    }

    private void showAddStudentDialogue() {
        MyDialog dialog = new MyDialog();
        dialog.show(getSupportFragmentManager(),MyDialog.STUDENT_ADD_DIALOG);
        dialog.setListener((roll,name) -> addStudent(roll,name));
    }

    private void addStudent(String roll_string, String name) {
        int roll = Integer.parseInt(roll_string);
        long sid = dbHelper.addStudent(class_id,roll,name);
        StudentItem studentItem =  new StudentItem(sid,roll,name);
        studentItems.add(studentItem);
        adapter.notifyDataSetChanged();
    }
}