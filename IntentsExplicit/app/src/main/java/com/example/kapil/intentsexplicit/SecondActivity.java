package com.example.kapil.intentsexplicit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {
     TextView tvResult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        tvResult=findViewById(R.id.tvResult);
  // getIntent() is the function to take the value from the intent that started in first activity
        Intent intentThatstartedthis = getIntent();

        //getIntExtra () is function that takes key value pairs from and returns it to result
        int result= intentThatstartedthis.getIntExtra("result",0);
        tvResult.setText(String.valueOf(result));
    }
}
