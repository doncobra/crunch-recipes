package com.example.kenny.crunchrecipes;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.content.ContentValues;
import android.database.Cursor;
import java.util.ArrayList;
import java.util.List;



public class DbCrunchRDataSource {
    private static final String LOG_TAG = DbCrunchRDataSource.class.getSimpleName();

    private SQLiteDatabase database;
    private DbCrunchRHelper dbHelper;

    private String[] columns = {
            DbCrunchRHelper.COLUMN_ID,
            DbCrunchRHelper.COLUMN_RECIPE,
            DbCrunchRHelper.COLUMN_HEARTS,
            DbCrunchRHelper.COLUMN_BONUS_HEARTS
    };


    public DbCrunchRDataSource(Context context) {
        Log.d(LOG_TAG, "Unsere DataSource erzeugt jetzt den dbHelper.");
        dbHelper = new DbCrunchRHelper(context);
    }

    public void open() {
        Log.d(LOG_TAG, "Eine Referenz auf die Datenbank wird jetzt angefragt.");
        database = dbHelper.getWritableDatabase();
        Log.d(LOG_TAG, "Datenbank-Referenz erhalten. Pfad zur Datenbank: " + database.getPath());
    }

    public void close() {
        dbHelper.close();
        Log.d(LOG_TAG, "Datenbank mit Hilfe des DbHelpers geschlossen.");
    }

    // Datensätze einfügen
    public DbCrunchR createDbMemo(String recipe, int hearts, int bonus) {
        ContentValues values = new ContentValues();
        values.put(DbCrunchRHelper.COLUMN_RECIPE, recipe);
        values.put(DbCrunchRHelper.COLUMN_HEARTS, hearts);
        values.put(DbCrunchRHelper.COLUMN_BONUS_HEARTS, bonus);

        long insertId = database.insert(DbCrunchRHelper.TABLE_RECIPE_LIST, null, values);

        Cursor cursor = database.query(DbCrunchRHelper.TABLE_RECIPE_LIST,
                columns, DbCrunchRHelper.COLUMN_ID + "=" + insertId,
                null, null, null, null);

        cursor.moveToFirst();
        return cursorToDbMemo(cursor);
    }

    //Cursor in Daten-Objekt
    private DbCrunchR cursorToDbMemo(Cursor cursor) {
        int idIndex = cursor.getColumnIndex(DbCrunchRHelper.COLUMN_ID);
        int idRecipe = cursor.getColumnIndex(DbCrunchRHelper.COLUMN_RECIPE);
        int idHearts = cursor.getColumnIndex(DbCrunchRHelper.COLUMN_HEARTS);
        int idBonus = cursor.getColumnIndex(DbCrunchRHelper.COLUMN_BONUS_HEARTS);

        String recipe = cursor.getString(idRecipe);
        int hearts = cursor.getInt(idHearts);
        int bonus = cursor.getInt(idBonus);
        long id = cursor.getLong(idIndex);

        DbCrunchR DbMemo = new DbCrunchR(recipe, hearts, bonus, id);

        return DbMemo;
    }

    // Alle Datensätze auslesen
    public List<DbCrunchR> getAllDbMemos() {
        List<DbCrunchR> DbMemoList = new ArrayList<>();

        Cursor cursor = database.query(DbCrunchRHelper.TABLE_RECIPE_LIST,
                columns, null, null, null, null, null);

        cursor.moveToFirst();

        if(cursor.getCount()==0) return DbMemoList;
        DbCrunchR DbMemo;

        while(cursor.isAfterLast() == false) {
            DbMemo = cursorToDbMemo(cursor);
            DbMemoList.add(DbMemo);
            Log.d(LOG_TAG, "ID: " + DbMemo.getId() + ", Inhalt: " + DbMemo.toString());
            cursor.moveToNext();
        }

        cursor.close();

        return DbMemoList;
    }
}