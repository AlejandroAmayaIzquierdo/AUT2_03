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

    public static final String DBNAME = "sandBox1.db";
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
        db.execSQL("create Table games(name TEXT primary key,developer TEXT,image INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("drop table if exists games");
        onCreate(db);
    }

    public Boolean insertData(String name,String developer,Integer image){
        SQLiteDatabase mydb = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name",name);
        contentValues.put("developer",developer);
        contentValues.put("image",image);
        long result = mydb.insert(GAMES_TABLE,null,contentValues);
        if(result == 1)
            return true;

        return false;
    }

    public Game getGame(String name){
        SQLiteDatabase mydb = this.getWritableDatabase();
        Cursor cursor = mydb.rawQuery("select * from games where name = ?",new String[]{name});

        if(cursor.moveToFirst()){
            Game game = new Game();
            game.setName(cursor.getString(0));
            game.setDeveloper(cursor.getString(1));
            game.setImage(cursor.getInt(2));

            return game;
        }

        return null;
    }
    public List<Game> getGames(){
        SQLiteDatabase mydb = this.getWritableDatabase();
        Cursor cursor = mydb.query("games",null,null,null,null,null,null);

        List<Game> games = new ArrayList<>();
        while(cursor.moveToNext()){
            Game toAdd = new Game();
            toAdd.setName(cursor.getString(0));
            toAdd.setDeveloper(cursor.getString(1));
            toAdd.setImage(cursor.getInt(2));

            games.add(toAdd);
        }

        return games;
    }

    public boolean removeGame(String name){
        SQLiteDatabase mydb = this.getWritableDatabase();
        long result = mydb.delete("games","name = ?",new String[]{name});
        if(result > 0){
            return true;
        }

        return false;
    }
}
