package com.example.geo.budgetmanagement;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by geo on 16.12.2017.
 */

public class AdapterDB {
    private SQLiteOpenHelper soh;
    private SQLiteDatabase db;
    public AdapterDB(Context context, String nume, int versiune){
        soh=new Helper(context,nume,null,versiune);
    }
    public void openConnection(){
        db=soh.getWritableDatabase();
    }
    public void closeConnection(){
        soh.close();
    }

    public void insertBudget(Budget b){
        ContentValues cv=new ContentValues();
        cv.put(DBConst.coloanaSalary, b.getSalary());
        cv.put(DBConst.coloanachcasnice, b.getChcasnice());
        cv.put(DBConst.coloanachmancare, b.getChmancare());
        cv.put(DBConst.coloanachtransport, b.getChtransport());
        db.insert(DBConst.tabela,null,cv);
    }

    public void insertSold(double sold){
        ContentValues cv=new ContentValues();
        cv.put(DBConstSold.coloanaSold, sold);
        db.insert(DBConstSold.tabela,null,cv);
    }


}
