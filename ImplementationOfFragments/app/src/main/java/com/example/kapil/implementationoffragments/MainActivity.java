package com.example.kapil.implementationoffragments;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements FragmentActionListener{

    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragmentManager = getSupportFragmentManager();
        addCountriesFragment();

    }

    private void addCountriesFragment(){
        fragmentTransaction=fragmentManager.beginTransaction();

        CountriesFragment countryListFragment=new CountriesFragment();
        //setFragmentActionListener needs to be invoked when fragment instance is creating just before launching it
        countryListFragment.setFragmentActionListener(this);

        fragmentTransaction.add(R.id.fragmentContainer,countryListFragment);
        fragmentTransaction.commit();
    }

    //private void addCountryDescriptionFragment(String countryName)
    private void addCountryDescriptionFragment(Bundle bundle){
        fragmentTransaction=fragmentManager.beginTransaction();

        CountryDescriptionFragment countryDescriptionFragment=new CountryDescriptionFragment();

      //  Bundle bundle=new Bundle();   // so here i used this because it will help mainactivity to communicate with fragment
        // It will set the data in key-Pair
       // bundle.putString(FragmentActionListener.KEY_SELECTED_COUNTRY,countryName);

        // here setArguments will set data in form of array i.e bundle will received by fragment 2 on activity created

        countryDescriptionFragment.setArguments(bundle);

        fragmentTransaction.replace(R.id.fragmentContainer,countryDescriptionFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
//
//    @Override
//    public void onCountrySelected(String country) {
//        addCountryDescriptionFragment(country);
//    }


    public void onActionPerformed(Bundle bundle) {
        int actionPerformed = bundle.getInt(FragmentActionListener.ACTION_KEY);
        switch (actionPerformed){
            case FragmentActionListener.ACTION_VALUE_COUNTRY_SELECTED: addCountryDescriptionFragment(bundle);
            break;
        }
    }
}

