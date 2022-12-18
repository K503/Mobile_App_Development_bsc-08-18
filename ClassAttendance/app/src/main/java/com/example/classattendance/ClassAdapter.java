package com.example.classattendance;

import android.content.Context;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ClassAdapter extends RecyclerView.Adapter<ClassAdapter.ClassViewHolder> {
    ArrayList<MyClasses> myClasses;
    Context context;

    private onItemClickListener onItemClickListener;

    public interface onItemClickListener{
        void onClick(int position);
    }

    public void setOnItemClickListener(ClassAdapter.onItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public ClassAdapter(Context context, ArrayList<MyClasses> myClasses) {
        this.myClasses = myClasses;
        this.context = context;
    }


    public static class ClassViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener{
        TextView courseName;
        TextView courseCode;
        public ClassViewHolder(@NonNull View itemView, onItemClickListener onItemClickListener) {
            super(itemView);
            courseName = itemView.findViewById(R.id.course_textview);
            courseCode = itemView.findViewById(R.id.code_textview);
            itemView.setOnClickListener(view -> onItemClickListener.onClick(getAdapterPosition()));
            itemView.setOnCreateContextMenuListener(this);
        }

        @Override
        public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
            contextMenu.add(getAdapterPosition(),0,0,"EDIT");
            contextMenu.add(getAdapterPosition(),1,0,"DELETE");
        }
    }

    @NonNull
    @Override
    public ClassViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemview = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_classes,parent,false);
        return new ClassViewHolder(itemview, onItemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ClassViewHolder holder, int position) {
        holder.courseName.setText(myClasses.get(position).getCourseName());
        holder.courseCode.setText(myClasses.get(position).getCourseCode());

    }

    @Override
    public int getItemCount() {
        return myClasses.size();
    }
}


