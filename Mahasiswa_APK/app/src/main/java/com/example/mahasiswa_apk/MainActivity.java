package com.example.mahasiswa_apk;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mahasiswa_apk.Student;
import com.example.mahasiswa_apk.StudentAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText editTextName, editTextNIM, editTextIPK, editTextSubject;
    private Button buttonAdd, buttonViewAll;
    private RecyclerView recyclerView;
    private DatabaseHelper databaseHelper;
    private ArrayList<Student> studentList;
    private StudentAdapter studentAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextName = findViewById(R.id.editTextName);
        editTextNIM = findViewById(R.id.editTextNIM);
        editTextIPK = findViewById(R.id.editTextIPK);
        editTextSubject = findViewById(R.id.editTextSubject);
        buttonAdd = findViewById(R.id.buttonAdd);
        buttonViewAll = findViewById(R.id.buttonViewAll);
        recyclerView = findViewById(R.id.recyclerView);

        databaseHelper = new DatabaseHelper(this);
        studentList = new ArrayList<>();

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        studentAdapter = new StudentAdapter(studentList, new StudentAdapter.OnDeleteClickListener() {
            @Override
            public void onDeleteClick(int position) {
                deleteStudent(position);
            }
        });
        recyclerView.setAdapter(studentAdapter);

        // Disable the "Lihat Semua" button in the input menu
        buttonViewAll.setEnabled(false);
        buttonViewAll.setVisibility(View.GONE); // Optionally hide the button

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addStudent();
            }
        });
    }

    private void addStudent() {
        String name = editTextName.getText().toString();
        String nim = editTextNIM.getText().toString();
        double ipk = Double.parseDouble(editTextIPK.getText().toString());
        String subject = editTextSubject.getText().toString();

        boolean isInserted = databaseHelper.addStudent(name, nim, ipk, subject);
        if (isInserted) {
            Toast.makeText(this, "Data berhasil ditambahkan", Toast.LENGTH_SHORT).show();
            clearFields(); // Optionally clear input fields after successful addition
        } else {
            Toast.makeText(this, "Data gagal ditambahkan", Toast.LENGTH_SHORT).show();
        }
    }

    private void deleteStudent(int position) {
        Student student = studentList.get(position);
        databaseHelper.deleteStudent(student.getId());
        studentList.remove(position);
        studentAdapter.notifyItemRemoved(position);
        Toast.makeText(this, "Data berhasil dihapus", Toast.LENGTH_SHORT).show();
    }

    private void clearFields() {
        editTextName.setText("");
        editTextNIM.setText("");
        editTextIPK.setText("");
        editTextSubject.setText("");
    }
}
