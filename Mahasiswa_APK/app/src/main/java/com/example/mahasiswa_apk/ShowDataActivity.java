package com.example.mahasiswa_apk;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class ShowDataActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<Student> studentList;
    private StudentAdapter studentAdapter;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_data);

        recyclerView = findViewById(R.id.recyclerViewShowData);
        studentList = new ArrayList<>();
        databaseHelper = new DatabaseHelper(this);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        studentAdapter = new StudentAdapter(studentList, new StudentAdapter.OnDeleteClickListener() {
            @Override
            public void onDeleteClick(int position) {
                deleteStudent(position);
            }
        });
        recyclerView.setAdapter(studentAdapter);

        loadStudents();
    }

    private void loadStudents() {
        studentList.clear();
        Cursor cursor = databaseHelper.getAllStudents();
        if (cursor.getCount() == 0) {
            Toast.makeText(this, "Tidak ada data ditemukan", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()) {
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                String nim = cursor.getString(2);
                double ipk = cursor.getDouble(3);
                String subject = cursor.getString(4);
                studentList.add(new Student(id, name, nim, ipk, subject));
            }
        }
        cursor.close();
        studentAdapter.notifyDataSetChanged();
    }

    private void deleteStudent(int position) {
        Student student = studentList.get(position);
        databaseHelper.deleteStudent(student.getId());
        studentList.remove(position);
        studentAdapter.notifyItemRemoved(position);
        Toast.makeText(this, "Data berhasil dihapus", Toast.LENGTH_SHORT).show();
    }
}

