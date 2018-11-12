package com.example.kapil.contentproviderswithloadersandcursorloader;

import android.Manifest;
import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


//If the data that needs to be loaded from the content provides is huge.
//        it makes sense to use a Thread or a AsyncTask to do this.
//        Android realised this and Loaders were introduced in Android from API 11 onwards (Honeycomb).
public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor>, View.OnClickListener{

public static final String TAG = "ContentProviderWithLoaders";

private int CONTACT = 34;
private boolean firstTimeLoaded = false;
private TextView textViewQueryResult;
private Button button;

private String[] mcolumnProjection = new String[]{
        ContactsContract.Contacts.DISPLAY_NAME_PRIMARY,
        ContactsContract.Contacts.CONTACT_STATUS,
        ContactsContract.Contacts.HAS_PHONE_NUMBER
};

private String mSelectionCluse = ContactsContract.Contacts.DISPLAY_NAME_PRIMARY+"= ?";

private String[] mSelectionArguments = new String []{"Ajay"};

private String mOrderBy = ContactsContract.Contacts.DISPLAY_NAME_PRIMARY;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewQueryResult = findViewById(R.id.textViewQueryResult);

        button = findViewById(R.id.buttonLoadData);
        button.setOnClickListener(this);


        ContentResolver contentResolver = getContentResolver();



    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode==CONTACT){

            if(permissions.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED ){
                //add code for inflation of contacts
            }
        }else {
            Toast.makeText(this, "Permission denied please close the app", Toast.LENGTH_SHORT).show();
        }
    }

    @NonNull
            @Override
            public Loader<Cursor> onCreateLoader ( int id, @Nullable Bundle args){

                if (id == 1) {

                    return new CursorLoader(MainActivity.this, ContactsContract.Contacts.CONTENT_URI,
                            mcolumnProjection, null, null, null);
                }


                return null;
            }

            @Override
            public void onLoadFinished (@NonNull Loader < Cursor > loader, Cursor cursor){
                if (cursor != null && cursor.getCount() > 0) {
                    StringBuilder stringBuilderQueryResult = new StringBuilder("");
                    //cursor.moveToFirst();
                    while (cursor.moveToNext()) {
                        stringBuilderQueryResult.append(cursor.getString(0) + " , " + cursor.getString(1) + " , " + cursor.getString(2) + "\n");
                    }
                    textViewQueryResult.setText(stringBuilderQueryResult.toString());
                } else {
                    textViewQueryResult.setText("No Contacts in device");
                }
                cursor.close();

            }

    @Override
    public void onLoaderReset(@NonNull Loader<Cursor> loader) {

    }


    @Override
    public void onClick (View v){

        switch (v.getId()) {
            case R.id.buttonLoadData: loadContacts();
//                if (firstTimeLoaded == false) {
//                    getSupportLoaderManager().initLoader(1, null, this);
//                    firstTimeLoaded = true;
//                } else {
//                    getSupportLoaderManager().restartLoader(1, null, this);
//                }

                break;
            default:
                break;
        }
    }

    private void loadContacts() {

        if(ActivityCompat.checkSelfPermission(this,Manifest.permission.READ_CONTACTS)==PackageManager.PERMISSION_GRANTED){
            Log.i("TAG","Permisssion is granted");
            if (firstTimeLoaded == false) {
                getSupportLoaderManager().initLoader(1, null, this);
                firstTimeLoaded = true;
            } else {
                getSupportLoaderManager().restartLoader(1, null, this);
            }
        }else{
            Log.i("TAG","Permisssion is not granted");
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.READ_CONTACTS)) {
                Log.i("TAG", "Permisssion is not granted, hence showing rationale");

                ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.READ_CONTACTS}, CONTACT);
                 }

        else{
                Log.i("TAG","Permisssion being requested for first time");
                ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_CONTACTS},CONTACT);
            }
        }


    }}





