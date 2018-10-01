package com.example.kapil.listviewcustomadapter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
     ArrayList<Teacher> teachers = Teacher.getRandomTeacher(); // It is accessing the Teacher names dynamically from teacher class and set it into teachers arraylist

     ListView lvTeachers;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lvTeachers=findViewById(R.id.lvTeachers);
        TeacherAdapter teacherAdapter = new TeacherAdapter();
        lvTeachers.setAdapter(teacherAdapter);

    }

    class TeacherAdapter extends BaseAdapter{   // It is construction of custom adapter which extends from base adapter and base adapter is declared abstract
        @Override
        public int getCount() {
            return teachers.size();  // get the total count of objects to be inflated in layout
        }

        @Override
        public Teacher getItem(int position) {
            return teachers.get(position); // get the position of each and every object to be inflated
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {    // this most important part  it contains get view it means here conversion takes place from xml to java

            View ItemView = getLayoutInflater().inflate(   // getLayoutInflater takes out the inflater and .inflate inflates the view in the layout
                    R.layout.list_item_teacher,    // the layout which wants to be inflated
                    parent,  // the layout that is inflated inside parent layout
                    false

            );

            TextView tvName = ItemView.findViewById(R.id.tvName);   // ItemView is necessary to find tvName because tvName is inside the ItemView
            TextView tvCourse = ItemView.findViewById(R.id.tvCourse);

            tvName.setText(getItem(position).getNames());    // for setting up the text in layout
            tvCourse.setText(getItem(position).getCourse());


            return ItemView;
        }
    }
}
