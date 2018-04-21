package com.example.geo.budgetmanagement;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView salaryTv;
    private TextView moneySoldTv;
    private TextView casnicTv;
    private TextView transportTv;
    private TextView mancareTv;
    private TextView currencyTv;
    private TextView dateTimeTv;
    public static double moneySold=0;
    public static double salary=0;
    public static double casnic=0;
    public static double transport=0;
    public static double mancare=0;
    private String currencyS;
    private String dateTimeS;
    private TextView label;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar=(Toolbar) findViewById(R.id.toolbarMain);
        setSupportActionBar(toolbar);

        salaryTv = (TextView) findViewById(R.id.salary);
        moneySoldTv = (TextView) findViewById(R.id.sold);
        casnicTv = (TextView) findViewById(R.id.chcasnice);
        transportTv = (TextView) findViewById(R.id.chtransport);
        mancareTv = (TextView) findViewById(R.id.chmancare);
        currencyTv = (TextView) findViewById(R.id.currency);
        dateTimeTv = (TextView) findViewById(R.id.datepickerdate);
        label=(TextView)findViewById(R.id.label);
        label.setText("set a date");
    }

    @Override
    protected void onResume() {
        super.onResume();
        dateTimeS=Settings.datetime;
        dateTimeTv.setText(dateTimeS);
        currencyS=Settings.radiobuttontext;
        currencyTv.setText(currencyS);

        Intent intent=getIntent();
        currencyS=Settings.radiobuttontext;
        dateTimeS=Settings.datetime;
        currencyTv.setText(currencyS);
        dateTimeTv.setText(dateTimeS);
            if(moneySold!=0) {
                moneySoldTv.setText("" + moneySold);
                moneySoldTv.setBackgroundResource(R.color.red);
            }
            if(salary!=0) {
                salaryTv.setText("" + salary);
            }
            if(mancare!=0) {
                mancareTv.setText("" + mancare);
            }
            if(transport!=0) {
                transportTv.setText("" + transport);
            }
            if(casnic!=0) {
                casnicTv.setText("" + casnic);
            }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id=item.getItemId();
        if(id==R.id.main_settings) {
            Intent intent = new Intent(this, Settings.class);
            this.startActivity(intent);
        }
        if(id==R.id.main_spendings){
            Intent i=new Intent(this,Spendings.class);
            this.startActivity(i);
        }
        if(id==R.id.main_history){
            Intent i1=new Intent(this,History_of_Budget.class);
            this.startActivity(i1);
        }
        if(id==R.id.convertorvalutar){
            Intent i2=new Intent(this,Curs_valutar.class);
            this.startActivity(i2);
        }
        if(id==R.id.piechart){
            Intent i3=new Intent(this,ChartSpendings.class);
            this.startActivity(i3);
        }
        return super.onOptionsItemSelected(item);
    }



    public void plus(View view){

        Intent i2=new Intent(getApplicationContext(),Add_note.class);
        this.startActivity(i2);
    }

    protected  void addDB(View v){
        AdapterDB adapter=new AdapterDB(getApplicationContext(),"BudgetDB",1);
        adapter.openConnection();
        Budget b=new Budget(Double.valueOf(salaryTv.getText().toString()),Double.valueOf(casnicTv.getText().toString()),Double.valueOf(mancareTv.getText().toString()),Double.valueOf(transportTv.getText().toString()));
        adapter.insertBudget(b);
        adapter.insertSold(Double.valueOf(moneySoldTv.getText().toString()));
        Toast.makeText(this, "Bugetul si soldul au fost adaugate cu succes", Toast.LENGTH_SHORT).show();

    }
}
