package com.example.kapil.intentsexplicit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView tvResult;
    EditText etVar1,etVar2;
    Button btnAdd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvResult=findViewById(R.id.tvResult);
        etVar1=findViewById(R.id.etVar1);
        etVar2=findViewById(R.id.etVar2);
        btnAdd=findViewById(R.id.btnAdd);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Here Integer.valueOf wraps the string value entered by user and convert it into integer value
                int var1=Integer.valueOf(etVar1.getText().toString());
                int var2=Integer.valueOf(etVar2.getText().toString());
                int var3=var1+var2;
                // Here String.valueOf takes the integer value and returns a string value because setText takes only string values in return
                tvResult.setText(String.valueOf(var3));


                // Intent is the class defined in java and these are used to navigate from one activity to another activity
                // i is object and Intent( ) is constructor having two arguments one is present instance of current activity and another is where you want to
                //navigate from this current activity.
                Intent i= new Intent(MainActivity.this,SecondActivity.class);

                //putExtra() is Hashmap that contains KeyValue pairs for i.e one is string only and another can be any pre-defined data type

                i.putExtra("result",var3);

                // startActivity () is function that starts another activity
                startActivity(i);


            }
        });
    }
}
