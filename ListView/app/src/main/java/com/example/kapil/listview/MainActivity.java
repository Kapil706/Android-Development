package com.example.kapil.listview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    //ListViews are very basic ones that contains objects on the screen one after the another vertically
    // ListViews are AutoScrollable when it contains objects which are out of screen
    // ListViews are inflated using Adapters

    ListView lvNames;
    String names[] =new String[]{"kapil Chaudhary",
            "Puneet sharma", "shanky","Rahul Aggarwal", "Raman","yo yo honey singh","VOlume 1",
            "The great khali",
    "Lakshay", "Chutiyapa","Elon Musk","lassi","twitter ","linkedin","parth","Sanchit"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lvNames=findViewById(R.id.lvNames);
// Adapters are the one which puts your dataView to your ScreeView
        //Adapters are generic and take parameter of that type which is same as that the object we are inflating i.e String in this case
//ArrayAdapter is used for one item into one text view or one object into one inflated view
        ArrayAdapter<String> namesAdapter = new ArrayAdapter<>( this, // for current context that is MainActivity
                android.R.layout.simple_list_item_1, // This is for the type of view you want to show on the screen
                android.R.id.text1, // This is for connecting layout with text
                names  // This is for the names which are to be inflated

        );

        lvNames.setAdapter(namesAdapter);  // This is for connecting Adapter to Current layout
    }
}
