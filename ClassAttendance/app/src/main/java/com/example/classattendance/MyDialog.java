package com.example.classattendance;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

public class MyDialog extends DialogFragment {

    public static final String CLASS_ADD_DIALOG="addClass";
    public static final String CLASS_UPDATE_DIALOG="updateClass";
    public static final String STUDENT_ADD_DIALOG="addStudent";
    public static final String STUDENT_UPDATE_DIALOG = "updateStudent";

    private onClickListener listener;
    private int roll;
    private String name;

    public MyDialog(int roll, String name) {
        this.roll = roll;
        this.name = name;
    }

    public MyDialog() {

    }

    public interface onClickListener{
        void onclick(String text1, String text2);
    }

    public void setListener(onClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Dialog dialog = null;
        if (getTag().equals(CLASS_ADD_DIALOG))dialog=getAddClassDialog();
        if (getTag().equals(STUDENT_ADD_DIALOG))dialog =getAddStudentDialog();
        if (getTag().equals(CLASS_UPDATE_DIALOG))dialog =getUpdateClassDialog();
        if (getTag().equals(STUDENT_UPDATE_DIALOG))dialog =getUpdateStudentDialog();
        return dialog;
    }

    private Dialog getUpdateStudentDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog, null);
        //AlertDialog dialog = builder.create();
        builder.setView(view);
        TextView title = view.findViewById(R.id.titleDialog);

        title.setText("Update Student");
        EditText roll_edit = view.findViewById(R.id.courseNameEdit);
        EditText name_edit = view.findViewById(R.id.courseCodeEdit);

        roll_edit.setHint("Roll");
        name_edit.setHint("Name of student");
        Button cancel = view.findViewById(R.id.cancelBtn);
        Button add = view.findViewById(R.id.addBtn);
        add.setText("Update");
        roll_edit.setText(roll + "");
        roll_edit.setEnabled(false);
        name_edit.setText(name);

        cancel.setOnClickListener(v -> dismiss());
        add.setOnClickListener(v -> {
            String roll = roll_edit.getText().toString();
            String student_name = name_edit.getText().toString();

            listener.onclick(roll,student_name);
            dismiss();
        });
        return builder.create();
    }

    private Dialog getUpdateClassDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog, null);
        builder.setView(view);
        TextView title = view.findViewById(R.id.titleDialog);

        title.setText("Update Class");
        EditText courseName = view.findViewById(R.id.courseNameEdit);
        EditText courseCode = view.findViewById(R.id.courseCodeEdit);

        courseName.setHint("New Course Name");
        courseCode.setHint("New Course Code");
        Button cancel = view.findViewById(R.id.cancelBtn);
        Button add = view.findViewById(R.id.addBtn);
        add.setText("Update");

        cancel.setOnClickListener(v -> dismiss());
        add.setOnClickListener(v -> {
            String course = courseName.getText().toString();
            String code = courseCode.getText().toString();
            listener.onclick(course,code);
            dismiss();

        });
        return builder.create();
    }

    private Dialog getAddStudentDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog, null);
        //AlertDialog dialog = builder.create();
        builder.setView(view);
        TextView title = view.findViewById(R.id.titleDialog);

        title.setText("Add New Student");
        EditText roll_edit = view.findViewById(R.id.courseNameEdit);
        EditText name_edit = view.findViewById(R.id.courseCodeEdit);

        roll_edit.setHint("Roll");
        name_edit.setHint("Name of student");
        Button cancel = view.findViewById(R.id.cancelBtn);
        Button add = view.findViewById(R.id.addBtn);

        cancel.setOnClickListener(v -> dismiss());
        add.setOnClickListener(v -> {
            String roll = roll_edit.getText().toString();
            String student_name = name_edit.getText().toString();
            roll_edit.setText(String.valueOf(Integer.parseInt(roll)+1));
            name_edit.setText("");
            listener.onclick(roll,student_name);
        });
           return builder.create();

    }

    private Dialog getAddClassDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog, null);
        //AlertDialog dialog = builder.create();
        builder.setView(view);
        TextView title = view.findViewById(R.id.titleDialog);




        EditText courseNameEdit = view.findViewById(R.id.courseNameEdit);
        EditText courseCodeEdit = view.findViewById(R.id.courseCodeEdit);
        Button cancel = view.findViewById(R.id.cancelBtn);
        Button addBtn = view.findViewById(R.id.addBtn);

        cancel.setOnClickListener(v -> dismiss());
        addBtn.setOnClickListener(v -> {
            String courseName = courseNameEdit.getText().toString();
            String courseCode = courseCodeEdit.getText().toString();
            listener.onclick(courseName,courseCode);
            dismiss();
        });
        return builder.create();
    }
}
