package com.example.mahasiswa_apk;

public class Student {
    private int id;
    private String name;
    private String nim;
    private double ipk;
    private String subject;

    public Student(int id, String name, String nim, double ipk, String subject) {
        this.id = id;
        this.name = name;
        this.nim = nim;
        this.ipk = ipk;
        this.subject = subject;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getNim() {
        return nim;
    }

    public double getIpk() {
        return ipk;
    }

    public String getSubject() {
        return subject;
    }
}
