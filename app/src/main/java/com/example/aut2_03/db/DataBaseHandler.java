package com.example.aut2_03.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;

import androidx.annotation.Nullable;

import com.example.aut2_03.Game;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class DataBaseHandler extends SQLiteOpenHelper {

    public static final String DBNAME = "myGames.db";
    public static final String GAMES_TABLE = "games";

    private Context context;

    public DataBaseHandler(@Nullable Context context) {
        super(context, DBNAME, null, 1);
        context = context;
    }

    @Override
    public void onConfigure(SQLiteDatabase db) {
        super.onConfigure(db);
        db.setForeignKeyConstraintsEnabled(true);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE games (name TEXT PRIMARY KEY, developer TEXT, image BLOB)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS games");
        onCreate(db);
    }

    public boolean insertData(String name, String developer, byte[] imageBytes) {
        SQLiteDatabase mydb = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("developer", developer);
        contentValues.put("image", imageBytes);
        long result = mydb.insert(GAMES_TABLE, null, contentValues);

        return result != -1;
    }

    public Game getGame(String name) {
        SQLiteDatabase mydb = this.getWritableDatabase();
        Cursor cursor = mydb.rawQuery("SELECT * FROM games WHERE name = ?", new String[]{name});

        if (cursor.moveToFirst()) {
            Game game = new Game();
            game.setName(cursor.getString(0));
            game.setDeveloper(cursor.getString(1));
            game.setImage(cursor.getBlob(2));

            return game;
        }

        return null;
    }

    public List<Game> getGames() {
        SQLiteDatabase mydb = this.getWritableDatabase();
        Cursor cursor = mydb.query("games", null, null, null, null, null, null);

        List<Game> games = new ArrayList<>();
        while (cursor.moveToNext()) {
            Game toAdd = new Game();
            toAdd.setName(cursor.getString(0));
            toAdd.setDeveloper(cursor.getString(1));
            toAdd.setImage(cursor.getBlob(2));

            games.add(toAdd);
        }

        return games;
    }

    public boolean removeGame(String name) {
        SQLiteDatabase mydb = this.getWritableDatabase();
        long result = mydb.delete("games", "name = ?", new String[]{name});
        return result > 0;
    }
}
