package com.example.kapil.sharedprefrencesdatabase.Settings;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

public class UserSettingsChangeListener implements SharedPreferences.OnSharedPreferenceChangeListener {

    public static final String TAG= UserSettingsChangeListener.class.getSimpleName();
    private Context mContext;

    public  UserSettingsChangeListener(Context context){
        this.mContext=context;


    }



    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String s) {

        Toast.makeText(mContext,s+" value changed: "+sharedPreferences.getString(s,"Unkown"),Toast.LENGTH_SHORT).show();


    }
}
