package com.example.aut2_03;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DataBaseHandler extends SQLiteOpenHelper {

    public static final String DBNAME = "users.db";

    public DataBaseHandler(@Nullable Context context) {
        super(context, DBNAME, null,1);

    }

    @Override
    public void onConfigure(SQLiteDatabase db) {
        super.onConfigure(db);
        db.setForeignKeyConstraintsEnabled(true);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create Table users(email TEXT primary key,name TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("drop table if exists users");
        onCreate(db);
    }

    public Boolean insertData(String email,String userName){
        SQLiteDatabase mydb = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("email",email);
        contentValues.put("name",userName);
        long result = mydb.insert("users",null,contentValues);
        if(result == 1)
            return true;

        return false;
    }

    public User getUser(String email){
        SQLiteDatabase mydb = this.getWritableDatabase();
        Cursor cursor = mydb.rawQuery("select * from users where email = ?",new String[]{email});


        if(cursor.moveToFirst()){
            User user = new User();
            user.setEmail(cursor.getString(0));
            user.setName(cursor.getString(1));

            return user;
        }

        return null;
    }
    public List<User> getUsers(){
        SQLiteDatabase mydb = this.getWritableDatabase();
        Cursor cursor = mydb.query("users",null,null,null,null,null,null);

        List<User> users = new ArrayList<>();
        while(cursor.moveToNext()){
            User user = new User();
            user.setName(cursor.getString(0));
            user.setEmail(cursor.getString(1));

            users.add(user);
        }

        return users;
    }

    public boolean removeUser(String email){
        SQLiteDatabase mydb = this.getWritableDatabase();
        long result = mydb.delete("users","email = ?",new String[]{email});
        if(result > 0){
            return true;
        }

        return false;
    }
}
