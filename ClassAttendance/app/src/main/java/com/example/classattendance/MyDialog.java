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
    public static final String STUDENT_ADD_DIALOG="addStudent";

    private onClickListener listener;

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
        return dialog;
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
