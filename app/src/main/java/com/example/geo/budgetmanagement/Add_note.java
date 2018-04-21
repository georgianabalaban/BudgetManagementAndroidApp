package com.example.geo.budgetmanagement;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.google.android.gms.maps.model.LatLng;

public class Add_note extends AppCompatActivity{

    Toolbar myToolbar;
    EditText et;
    TextView locationPlace;
    int status;
    public static LatLng location;
    public static String toastMsg;

    public double money;
    public String spinnerOption;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        et=(EditText)findViewById(R.id.moneySold);
        myToolbar = (Toolbar) findViewById(R.id.my_toolbar2);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        status = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
        if (status != ConnectionResult.SUCCESS) {
            if (GooglePlayServicesUtil.isUserRecoverableError(status)) {
                GooglePlayServicesUtil.getErrorDialog(status, this,
                        100).show();
            }
        }

        final Context context = this;
        locationPlace=(TextView)findViewById(R.id.locationPlace);
            locationPlace.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (status == ConnectionResult.SUCCESS) {
                        int PLACE_PICKER_REQUEST = 199;
                        PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();
                        try {
                            startActivityForResult(builder.build((Activity) context), PLACE_PICKER_REQUEST);
                        } catch (GooglePlayServicesRepairableException e) {
                            e.printStackTrace();
                        } catch (GooglePlayServicesNotAvailableException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
    }

    @Override
    protected void onActivityResult (int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 100){
            status = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
        }
        if (requestCode == 199){

            //process Intent......
            Place place = PlacePicker.getPlace(data, this);
            toastMsg = String.format("Place: %s", place.getName());
            location= place.getLatLng();
            locationPlace.setText(toastMsg);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();
        if(id == android.R.id.home){
            if(!et.getText().toString().equals("")) {
                money = Double.valueOf(et.getText().toString());
                Spinner spinner = (Spinner) findViewById(R.id.spinner);
                spinnerOption = spinner.getSelectedItem().toString();
                switch (spinnerOption) {
                    case "Salariu":
                        MainActivity.moneySold += money;
                        MainActivity.salary += money;
                        break;
                    case "Cheltuieli casnice":
                        MainActivity.moneySold -= money;
                        MainActivity.casnic += money;
                        break;
                    case "Cheltuieli pentru transport":
                        MainActivity.moneySold -= money;
                        MainActivity.transport += money;
                        break;
                    case "Cheltuieli pentru mancare":
                        MainActivity.moneySold -= money;
                        MainActivity.mancare += money;
                        break;
                }
            }
            Budget b=new Budget(MainActivity.salary,MainActivity.casnic,MainActivity.mancare,MainActivity.transport);
            History_of_Budget.listaBudget.add(b);
            Intent intent=new Intent(this, MainActivity.class);
            this.startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    public void addLocation(View v){
        Intent i2=new Intent(getApplicationContext(),MapsActivity.class);
        this.startActivity(i2);
    }

    public void openMarkers(View v){
        Intent i=new Intent(this.getApplicationContext(),Markers.class);
        this.startActivity(i);
    }
}
