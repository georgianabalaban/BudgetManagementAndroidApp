package com.example.geo.budgetmanagement;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class Settings extends AppCompatActivity {

    ImageButton btn;
    int year1,month1,day1;
    static final int DIALOG_ID=0;
    Toolbar myToolbar;
    public static String radiobuttontext;
    public static String datetime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        showDialogOnImageButtonClick();
        myToolbar = (Toolbar) findViewById(R.id.my_toolbar1);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();
        if(id == android.R.id.home){
            int radiobutton = ((RadioGroup)findViewById( R.id.radiogroup )).getCheckedRadioButtonId();
            radiobuttontext=getAtmos(radiobutton);
            Intent intent=new Intent(this, MainActivity.class);
            this.startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    public String getAtmos(int id){
        String atmos="";
        switch( id ) {
            case R.id.eur:
                atmos = "euro";
                break;
            case R.id.ron:
                atmos = "ron";
                break;
            case R.id.usd:
                atmos = "usd";
                break;
        }
        return atmos;
    }

    public void showDialogOnImageButtonClick(){
        btn=(ImageButton)findViewById(R.id.datepickerbtn);
        btn.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View v){
                        showDialog(DIALOG_ID);
                    }
                }
        );
    }

    @Override
    protected DatePickerDialog onCreateDialog(int id){
        if(id==DIALOG_ID){
            return new DatePickerDialog(this, dpickerListner, year1,month1,day1);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener dpickerListner=new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            year1=year;
            month1=month;
            day1=dayOfMonth;
            TextView date=(TextView)findViewById(R.id.Date);
            date.setText(day1+"-"+month1+"-"+year1);
            datetime=day1+"."+month1+"."+year1;
        }
    };
}
