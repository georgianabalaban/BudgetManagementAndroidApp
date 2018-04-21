package com.example.geo.budgetmanagement;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by geo on 16.12.2017.
 */

public class Helper extends SQLiteOpenHelper {
    public Helper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("Create table "+ DBConst.tabela+"("+ DBConst.coloanaid+" integer primary key autoincrement, "+ DBConst.coloanaSalary+" REAL, "+ DBConst.coloanachcasnice+" REAL, "+ DBConst.coloanachmancare+" REAL, "+ DBConst.coloanachtransport+" REAL);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("Drop table if exists "+ DBConst.tabela+";");
        onCreate(db);
    }
}
