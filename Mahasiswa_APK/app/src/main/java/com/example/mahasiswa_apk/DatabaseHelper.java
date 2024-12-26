package com.example.mahasiswa_apk;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "students.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_STUDENTS = "students";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_NIM = "nim";
    private static final String COLUMN_IPK = "ipk";
    private static final String COLUMN_SUBJECT = "subject";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_STUDENTS + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME + " TEXT, " +
                COLUMN_NIM + " TEXT, " +
                COLUMN_IPK + " REAL, " +
                COLUMN_SUBJECT + " TEXT)";
        db.execSQL(createTable);

        // Insert initial data
        insertInitialData(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_STUDENTS);
        onCreate(db);
    }

    private void insertInitialData(SQLiteDatabase db) {
        if (getStudentCount(db) == 0) {
            addStudent(db, "Joshua", "11322456", 3.6, "Kalkulus 1");
            addStudent(db, "Yosephine", "11322557", 2.9, "Dasar Pemrograman 1");
            addStudent(db, "Geneva", "11321356", 3.1, "Kalkulus 1");
            addStudent(db, "Gerald", "11322456", 3.4, "Analisa Algoritma");
            addStudent(db, "Willy", "11321667", 3.5, "Analisa Algoritma");
        }
    }

    private int getStudentCount(SQLiteDatabase db) {
        Cursor cursor = db.rawQuery("SELECT COUNT(*) FROM " + TABLE_STUDENTS, null);
        int count = 0;
        if (cursor.moveToFirst()) {
            count = cursor.getInt(0);
        }
        cursor.close();
        return count;
    }

    private void addStudent(SQLiteDatabase db, String name, String nim, double ipk, String subject) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_NAME, name);
        contentValues.put(COLUMN_NIM, nim);
        contentValues.put(COLUMN_IPK, ipk);
        contentValues.put(COLUMN_SUBJECT, subject);
        db.insert(TABLE_STUDENTS, null, contentValues);
    }

    public boolean addStudent(String name, String nim, double ipk, String subject) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_NAME, name);
        contentValues.put(COLUMN_NIM, nim);
        contentValues.put(COLUMN_IPK, ipk);
        contentValues.put(COLUMN_SUBJECT, subject);

        long result = db.insert(TABLE_STUDENTS, null, contentValues);
        return result != -1;
    }

    public Cursor getAllStudents() {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_STUDENTS, null);
    }

    public void deleteStudent(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_STUDENTS, COLUMN_ID + "=?", new String[]{String.valueOf(id)});
    }
}
