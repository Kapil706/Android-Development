package com.example.kapil.sharedprefrencesdatabase;

import android.content.Intent;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.kapil.sharedprefrencesdatabase.Settings.SettingsActivity;
import com.example.kapil.sharedprefrencesdatabase.Settings.UserSettingsChangeListener;


// In this application we are using sharedprefrences to store the data into file in xml format


public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private static final String TAG=MainActivity.class.getSimpleName();

    private static final String FILE_NAME="sample.txt";

    private Context mContext;

    private SharedPreferences sharedPreferences;

    private Button buttonWriteToFile,buttonReadFromFile;
    private TextView textViewContentFromFile;
    private EditText editTextUserMessage;
    private UserAction recentUserAction;
    enum UserAction{
        READ,WRITE
    }

   // listener object
    UserSettingsChangeListener listener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext=getApplicationContext();

        buttonReadFromFile=(Button)findViewById(R.id.buttonWriteToFile);
        buttonWriteToFile=(Button)findViewById(R.id.buttonReadFromFile);
        textViewContentFromFile=(TextView)findViewById(R.id.textViewContentFromFile);
        editTextUserMessage=(EditText)findViewById(R.id.editTextUserMessage);

        buttonReadFromFile.setOnClickListener(this);
        buttonWriteToFile.setOnClickListener(this);
// It access the the activity and mode in which file is to be work
        sharedPreferences = getSharedPreferences(getPackageName()+"."+TAG,MODE_PRIVATE);


    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.buttonWriteToFile: writeContentToFile(); break;
            case R.id.buttonReadFromFile: populateTheReadText();break;
            default: break;
        }

    }

    private void writeContentToFile(){

        recentUserAction=UserAction.WRITE;
        String string=editTextUserMessage.getText().toString();

        if(isStringEmpty(string)){

            // editor will help to write to file using sharedprefrences
            SharedPreferences.Editor editor=sharedPreferences.edit();

            // putstring is used to put the key and value of string into file
            editor.putString("SAMPLE_KEY",string);
            editor.commit();
    }


    }
        protected boolean isStringEmpty(String string){
            if(string!=null && !string.equals("") && string.length()>0){
                return true;
            }else {
                return false;
            }
        }


    @Override
    protected void onResume() {
        super.onResume();
        populateTextFromPreviousSession();
    }

    private  void  populateTheReadText(){

        recentUserAction =UserAction.READ;
        // It will get the key and string  and default value is string not found

        textViewContentFromFile.setText(sharedPreferences.getString("SAMPLE_KEY","String not Found"));

        }


        private void populateTextFromPreviousSession(){

        recentUserAction =UserAction.READ;

            textViewContentFromFile.setText("From Previous Session: \n "+sharedPreferences.getString("SAMPLE_KEY","String not found"));
        }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater =getMenuInflater();
        menuInflater.inflate(R.menu.menu_main_activity,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        listener = new UserSettingsChangeListener(mContext);
        switch(item.getItemId()){

            case R.id.menuId_app_settings:
                 // for starting new class through settings in menu
                startActivity(new Intent(mContext,SettingsActivity.class));
                
                // when there is change in setting activity  it will listen it here 
                PreferenceManager.getDefaultSharedPreferences(mContext).registerOnSharedPreferenceChangeListener(listener);
                break;
                default:break;




        }
        return super.onOptionsItemSelected(item);

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        PreferenceManager.getDefaultSharedPreferences(mContext).unregisterOnSharedPreferenceChangeListener(listener);
    }
}

