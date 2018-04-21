package com.example.geo.budgetmanagement;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ProgressBar;
import android.widget.TextView;

public class Spendings extends AppCompatActivity {

    private ProgressBar mancare;
    private ProgressBar transport;
    private ProgressBar casnic;
    private int m;
    private int t;
    private int c;
    private double total;
    private TextView mancareTv;
    private TextView casnicTv;
    private TextView transportTv;
    Toolbar myToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spendings);
        myToolbar = (Toolbar) findViewById(R.id.my_toolbarSpendings);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        mancare=(ProgressBar)findViewById(R.id.progressBar_chpentrumancare);
        transport=(ProgressBar)findViewById(R.id.progressBar_chpentrutransport);
        casnic=(ProgressBar)findViewById(R.id.progressBar_chcasnice);
        mancareTv=(TextView)findViewById(R.id.mancareProcent);
        transportTv=(TextView)findViewById(R.id.transportProcent);
        casnicTv=(TextView)findViewById(R.id.casnicProcent);

        total = MainActivity.mancare + MainActivity.casnic + MainActivity.transport;
        m = (int)((MainActivity.mancare * 100) / total);
        t = (int)((MainActivity.transport * 100) / total);
        c = (int)((MainActivity.casnic * 100) / total);
        if (m != 0) {
            mancare.setProgress(m);
            mancareTv.setText(m + " %");
        } else {
            mancare.setProgress(0);
            mancareTv.setText(0 + " %");
        }
        if (t != 0) {
            transport.setProgress(t);
            transportTv.setText(t + " %");
        } else {
            transport.setProgress(0);
            transportTv.setText(0 + " %");
        }
        if (c != 0) {
            casnic.setProgress(c);
            casnicTv.setText(c + " %");
        } else {
            casnic.setProgress(0);
            casnicTv.setText(0 + " %");
        }


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();
        if(id == android.R.id.home){
            Intent intent=new Intent(this, MainActivity.class);
            this.startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}
