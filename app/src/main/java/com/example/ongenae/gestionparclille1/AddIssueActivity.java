package com.example.ongenae.gestionparclille1;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ongenae.gestionparclille1.database.DatabaseHelper;
import com.example.ongenae.gestionparclille1.database.Issue;
import com.j256.ormlite.android.apptools.OpenHelperManager;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Locale;

public class AddIssueActivity extends AppCompatActivity {

    /* items de la UI */
    private Spinner mSpinnerType;
    private EditText mAdresse, mDetails;
    private TextView mPosition;
    private double mLatitude, mLongitude;

    /* database */
    private DatabaseHelper mDbHelper;

    /* géolocalisation */
    private LocationManager mLocationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        mLocationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        mSpinnerType = findViewById(R.id.type_add);
        mSpinnerType.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, TypeEnum.values()));
        mAdresse = findViewById(R.id.adresse_add);
        mDetails = findViewById(R.id.details_add);
        mPosition = findViewById(R.id.positiongps_add);

        setConfigLocationManager();
    }

    /**
     * on click permettant d'ajouter l'issue entrée avec message de confirmation s'il manque des informations dans le formulaire
     * @param view, le bouton ayant été cliqué
     */
    public void add_issue_on_click(View view) {
        if (view.getId() == R.id.btn_ajout) {
            Issue tIssue = new Issue((TypeEnum) mSpinnerType.getSelectedItem(),
                    mLatitude, mLongitude,
                    mAdresse.getText().toString(),
                    mDetails.getText().toString());
            try {
                getHelper().getIssueDao().create(tIssue);
            } catch (SQLException e) {
                Toast toast = Toast.makeText(this, "Erreur d'ajout du problème.", Toast.LENGTH_LONG);
                toast.show();
            }
            finish();
        }
    }

    private void setConfigLocationManager(){
        String provider = LocationManager.GPS_PROVIDER;
        int minDistance = 5;
        int minTime = 120000;
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED || ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if (!ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)) {
                requestPermissions(new String[] { Manifest.permission.ACCESS_FINE_LOCATION }, 2);
            }
            return;
        }
        mLocationManager.requestLocationUpdates(provider, minTime, minDistance, new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                mLatitude = location.getLatitude();
                mLongitude = location.getLongitude();
                String tPosition = location.getLatitude() + ";" + location.getLongitude();
                mPosition.setText(tPosition);
                Geocoder geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());
                try {
                    Address tAdresse = geocoder.getFromLocation(mLatitude, mLongitude, 1).get(0);
                    String address = "";
                    for (int i = 0; i < tAdresse.getMaxAddressLineIndex(); i++) {
                        address += tAdresse.getAddressLine(i) + " ";
                    }
                    mAdresse.setText(address);
                } catch (IOException e) {
                    Toast toast = Toast.makeText(getApplicationContext(), "Erreur de récupération d'adresse.", Toast.LENGTH_LONG);
                    toast.show();
                }
            }

            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {}

            @Override
            public void onProviderEnabled(String s) {}

            @Override
            public void onProviderDisabled(String s) {}
        });
    }

    private DatabaseHelper getHelper() {
        if (mDbHelper == null) {
            mDbHelper = OpenHelperManager.getHelper(this, DatabaseHelper.class);
        }
        return mDbHelper;
    }
}
