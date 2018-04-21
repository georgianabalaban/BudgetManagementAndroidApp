package com.example.geo.budgetmanagement;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class History_of_Budget extends AppCompatActivity {

    public static List<Budget> listaBudget= new ArrayList<Budget>();
    ListView listView;
    BudgetAdapter budgetAdapter;
    Toolbar myToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_of__budget);
        myToolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.my_toolbarHistory);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        listView=(ListView)findViewById(R.id.historylist);
        budgetAdapter=new BudgetAdapter(getApplicationContext(),R.layout.row_layout,listaBudget);
        listView.setAdapter(budgetAdapter);
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterable<DataSnapshot> budgets = dataSnapshot.child("Budgets").getChildren();
                for(DataSnapshot ds:budgets){
                    Budget b =ds.getValue(Budget.class);
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               budgetAdapter.remove(budgetAdapter.getItem(position));
               budgetAdapter.notifyDataSetChanged();
            }
        });


    }



    protected void send(View v){
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
        DatabaseReference studentsRef = reference.child("Budgets");
        for(int i=0;i<budgetAdapter.getCount();i++) {
            Budget b=new Budget(budgetAdapter.getItem(i).getSalary(),budgetAdapter.getItem(i).getChcasnice(),budgetAdapter.getItem(i).getChmancare(),budgetAdapter.getItem(i).getChtransport());
            DatabaseReference budgetRef = studentsRef.child(""+b.hashCode());
            budgetRef.setValue(b);
        }
        Toast.makeText(getApplicationContext(),"datele au fost adaugate in Firebase DB",Toast.LENGTH_SHORT).show();
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();
        if(id == android.R.id.home){
            Intent intent=new Intent(this, MainActivity.class);
            this.startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    protected void adaugaFis(View v){
        try {
            OutputStreamWriter osw=new OutputStreamWriter(openFileOutput("fisier.txt", Context.MODE_PRIVATE));
            for(int i=0;i<budgetAdapter.getCount();i++) {
                String text = budgetAdapter.getItem(i).toString();
                osw.write(text);
            }
            Toast.makeText(getApplicationContext(),"Datele au fost adaugate",Toast.LENGTH_SHORT).show();
            osw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void citesteFis(View v){
        try {
            InputStreamReader isr=new InputStreamReader(openFileInput("fisier.txt"));//nume fis
            BufferedReader reader=new BufferedReader(isr);
            String text=reader.readLine();
            Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
            isr.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
