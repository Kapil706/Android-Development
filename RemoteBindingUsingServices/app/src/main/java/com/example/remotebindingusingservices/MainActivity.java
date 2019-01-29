package com.example.remotebindingusingservices;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;






// Here you will learn about how one app can access data from another app using remote binding services
// this is service side application
public class MainActivity extends AppCompatActivity implements View.OnClickListener {




        private static final String TAG = MainActivity.class.getSimpleName();

        private Button buttonStartService, buttonStopService;
        private Context mContext;

        private Intent serviceIntent;

        @Override
        protected void onCreate (@Nullable Bundle savedInstanceState){
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            mContext = getApplicationContext();
            buttonStartService = (Button) findViewById(R.id.buttonStartService);
            buttonStopService = (Button) findViewById(R.id.buttonStopService);

            buttonStopService.setOnClickListener(this);
            buttonStartService.setOnClickListener(this);
            serviceIntent = new Intent(getApplicationContext(), MyService.class);
        }


        @Override
        public void onClick (View v){
            switch (v.getId()) {
                case R.id.buttonStartService:
                    startService(serviceIntent);
                    Toast.makeText(mContext, "Service Started", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.buttonStopService:
                    stopService(serviceIntent);
                    break;
                default:
                    break;
            }

        }
    }




