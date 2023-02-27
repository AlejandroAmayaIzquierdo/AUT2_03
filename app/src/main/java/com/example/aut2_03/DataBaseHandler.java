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

    public static final String DBNAME = "sandBox.db";
    public static final String GAMES_TABLE = "games";

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
        db.execSQL("create Table games(name TEXT primary key,developer TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("drop table if exists games");
        onCreate(db);
    }

    public Boolean insertData(String name,String developer){
        SQLiteDatabase mydb = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name",name);
        contentValues.put("developer",developer);
        long result = mydb.insert(GAMES_TABLE,null,contentValues);
        if(result == 1)
            return true;

        return false;
    }

    public Games getGame(String name){
        SQLiteDatabase mydb = this.getWritableDatabase();
        Cursor cursor = mydb.rawQuery("select * from games where name = ?",new String[]{name});

        if(cursor.moveToFirst()){
            Games game = new Games();
            game.setName(cursor.getString(0));
            game.setDeveloper(cursor.getString(1));

            return game;
        }

        return null;
    }
    public List<Games> getUsers(){
        SQLiteDatabase mydb = this.getWritableDatabase();
        Cursor cursor = mydb.query(GAMES_TABLE,null,null,null,null,null,null);

        List<Games> games = new ArrayList<>();
        while(cursor.moveToNext()){
            Games user = new Games();
            user.setName(cursor.getString(0));
            user.setDeveloper(cursor.getString(1));

            games.add(user);
        }

        return games;
    }

    public boolean removeUser(String email){
        SQLiteDatabase mydb = this.getWritableDatabase();
        long result = mydb.delete(GAMES_TABLE,"name = ?",new String[]{email});
        if(result > 0){
            return true;
        }

        return false;
    }
}
