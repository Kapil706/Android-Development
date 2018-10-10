package com.example.kapil.retainingstate;

import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
   public static final String TAG = MainActivity.class.getSimpleName();
    // so this application basically shows the retaining state of android application when orientation changes
    // Here I have to use to functions one is onSaveInstanceState which will be invoked just before onstop of your application to save the state when orientation changes form portrait to landscape
// Next I have to use onRestoreInstance state so that when orientation changes it will be invoked just after onStart and before onResume it means now new activity is not creating again and again
    EditText tvView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvView = (EditText) findViewById(R.id.tvView);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.i(TAG,"inInstanceState");
        // this method contains putString that takes keyValue and map that with the text we enter and save it
        outState.putString("TextEntered",tvView.getText().toString());

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.i(TAG,"inRestoreInstanceState");
        // here we have setText through the EditText View through the savedInstanceState
        tvView.setText(savedInstanceState.getString("TextEntered"));

    }

    // now one more important thing if I comment these two functions then app will work fine because we have given id for particular editText view so it will retain the state
}
