package com.example.kapil.dangerouspermissions;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText dial;
    Button dialer;
    void call(){

        String Phone = dial.getText().toString();
        Uri uri = Uri.parse("tel:" + Phone);
        Intent i = new Intent(Intent.ACTION_CALL,uri);
        startActivity(i);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dial =findViewById(R.id.dial);
        dialer=findViewById(R.id.dialer);

        dialer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Now In this We check if we have the permission to make call

                // ContextCompat is used because we are targeting API level 19

                // Permission Call_Phone directly make phone call it will not show dialer to dial number
                int perm = ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CALL_PHONE);

                // PackageManager is checking is permission is granted
                if (perm == PackageManager.PERMISSION_GRANTED){
                    call();
                }

                // if permission is not granted then in this user will grant permission
                // request code is any integer you want to choose
                else {
                    ActivityCompat.requestPermissions(MainActivity.this,new  String[] {
                            Manifest.permission.CALL_PHONE}, 121
                           );

                }

            }
        });


    }
}
