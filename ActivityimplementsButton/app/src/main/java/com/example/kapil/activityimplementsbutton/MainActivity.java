package com.example.kapil.activityimplementsbutton;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
   public static final String TAG="btn";

   // btun is object
    Button btun;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btun=findViewById(R.id.btun3);
// Here this is in refrence  to MainActivity object so this refers to MainActivity and MainActivity implements an interface i.e View.onClickListener
        btun.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Log.d(TAG,"button clicked");
        
    }
}
