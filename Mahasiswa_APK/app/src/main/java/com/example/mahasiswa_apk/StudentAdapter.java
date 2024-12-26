package com.example.mahasiswa_apk;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.StudentViewHolder> {

    private ArrayList<com.example.mahasiswa_apk.Student> studentList;
    private OnDeleteClickListener onDeleteClickListener;

    public interface OnDeleteClickListener {
        void onDeleteClick(int position);
    }

    public StudentAdapter(ArrayList<com.example.mahasiswa_apk.Student> studentList, OnDeleteClickListener onDeleteClickListener) {
        this.studentList = studentList;
        this.onDeleteClickListener = onDeleteClickListener;
    }

    @NonNull
    @Override
    public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.student_item, parent, false);
        return new StudentViewHolder(view, onDeleteClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentViewHolder holder, int position) {
        com.example.mahasiswa_apk.Student currentStudent = studentList.get(position);
        holder.textViewName.setText(currentStudent.getName());
        holder.textViewNIM.setText(currentStudent.getNim());
        holder.textViewIPK.setText(String.valueOf(currentStudent.getIpk()));
        holder.textViewSubject.setText(currentStudent.getSubject());
    }

    @Override
    public int getItemCount() {
        return studentList.size();
    }

    public static class StudentViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewName, textViewNIM, textViewIPK, textViewSubject;
        public Button buttonDelete;

        public StudentViewHolder(@NonNull View itemView, final OnDeleteClickListener onDeleteClickListener) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.textViewName);
            textViewNIM = itemView.findViewById(R.id.textViewNIM);
            textViewIPK = itemView.findViewById(R.id.textViewIPK);
            textViewSubject = itemView.findViewById(R.id.textViewSubject);
            buttonDelete = itemView.findViewById(R.id.buttonDelete);

            buttonDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onDeleteClickListener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            onDeleteClickListener.onDeleteClick(position);
                        }
                    }
                }
            });
        }
    }
}
