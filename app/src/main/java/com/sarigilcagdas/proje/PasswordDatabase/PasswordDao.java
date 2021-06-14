package com.sarigilcagdas.proje.PasswordDatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class PasswordDao {

    SQLiteDatabase db;
    DatabaseHelper _db;

    public PasswordDao(Context c){
        _db = new DatabaseHelper(c);
    }

    public void open(){
        db = _db.getWritableDatabase();
    }
    public void close(){
        _db.close();
    }

    public void addToDB(String type, String password){
        ContentValues values = new ContentValues();
        values.put("type",type);
        values.put("password",password);

        db.insert("passwordInformation",null,values);

    }

    public ArrayList<Password> getValues(){

        ArrayList<Password> passwords = new ArrayList<>();
        String[] columns = {"type","password"};
        Cursor c = db.query("passwordInformation",columns,null,null,null,null,null);
        while (c.moveToNext()){

            passwords.add(new Password(c.getString(c.getColumnIndex("type")),c.getString(c.getColumnIndex("password"))));
        }

        return passwords;
    }

    public void deleteToDB(String deletedType ,String deletedPassword){
        db.execSQL("delete from passwordInformation where type= "+ "\"" + deletedType +"\" and "+ "password= "+ "\"" + deletedPassword + "\"");
    }

    public void updateToDB(String checkType, String updatedPassword){
        db.execSQL("UPDATE passwordInformation SET password=" + "\"" + updatedPassword + "\" where " + "type=" + "\""+ checkType + "\"");
    }
}
