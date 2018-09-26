package com.example.kapil.normalpermissions;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Button btnStatusbar;
    TextView tvStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnStatusbar=findViewById(R.id.btnStatusbar);
        tvStatus=findViewById(R.id.tvStatus);

        btnStatusbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // It is the Connectivity manager that shows about the current connectivity to internet service or not
                // Here getSystemService access the system services and returns object and hence it must be type casted
                // into Connectivity Manager Type
                ConnectivityManager cm = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);

                // NetworkInfo access the current active network information
                NetworkInfo netinfo =  cm.getActiveNetworkInfo();

                boolean isConnected = netinfo !=null &&netinfo.isConnected();

                tvStatus.setText(isConnected? "Connected" : "Disconnected");
            }
        });


    }
}
