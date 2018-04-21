package com.example.geo.budgetmanagement;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class ChartSpendings extends AppCompatActivity {
    float values[]={(float) MainActivity.mancare, (float) MainActivity.casnic, (float) MainActivity.transport};
    Toolbar myToolbar;
    ImageView m;
    ImageView c;
    ImageView t;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart_spendings);
        myToolbar = (Toolbar) findViewById(R.id.my_toolbarChart);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        LinearLayout linear=(LinearLayout) findViewById(R.id.linear);
        values=calculateData(values);
        linear.addView(new MyGraphView(this,values));
        m=(ImageView)findViewById(R.id.blueMancare);
        c=(ImageView)findViewById(R.id.greenCasnic);
        t=(ImageView)findViewById(R.id.redTransport);
        m.setBackgroundColor(Color.BLUE);
        c.setBackgroundColor(Color.GREEN);
        t.setBackgroundColor(Color.RED);
    }
    private float[] calculateData(float[] data) {
        // TODO Auto-generated method stub
        float total=0;
        for(int i=0;i<data.length;i++)
        {
            total+=data[i];
        }
        for(int i=0;i<data.length;i++)
        {
            data[i]=360*(data[i]/total);
        }
        return data;

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
