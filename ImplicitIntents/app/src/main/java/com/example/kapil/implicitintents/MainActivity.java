package com.example.kapil.implicitintents;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView weburl;
    ImageButton imgBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        weburl=findViewById(R.id.weburl);
        imgBtn=findViewById(R.id.imgBtn);

        imgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String Url= weburl.getText().toString();
  // Uri is uniform resource identifier and it used for implicit intents
                //Uri is abstract class that extends objects and implements parcelable
                // It takes input in form of string and parse it into Uri format and returns it to the object
                Uri uri= Uri.parse(Url);
// Here Intent.Action_View is the action performed by the intent and it is used for implicit intents
                Intent i= new Intent(Intent.ACTION_VIEW,uri);
                try{
                    startActivity(i);
                }
                catch (ActivityNotFoundException  e){

                    Log.e("TAG", "Could not open activity with given Url",e );

                    Toast.makeText(MainActivity.this, "Could Not find any application to open this app", Toast.LENGTH_SHORT).show();
                }

            }
        });




    }
}
