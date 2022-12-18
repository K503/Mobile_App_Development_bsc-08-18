package com.example.classattendance;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.MenuItem;
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
    ArrayList<StudentItem> studentItems = new ArrayList<>();
    Toolbar toolbar;
    DbHelper dbHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_classes);


        dbHelper = new DbHelper(this);
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
        loadData();
    }

    private void loadData() {
        Cursor cursor = dbHelper.getClassTable();

        myClasses.clear();
        while (cursor.moveToNext()){

            long id =cursor.getInt(cursor.getColumnIndex(DbHelper.C_ID));
            String courseName = cursor.getString(cursor.getColumnIndex(DbHelper.COURSE_NAME_KEY));
            String courseCode = cursor.getString(cursor.getColumnIndex(DbHelper.COURSE_CODE_KEY));

            myClasses.add(new MyClasses(courseName,courseCode,id));

        }
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
        intent.putExtra("class_id",myClasses.get(position).getClass_id());
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
        long class_id = dbHelper.addClass(courseName,courseCode);
        MyClasses myClass = new MyClasses(courseName,courseCode,class_id);
        myClasses.add(myClass);
        classAdapter.notifyDataSetChanged();


    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case 0:
                showUpdateDialog(item.getGroupId());
            case 1:
                deleteClass(item.getGroupId());
        }
        return super.onContextItemSelected(item);
    }

    private void showUpdateDialog(int position) {
        MyDialog dialog = new MyDialog();
        dialog.show(getSupportFragmentManager(),MyDialog.CLASS_UPDATE_DIALOG);
        dialog.setListener((courseName,courseCode) -> updateClass(position,courseName,courseCode));
    }

    private void updateClass(int position, String courseName, String courseCode) {
        dbHelper.updateClass(myClasses.get(position).getClass_id(),courseName,courseCode);
        myClasses.get(position).setCourseName(courseName);
        myClasses.get(position).setCourseCode(courseCode);
        classAdapter.notifyItemChanged(position);

    }

    private void deleteClass(int position) {
        dbHelper.deleteClass(myClasses.get(position).getClass_id());
        myClasses.remove(position);
        classAdapter.notifyItemRemoved(position);
    }
}
