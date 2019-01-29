package com.example.servicesinandroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


// Here in this we will learn about services in android
// services in android are used to running long task in the background
// services in android do not have UI
// services are not like threads

// so to use service we have to make a custom class MyService and it will extend service class and it's methods
// after that we have to send an intent explicit from mainactivity to myservice when button is clicked

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button start,stop;
    protected TextView textView;
    private boolean mstoploop;
    int count =0;
    private Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        start= findViewById(R.id.StartThread);
        stop = findViewById(R.id.StopThread);
        textView=findViewById(R.id.threadCount);

        start.setOnClickListener(this);
        stop.setOnClickListener(this);

        intent= new Intent(getApplicationContext(),MyService.class);
        // sending intent and now you have to also declare service class in manifest.xml

    }

    @Override
    public void onClick(View v) {

        switch(v.getId()){

            case R.id.StartThread:
                mstoploop=true;
             // for starting the service we will use StartService function


                startService(intent);
                break;

            case R.id.StopThread:
                //It is the method to stop service
                stopService(intent);
             break;

        }

    }
}
