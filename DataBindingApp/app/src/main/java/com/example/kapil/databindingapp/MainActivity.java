package com.example.kapil.databindingapp;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      //  setContentView(R.layout.activity_main);

        //Inflate layout
         ViewDataBinding binding = DataBindingUtil.setContentView(this,R.layout.activity_main);

         //GetData
        DataSource dataSource = DataSource.get("World");

        //Bind the data to the view
        binding.setDataSource(dataSource);


    }
}
