package com.example.kapil.sharedprefrencesdatabase.Settings;

import android.os.Bundle;
import android.preference.PreferenceActivity;

import com.example.kapil.sharedprefrencesdatabase.R;

public class SettingsActivity extends PreferenceActivity {
    public static final String TAG = SettingsActivity.class.getSimpleName();

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.user_pref_settins);
    }
}
