package com.example.kapil.listviewcustomadapter;

import java.util.ArrayList;

public class Teacher {
 String names;
 String course;

    public Teacher(String names, String course) {
        this.names = names;
        this.course = course;
    }

    public String getNames() {
        return names;
    }

    public String getCourse() {
        return course;
    }
// ArrayList is the dynamic array which can take data without bound
    public static ArrayList<Teacher> getRandomTeacher(){
        ArrayList<Teacher> teachers = new ArrayList<>();
        teachers.add(new Teacher("Arnav Gutpa","Android"));
        teachers.add(new Teacher("Arnav Gutpa","Android"));
        teachers.add(new Teacher("Arnav Gutpa","Android"));
        teachers.add(new Teacher("Arnav Gutpa","Android"));
        teachers.add(new Teacher("Arnav Gutpa","Android"));
        teachers.add(new Teacher("Arnav Gutpa","Android"));
        teachers.add(new Teacher("Arnav Gutpa","Android"));
        teachers.add(new Teacher("Arnav Gutpa","Android"));
        teachers.add(new Teacher("Arnav Gutpa","Android"));
        teachers.add(new Teacher("Arnav Gutpa","Android"));
        return teachers;

    }
}
