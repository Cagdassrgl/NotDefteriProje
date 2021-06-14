package com.sarigilcagdas.proje.UserDatabase;

import android.app.Presentation;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

public class UserDao {
    SQLiteDatabase db;
    SqLiteHelper _db;

    public UserDao(Context c){
        _db = new SqLiteHelper(c);
    }

    public void open(){
        db = _db.getWritableDatabase();
    }

    public  void close(){
        _db.close();
    }

    public long addToDb(User user){
        ContentValues values = new ContentValues();

        values.put("username",user.getUsername());
        values.put("password",user.getPassword());
        values.put("keyword", user.getKeyword());

        long result = db.insert("userInformation",null,values);

        return  result;
    }

    public ArrayList<User> getValues(){

        ArrayList<User> users = new ArrayList<>();
        String[] columns = {"username","password","keyword"};
        Cursor c = db.query("userInformation",columns,null,null,null,null,null);

        while (c.moveToNext()){
            User tempUser = new User(c.getString(c.getColumnIndex("username")),
                    c.getString(c.getColumnIndex("password")),
                    c.getString(c.getColumnIndex("keyword")));

            users.add(tempUser);
        }
        return users;
    }

    public void update(User user, String password){

        db.execSQL("update userInformation set password= "+ "\"" + password+ "\"" + " where keyword=" + "\"" + user.getKeyword()+ "\"");
    }

    public void unique(){
        String[] columns = {"username","password","keyword"};
        Cursor c = db.query("userInformation",columns,null,null,null,null,null);

    }
}
