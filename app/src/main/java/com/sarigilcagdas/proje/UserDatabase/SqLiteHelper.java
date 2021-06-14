package com.sarigilcagdas.proje.UserDatabase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SqLiteHelper extends SQLiteOpenHelper {

    public SqLiteHelper(Context context){
        super(context,"User",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table userInformation ( id integer primary key autoincrement, " +
                "username TEXT unique, password TEXT, keyword TEXT unique);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists userInformation");
        onCreate(db);
    }
}
