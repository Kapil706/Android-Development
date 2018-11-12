package com.example.kapil.contentprovider;

import android.Manifest;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

// So content providers are useful when a third party app wants to access the data from the system app installed on the android device
// So content resolver send the data request to the app and then app responds to it using content provider
// Now content provider documentation is found at android.provider
// It contains URI , query command for the table or data to fetch that data
// It has Column selection, selection clause, selection arguments and order by
// It contains cursor which parses through the data and sends it to the app that requested that data
// One more important thing here are only four field to display but if there are more than we have to go for UI threading and have to work on new thread
//
public class MainActivity extends AppCompatActivity {
    public static final String TAG = "ContentProvider";
    private int CONTACT=767;
    private TextView textViewQueryResult;

    private String[] mColumnProjection = new String[]{ContactsContract.Contacts.DISPLAY_NAME_PRIMARY,
    ContactsContract.Contacts.CONTACT_STATUS,
            ContactsContract.Contacts.HAS_PHONE_NUMBER};
    private String mSelectionCluse = ContactsContract.Contacts.DISPLAY_NAME_PRIMARY+"Kapil";

    private String [] mSelectionArguments = new String []{"Kapil"};

    private String mOrderBy = ContactsContract.Contacts.DISPLAY_NAME_PRIMARY;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textViewQueryResult = (TextView) findViewById(R.id.textViewQueryResult);
// Now I remember here about that google has introduced runtime permission above api level 23 for security purposes
        // Here I am checking if I have the permission to access the contacts
        // If Do not have permission granted then first describe why i need permission to user
        // Then I am requesting permission when user allow to give access to contacts
        // If I already have permission then I am fetching the contacts in the mobile


        if(ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS)
                !=PackageManager.PERMISSION_GRANTED)
        {Log.i("1", "Permission is not granted");
            if(ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.READ_CONTACTS)){

                Log.i("REQUEST", "Requesting permission....");
                ActivityCompat.requestPermissions(MainActivity.this,
                        new String[]{Manifest.permission.READ_CONTACTS},
                        CONTACT);


//                new AlertDialog.Builder(this)
//                        .setMessage("We need Contact Permission to proceed")
//                        .setPositiveButton("Okay", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//                                ActivityCompat.requestPermissions(MainActivity.this,new String[]
//                                        {Manifest.permission.READ_CONTACTS},CONTACT);
//                            }
//                        })
//                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//                                dialog.dismiss();
//                            }
//                        }).create().show();
            }




        }else{
            fetchContacts();
        }
        }
// here is the code after the permission is granted to the third party application
    // this code will fetch contacts if permission is granted
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode==CONTACT){

            if(permissions.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED ){
                fetchContacts();
            }
        }else {
            Toast.makeText(this, "Permission denied please close the app", Toast.LENGTH_SHORT).show();
        }
    }

    public void fetchContacts(){

        ContentResolver contentResolver = getContentResolver();
        Cursor cursor = contentResolver.query(ContactsContract.Contacts.CONTENT_URI,
                mColumnProjection,
                null,
                null,
                null);

        if(cursor!=null && cursor.getCount()>0){
            StringBuilder stringBuilder= new StringBuilder();

            while(cursor.moveToNext()){

                stringBuilder.append(cursor.getString(0)+","+cursor.getString(1)+","+cursor.getString(2)+"\n");


            }
            textViewQueryResult.setText(stringBuilder.toString());

        }else{
            textViewQueryResult.setText("No Contacts in device");
        }


    }


}
