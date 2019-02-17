package com.example.localbroadcastsender;

import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
// make use of localbroadcast manager so that this applcation do not trigger the brodcastreceiver of another applications
// This application is connected with Broadcast Receiver application
public class MainActivity extends AppCompatActivity {
     private Button sender;
     LocalBroadcastManager localBroadcastManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sender =findViewById(R.id.sender);
        // localBroadcastManager getting the application context
        localBroadcastManager = LocalBroadcastManager.getInstance(getApplicationContext());

        sender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent();
                i.setAction("my.custom.action.tag");
                i.addCategory(Intent.CATEGORY_DEFAULT);
                localBroadcastManager.sendBroadcast(i);
            }
        });

    }
}
