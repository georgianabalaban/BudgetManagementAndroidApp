package com.example.geo.budgetmanagement;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by geo on 16.12.2017.
 */

public class HelperSold extends SQLiteOpenHelper {
    public HelperSold(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("Create table "+ DBConstSold.tabela+"("+ DBConstSold.coloanaid+" integer primary key autoincrement, "+ DBConstSold.coloanaSold+" REAL, "+ DBConstSold.coloanaidDetalii+" INTEGER, foreign key( "+ DBConstSold.coloanaidDetalii+" ) references "+DBConst.coloanaid+");");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("Drop table if exists "+ DBConstSold.tabela+";");
        onCreate(db);
    }
}
