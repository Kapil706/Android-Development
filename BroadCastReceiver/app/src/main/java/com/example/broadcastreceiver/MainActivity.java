package com.example.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/*
*In this We will learn about the BroadCast Receiver , it means listening of the
* intents like messaging, notifications, calls etc
* In this we will first make MyBroadCast Receiver class and then extends it from
* BroadCastReceiver class
* then implement onReceive function that will listen the broadcast
* then we will register the receiver in our manifest file
* */

// But there is biggest problem with this type of registration of broadcast receiver because it
// it is receiving the messages after the application is paused or stopped and it causes a security breach
// because we can access OTP without the application running in foreground

// so to resolve this error we have to register the broadcast receiver dynamically

/*  For the dyanmic registration of broadcast receiver we have to remove code from manifest file
 * and then create instance of broadcast receiver and register it in onstart() and unregister it in onstop() */


// Till now the application consists of the system generated intents or broadcasts but if we want to trigger the
// broadcast through our own generated broadcast we have to make a localBR sender then through send message Broadcast must be triggered
// we can also trigger the broadcast by creating a button instead


// Now the new problem arises is that any other localbroadcast application can be able to trigger our broadcastreceiver if it contains the same properties like of intent filter
// so to resolve this we have to exported=false in android manifest in receiver area

// other solution is we can make our own application and wrap it under local broadcast manager then the broadcast manager will not get triggered if any another wants to trigger it.


// Now in oreo and post oreo it is compulsory that you must register your receiver in onstart() whether you register it in manifest or not



public class MainActivity extends AppCompatActivity {
     private BroadcastReceiver broadcastReceiver;
     private Button BroadcastIntent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BroadcastIntent = findViewById(R.id.buttonBroadcastIntent);
        // instantiation of MyBroadcastReceiver
        broadcastReceiver = new MyBroadCastReceiver();
        BroadcastIntent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent();
                i.setAction("my.custom.action.tag");
                i.addCategory(Intent.CATEGORY_DEFAULT);
                sendBroadcast(i);
            }
        });
    }


    @Override
    protected void onStart() {
        super.onStart();

        // intent filter to register and listen the broadcast receiver
//        IntentFilter i = new IntentFilter("android.provider.Telephony.SMS_RECEIVED");
//        registerReceiver(broadcastReceiver,i);

        IntentFilter i = new IntentFilter("my.custom.action.tag");
        i.addCategory(Intent.CATEGORY_DEFAULT);
        registerReceiver(broadcastReceiver,i);

    }

    @Override
    protected void onStop() {
        super.onStop();
       // unregisterReceiver(broadcastReceiver);
    }
}
