package com.example.kapil.explicitintents2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    // This application will give insights to explicit intents and how implicit intents differ from them
    // Here both implicit intents and explicit intents are shown
    Button btnAdd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnAdd = findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // So this is the code for explicit intent that will start new layout on button click i.e ActivitySecond

                // Run One type of intent at a time either implicit or explicit to learn how they work

                // Now we want to extend this application further and now we are passing the data to intent so that the message can be sent to another activity
                // We are using Serializable to pass object with intent so we have to make a custom class that implements serializable

               // Now We want to move forward and use parcelable in this application so why we are using parcelable
                // We are using parcelable because it is faster than serializable
                // So now we have to implement and override methods of parcelable instead of serializable in Person.java class and do minor changes in

                Intent ExplicitIntent = new Intent(MainActivity.this, ActivitySecond.class);
                // now we have to putExtra to send message to other component through intent
                ExplicitIntent.putExtra("Key","This message is sent from mainactivity");

                // now we have to make the object for person class which is implementing serializable
                Person person = new Person();
                person.setFirstName("kapil");
                person.setLastName("Chaudhary");
                person.setQualification("BTech Undergrad");

                ExplicitIntent.putExtra("description",person); // It is passing object of Person class to intent

              startActivity(ExplicitIntent);

                // now code for implicit intents
                // Implicit intents are those when we trigger the button it broadcasts message to every other application in android device to receive that intent and start that
                // Implicit intents users intent filter which are defined in manifest.xml file
                // Intent filter have three parts 1. Data 2. category 3. Action
                // It works like matching is done data is matched with data value set in activity with intent filter in manifest file same for category and action
                // and if the matching is successful then that particular app/activity/framework will open
                // We have to comment implicit intent to use data through intent

//                Intent ImplicitIntent = new Intent();
//                ImplicitIntent.setAction("com.example.ImplicitIntent");
//                ImplicitIntent.addCategory(Intent.CATEGORY_DEFAULT);
//                startActivity(ImplicitIntent);

            }

        });
    }
}
