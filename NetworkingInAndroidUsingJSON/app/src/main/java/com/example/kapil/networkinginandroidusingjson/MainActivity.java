package com.example.kapil.networkinginandroidusingjson;

import android.os.AsyncTask;
import android.renderscript.ScriptGroup;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;
// Networking in Android uses streams to read and write data into the program from external source
// So Here We are getting the data from the internet in String format by accessing the URL
// We are making the network call here
// Network call must not be made on mainUI Thread because data from the internet comes in chunks
// So the Network call must be made on another Thread using AsyncTask in doInBackground method

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updatetextView();
            }
        });

    }
    private void updatetextView(){

      //  TextView textView = findViewById(R.id.textView);
// creating an object for network task
        NetworkTask networkTask = new NetworkTask();
        networkTask.execute("https://www.google.com");

    }

class  NetworkTask extends AsyncTask<String,Void,String>{


    @Override
    protected String doInBackground(String... strings) {
        String stringUrl = strings[0];
        // you can not make internet call through string object You must have an URL object to make internet or API call
// you have to handle URL in try catch bcz there may be some problem with string url
        try {
            URL url = new URL(stringUrl);
            // make a connection with this URL by doing openConnection
            url.openConnection();
            // store the instance of URl connection
            // reason is we want to get data
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

             InputStream inputStream = httpURLConnection.getInputStream();

             // to get data in chunks we use Scanner

            Scanner scanner = new Scanner(inputStream);
            // we use Delimiter bcz it will allow us to read entire inputStream in one go
            scanner.useDelimiter("\\A");

            if(scanner.hasNext()){

                String s = scanner.next();

                // this return statement passed to onPostExecute method
                return s;
            }




        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
// this return will be called when try catch failed to execute

        return "failed to load";
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        TextView textView = findViewById(R.id.textView);
        textView.setText(s);
    }
}










}






