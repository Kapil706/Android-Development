package com.example.kapil.beach;

import android.app.Dialog;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.kapil.beach.rest.SafetyActivity;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Toolbar toolbar;
    private EditText beachET;
    private ImageView searchIcon;
    private Spinner skinTypeSelectionSpinner;
    private Button checkResultButton, openBlogButton, openSafetyButton;
    private ProgressBar loadingBar;
    private ImageView infoIcon;

    private final int PLACE_AUTOCOMPLETE_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        beachET = findViewById(R.id.beach_et);
        searchIcon = findViewById(R.id.search_icon);
        skinTypeSelectionSpinner = findViewById(R.id.skin_type_selection);
        checkResultButton = findViewById(R.id.check_result_button);
        loadingBar = findViewById(R.id.loading_bar);
        infoIcon = findViewById(R.id.info_icon);
        openBlogButton = findViewById(R.id.open_blog);
        openSafetyButton = findViewById(R.id.open_protection);

        beachET.setOnClickListener(this);
        searchIcon.setOnClickListener(this);
        checkResultButton.setOnClickListener(this);
        infoIcon.setOnClickListener(this);
        openBlogButton.setOnClickListener(this);
        openSafetyButton.setOnClickListener(this);
        setSupportActionBar(toolbar);

        Repository.getInstance().saveSkinType(1);

        skinTypeSelectionSpinner.setAdapter(new SkinColorAdapter(this, new String[] {"Type 1", "Type 2", "Type 3", "Type 4", "Type 5", "Type 6"},
                new int[] {R.color.type1Color, R.color.type2Color, R.color.type3Color, R.color.type4Color, R.color.type5Color, R.color.type6Color}));

        skinTypeSelectionSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Repository.getInstance().saveSkinType(position + 1);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.search_icon:
            case R.id.beach_et:
                try {
                    Intent intent =
                            new PlaceAutocomplete.IntentBuilder(PlaceAutocomplete.MODE_FULLSCREEN)
                                    .build(this);
                    startActivityForResult(intent, PLACE_AUTOCOMPLETE_REQUEST_CODE);
                } catch (GooglePlayServicesRepairableException | GooglePlayServicesNotAvailableException e) {
                    Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.check_result_button:
                loadingBar.setVisibility(View.VISIBLE);
                checkResultButton.setVisibility(View.GONE);
                Repository.getInstance().evaluate(new Repository.UVDataListener() {
                    @Override
                    public void onUVDataReceived() {
                        loadingBar.setVisibility(View.GONE);
                        checkResultButton.setVisibility(View.VISIBLE);
                        ResultSheet addPhotoBottomDialogFragment =
                                ResultSheet.newInstance();
                        addPhotoBottomDialogFragment.show(getSupportFragmentManager(),
                                "result");
                    }

                    @Override
                    public void onError(String error) {
                        Snackbar.make(checkResultButton, error, Snackbar.LENGTH_LONG);
                    }
                });
                break;
            case R.id.info_icon:
                Dialog settingsDialog = new Dialog(this);
                settingsDialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
                settingsDialog.setContentView(getLayoutInflater().inflate(R.layout.image_dialog
                        , null));
                settingsDialog.show();
                break;
            case R.id.open_blog:
                startActivity(new Intent(this, BlogActivity.class));
                break;
            case R.id.open_protection:
                startActivity(new Intent(this, SafetyActivity.class));
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == PLACE_AUTOCOMPLETE_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                Place place = PlaceAutocomplete.getPlace(this, data);
                beachET.setText(place.getName());
                Repository.getInstance().setPlace(place);
            } else if (resultCode == PlaceAutocomplete.RESULT_ERROR) {
                Status status = PlaceAutocomplete.getStatus(this, data);
                Snackbar.make(searchIcon, status.getStatusMessage(), Snackbar.LENGTH_LONG);
            }
        }
    }
}
