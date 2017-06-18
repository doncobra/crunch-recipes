package com.example.kenny.crunchrecipes;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.content.ContentValues;
import android.database.Cursor;
import java.util.ArrayList;
import java.util.List;



public class DbMemoDataSource {
    private static final String LOG_TAG = DbMemoDataSource.class.getSimpleName();

    private SQLiteDatabase database;
    private DbMemoHelper dbHelper;

    private String[] columns = {
            DbMemoHelper.COLUMN_ID,
            DbMemoHelper.COLUMN_RECIPE,
            DbMemoHelper.COLUMN_HEARTS,
            DbMemoHelper.COLUMN_BONUS_HEARTS
    };


    public DbMemoDataSource(Context context) {
        Log.d(LOG_TAG, "Unsere DataSource erzeugt jetzt den dbHelper.");
        dbHelper = new DbMemoHelper(context);
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
    public DbMemo createDbMemo(String recipe, int hearts, int bonus) {
        ContentValues values = new ContentValues();
        values.put(DbMemoHelper.COLUMN_RECIPE, recipe);
        values.put(DbMemoHelper.COLUMN_HEARTS, hearts);
        values.put(DbMemoHelper.COLUMN_BONUS_HEARTS, bonus);

        long insertId = database.insert(DbMemoHelper.TABLE_RECIPE_LIST, null, values);

        Cursor cursor = database.query(DbMemoHelper.TABLE_RECIPE_LIST,
                columns, DbMemoHelper.COLUMN_ID + "=" + insertId,
                null, null, null, null);

        cursor.moveToFirst();
        DbMemo DbMemo = cursorToDbMemo(cursor);
        cursor.close();

        return DbMemo;
    }

    //Cursor in Daten-Objekt
    private DbMemo cursorToDbMemo(Cursor cursor) {
        int idIndex = cursor.getColumnIndex(DbMemoHelper.COLUMN_ID);
        int idRecipe = cursor.getColumnIndex(DbMemoHelper.COLUMN_RECIPE);
        int idHearts = cursor.getColumnIndex(DbMemoHelper.COLUMN_HEARTS);
        int idBonus = cursor.getColumnIndex(DbMemoHelper.COLUMN_BONUS_HEARTS);

        String recipe = cursor.getString(idRecipe);
        int hearts = cursor.getInt(idHearts);
        int bonus = cursor.getInt(idBonus);
        long id = cursor.getLong(idIndex);

        DbMemo DbMemo = new DbMemo(recipe, hearts, bonus, id);

        return DbMemo;
    }

    // Alle Datensätze auslesen
    public List<DbMemo> getAllDbMemos() {
        List<DbMemo> DbMemoList = new ArrayList<>();

        Cursor cursor = database.query(DbMemoHelper.TABLE_RECIPE_LIST,
                columns, null, null, null, null, null);

        cursor.moveToFirst();
        DbMemo DbMemo;

        while(!cursor.isAfterLast()) {
            DbMemo = cursorToDbMemo(cursor);
            DbMemoList.add(DbMemo);
            Log.d(LOG_TAG, "ID: " + DbMemo.getId() + ", Inhalt: " + DbMemo.toString());
            cursor.moveToNext();
        }

        cursor.close();

        return DbMemoList;
    }
}