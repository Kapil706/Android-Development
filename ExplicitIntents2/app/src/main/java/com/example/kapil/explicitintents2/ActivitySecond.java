package com.example.kapil.explicitintents2;

import android.os.Bundle;
import android.widget.TextView;

public class ActivitySecond extends MainActivity {
    TextView textViewMessage;
    TextView textViewPerson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_activity_second);
        textViewMessage =findViewById(R.id.textViewMessage);
        textViewPerson =findViewById(R.id.textViewPerson);

        String message = getIntent().getStringExtra("key");
        textViewMessage.setText(message);

       // Person  person = (Person) getIntent().getSerializableExtra("description");
        Person  person = (Person) getIntent().getParcelableExtra("description");
        textViewPerson.setText("First Name "+
                person.getFirstName()+  "Last Name "
                +  person.getLastName()+  " Qualification"
                +  person.getQualification());

    }
}
