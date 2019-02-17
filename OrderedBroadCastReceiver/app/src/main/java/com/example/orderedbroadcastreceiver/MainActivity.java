package com.example.orderedbroadcastreceiver;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG=MainActivity.class.getSimpleName();

    private Button buttonStartBroadcastRecever;
    private static final String BREAD_CRUMB = "Breadcrumb";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonStartBroadcastRecever=(Button)findViewById(R.id.buttonStartBroadCast);
        buttonStartBroadcastRecever.setOnClickListener(this);


    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_activity_main, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//        return super.onOptionsItemSelected(item);
    //}

    @Override
    public void onClick(View view) {
        Intent intent=new Intent();
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        intent.setAction("com.br1");

        sendOrderedBroadcast(intent, null, new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                Bundle bundle=getResultExtras(true);
                String breadcrumb=bundle.getString(BREAD_CRUMB);
                breadcrumb=breadcrumb+"->"+TAG;
                Log.i(TAG,"On Receive: "+breadcrumb);
            }
        }, null, MainActivity.RESULT_OK,null,null);


    }
}
