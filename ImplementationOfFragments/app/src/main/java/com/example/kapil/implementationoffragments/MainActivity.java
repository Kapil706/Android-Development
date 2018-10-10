package com.example.kapil.implementationoffragments;
import android.content.res.Configuration;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
// There are two ways for retaining the state of the application while orientation changes
// one is change the manifest file and add change cofiguration and override the function i.e onorientiationchanges in maianactivity
// This method will handle all internal work easily and will force oncreate method not to create new activity every time the orientation changes
// Second method is handle all this manually when orientation changes

// Here Both are implemented
public class MainActivity extends AppCompatActivity implements FragmentActionListener {
    String selectedCountry;

    private static final String COMMON_TAG = "OrintationChange";
    private static final String ACTIVITY_NAME = MainActivity.class.getSimpleName();

    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragmentManager = getSupportFragmentManager();
        // It means savedInstanceState is null means it is a fresh start so addCountriesFragment must be inside it
        // Now due to this the callback in CountriesFragment get lost because Activity is lost so we have to set call back again
        // So we override a function onActivitycreated in countriesFragment so that when instancestateis not null activityorientation changes and fragementlistener initialize again
// Now this application only work for single layout fragment but if we have multiple layout fragment
        // So for this we have to make another layout folder particular for landscape mode that will support two fragments at a time.
        //now after this we have to manage the fragments
        if (findViewById(R.id.activity_main_portrait) != null) {

            // this if - else will be irrelevant if we are not creating new activity means we are retaininig the state present state
           // if (savedInstanceState == null) {
                addCountriesFragment();
//            } else {
//                addCountryDescriptionFragment(savedInstanceState.getString("selectedCountry", null));
//            }
 }
            else if (findViewById(R.id.activity_main_landscape) != null) {
                addCountriesFragment();
              //  if (savedInstanceState == null) {
                    addCountryDescriptionFragment(R.id.fragmentContainer2, "India");
//                } else {
//
//                    addCountryDescriptionFragment(R.id.fragmentContainer2, savedInstanceState.getString("selectedCountry", null));
//                }
            }

//        if(savedInstanceState ==null){
//            addCountriesFragment();
//        }
            //  addCountriesFragment();
            // else the activity is restarted on orientation change
        }
        @Override
        protected void onSaveInstanceState (Bundle outState){
            super.onSaveInstanceState(outState);
            Log.i("OrintationChange", "MainActivity onSaveInstanceState");
            outState.putString("selectedCountry", selectedCountry);

        }

        @Override
        protected void onRestoreInstanceState (Bundle savedInstanceState){
            super.onRestoreInstanceState(savedInstanceState);
            Log.i("OrintationChange", "MainActivity onSaveInstanceState");
            selectedCountry = savedInstanceState.getString("selectedCountry", "India");
        }

        private void addCountriesFragment () {
            fragmentTransaction = fragmentManager.beginTransaction();

            CountriesFragment countryListFragment = new CountriesFragment();
            //setFragmentActionListener needs to be invoked when fragment instance is creating just before launching it
            countryListFragment.setFragmentActionListener(this);

            fragmentTransaction.add(R.id.fragmentContainer, countryListFragment);
            fragmentTransaction.commit();
        }

        private void addCountryDescriptionFragment (String countryName){
//    private void addCountryDescriptionFragment(Bundle bundle){
            fragmentTransaction = fragmentManager.beginTransaction();

            CountryDescriptionFragment countryDescriptionFragment = new CountryDescriptionFragment();

            Bundle bundle = new Bundle();   // so here i used this because it will help mainactivity to communicate with fragment
            //  It will set the data in key-Pair
            bundle.putString(FragmentActionListener.KEY_SELECTED_COUNTRY, countryName);

            //   here setArguments will set data in form of array i.e bundle will received by fragment 2 on activity created

            countryDescriptionFragment.setArguments(bundle);

            fragmentTransaction.replace(R.id.fragmentContainer, countryDescriptionFragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }

        private void addCountryDescriptionFragment ( int fragmentContainerId, String countryName){
            fragmentTransaction = fragmentManager.beginTransaction();

            CountryDescriptionFragment countryDescriptionFragment = new CountryDescriptionFragment();


            Bundle bundle = new Bundle();
            bundle.putString(FragmentActionListener.KEY_SELECTED_COUNTRY, countryName);
            countryDescriptionFragment.setArguments(bundle);

            fragmentTransaction.replace(fragmentContainerId, countryDescriptionFragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }



//
//    @Override
//    public void onCountrySelected(String country) {
//        addCountryDescriptionFragment(country);
//    }


//    public void onActionPerformed(Bundle bundle) {
//        int actionPerformed = bundle.getInt(FragmentActionListener.ACTION_KEY);
//        switch (actionPerformed){
//            case FragmentActionListener.ACTION_VALUE_COUNTRY_SELECTED: addCountryDescriptionFragment(bundle);
//            break;
//        }
//    }

        @Override
        public void onConfigurationChanged (Configuration newConfig) {
            super.onConfigurationChanged(newConfig);
            setContentView(R.layout.activity_main);
           if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
//                Log.i(COMMON_TAG, "landscape");
//
//            } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
//                Log.i(COMMON_TAG, "portrait");
//            }
            Log.i("OrintationChange", "landscape");
            addCountriesFragment();
            if (selectedCountry == null) {
                addCountryDescriptionFragment("India");
            } else {
                addCountryDescriptionFragment(R.id.fragmentContainer2, selectedCountry);
            }

        } else if(newConfig.orientation==Configuration.ORIENTATION_PORTRAIT){
        Log.i("OrintationChange", "portrait");
        if(selectedCountry==null){
            addCountriesFragment();
        }else {
            addCountryDescriptionFragment(selectedCountry);
        }
    }   }





    @Override
    public void onCountrySelected(String country) {
        selectedCountry = country;
        if (findViewById(R.id.activity_main_landscape) == null) {
            addCountryDescriptionFragment(country);
        } else {
            addCountryDescriptionFragment(R.id.fragmentContainer2, country);
        }

    }
}
