package com.example.kapil.fragmentslifecycle;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.widget.Button;
public class MainActivity extends AppCompatActivity {
    private static final String COMMON_TAG = "CombinedLifeCycle";
    private static final String ACTIVITY_NAME = MainActivity.class.getSimpleName();
    private static final String TAG = COMMON_TAG;

     private Button btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnAdd = findViewById(R.id.btnAdd);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
         AddFragment();
            }
        });

        Log.i(TAG,ACTIVITY_NAME+"OnCreate");
    }



    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, ACTIVITY_NAME+" onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, ACTIVITY_NAME+" onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, ACTIVITY_NAME+" onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, ACTIVITY_NAME+" onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, ACTIVITY_NAME+" onDestroy");
    }


    private void AddFragment(){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Fragment fragment = new FragmentLifeCycle();
        fragmentTransaction.add(R.id.fragmentContainer,fragment,"FragmentLifecCycle");
     //   fragmentTransaction.addToBackStack("FragmentStack1");
        fragmentTransaction.commit();

    }
}
