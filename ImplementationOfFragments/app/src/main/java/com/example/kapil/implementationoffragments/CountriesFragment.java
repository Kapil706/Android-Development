package com.example.kapil.implementationoffragments;

import android.support.v4.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.lang.ref.WeakReference;

public class CountriesFragment extends Fragment {
    private static final String COMMON_TAG = "OrintationChange";
    private static final String FRAGMENT_NAME = CountriesFragment.class.getSimpleName();

    View rootView;

    ListView listViewCountries;
    ArrayAdapter<String> countryNamesAdapter;
    Context context;
    String [] countries;

    FragmentActionListener fragmentActionListener;  // this will help countries fragment to communicate with MainActivity that will further trigger another fragment
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_countries,container,false);
        // Inflater value is saved inside rootView at particular instance so that we can use rootView again to initialize
        // various other methods

        initUI();
        return rootView;
    }

    @Override
    // For setting the title bar for the fragment
    public void onResume() {
        super.onResume();
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(getString(R.string.app_name)+"->Select Country");



    }
// When the activity is lost due to change in orientation this method will help to reinitialize callback again
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if(savedInstanceState!=null){
            fragmentActionListener = (MainActivity)getActivity();
        }
    }


  // setFragmentActionListener will take instance of interface
    public void setFragmentActionListener(FragmentActionListener fragmentActionListener){
        this.fragmentActionListener = fragmentActionListener;
    }

    private void initUI(){
        context  = getContext();
        countries = getResources().getStringArray(R.array.countries);
        listViewCountries = (ListView)rootView.findViewById(R.id.listViewCountries);

        countryNamesAdapter = new ArrayAdapter<String>(context,android.R.layout.simple_list_item_1,countries);
        listViewCountries.setAdapter(countryNamesAdapter);

        listViewCountries.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (fragmentActionListener!=null){
                    fragmentActionListener.onCountrySelected(countries[i]);
                }
//                Bundle bundle = new Bundle();
//                bundle.putInt(FragmentActionListener.ACTION_KEY, FragmentActionListener.ACTION_VALUE_COUNTRY_SELECTED);
//                bundle.putString(FragmentActionListener.KEY_SELECTED_COUNTRY, countries[i]);
//                fragmentActionListener.onActionPerformed(bundle);
          }
        });
        }
}


