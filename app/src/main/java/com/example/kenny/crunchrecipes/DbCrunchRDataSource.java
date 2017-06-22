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
    private DbCrunchRHelper dbCrunchRHelper;

    private String[] columns = {
            DbCrunchRHelper.COLUMN_ID_RECIPE,
            DbCrunchRHelper.COLUMN_RECIPE,
            DbCrunchRHelper.COLUMN_FAVO,
            DbCrunchRHelper.COLUMN_ING_1,
            DbCrunchRHelper.COLUMN_ING_2,
            DbCrunchRHelper.COLUMN_ING_3,
            DbCrunchRHelper.COLUMN_ING_4,
            DbCrunchRHelper.COLUMN_ING_5,
            DbCrunchRHelper.COLUMN_HEAL,
            DbCrunchRHelper.COLUMN_BUFF,
            DbCrunchRHelper.COLUMN_LEVEL,
            DbCrunchRHelper.COLUMN_TIME
    };


    public DbCrunchRDataSource(Context context) {
        Log.d(LOG_TAG, "Unsere DataSource erzeugt jetzt den dbHelper.");
        dbCrunchRHelper = new DbCrunchRHelper(context);
    }

    public void open() {
        Log.d(LOG_TAG, "Eine Referenz auf die Datenbank wird jetzt angefragt.");
        database = dbCrunchRHelper.getWritableDatabase();
        Log.d(LOG_TAG, "Datenbank-Referenz erhalten. Pfad zur Datenbank: " + database.getPath());
    }

    public void close() {
        dbCrunchRHelper.close();
        Log.d(LOG_TAG, "Datenbank mit Hilfe des DbHelpers geschlossen.");
    }

    // Datensätze einfügen Rezepte
    public DbCrunchR createDbRecipe(String recipe, int favo, String ing_1, String ing_2, String ing_3, String ing_4, String ing_5, int heal, int buff, int level, int time) {
        ContentValues values = new ContentValues();
        values.put(DbCrunchRHelper.COLUMN_RECIPE, recipe);
        values.put(DbCrunchRHelper.COLUMN_FAVO, favo);
        values.put(DbCrunchRHelper.COLUMN_ING_1, ing_1);
        values.put(DbCrunchRHelper.COLUMN_ING_2, ing_2);
        values.put(DbCrunchRHelper.COLUMN_ING_3, ing_3);
        values.put(DbCrunchRHelper.COLUMN_ING_4, ing_4);
        values.put(DbCrunchRHelper.COLUMN_ING_5, ing_5);
        values.put(DbCrunchRHelper.COLUMN_HEAL, heal);
        values.put(DbCrunchRHelper.COLUMN_BUFF, buff);
        values.put(DbCrunchRHelper.COLUMN_LEVEL, level);
        values.put(DbCrunchRHelper.COLUMN_TIME, time);

        long insertId = database.insert(DbCrunchRHelper.TABLE_RECIPE_LIST, null, values);

        Cursor cursor = database.query(DbCrunchRHelper.TABLE_RECIPE_LIST,
                columns, DbCrunchRHelper.COLUMN_ID_RECIPE + "=" + insertId,
                null, null, null, null);

        cursor.moveToFirst();
        return cursorToDbMemo(cursor);
    }

    //Cursor in Daten-Objekt
    private DbCrunchR cursorToDbMemo(Cursor cursor) {
        int idIndex = cursor.getColumnIndex(DbCrunchRHelper.COLUMN_ID_RECIPE);
        int idRecipe = cursor.getColumnIndex(DbCrunchRHelper.COLUMN_RECIPE);
        int idFavo = cursor.getColumnIndex(DbCrunchRHelper.COLUMN_FAVO);
        int idIng1 = cursor.getColumnIndex(DbCrunchRHelper.COLUMN_ING_1);
        int idIng2 = cursor.getColumnIndex(DbCrunchRHelper.COLUMN_ING_2);
        int idIng3 = cursor.getColumnIndex(DbCrunchRHelper.COLUMN_ING_3);
        int idIng4 = cursor.getColumnIndex(DbCrunchRHelper.COLUMN_ING_4);
        int idIng5 = cursor.getColumnIndex(DbCrunchRHelper.COLUMN_ING_5);
        int idHeal = cursor.getColumnIndex(DbCrunchRHelper.COLUMN_HEAL);
        int idBuff = cursor.getColumnIndex(DbCrunchRHelper.COLUMN_BUFF);
        int idLevel = cursor.getColumnIndex(DbCrunchRHelper.COLUMN_LEVEL);
        int idTime = cursor.getColumnIndex(DbCrunchRHelper.COLUMN_TIME);

        long id = cursor.getLong(idIndex);
        String recipe = cursor.getString(idRecipe);
        int favo = cursor.getInt(idFavo);
        String ing1 = cursor.getString(idIng1);
        String ing2 = cursor.getString(idIng2);
        String ing3 = cursor.getString(idIng3);
        String ing4 = cursor.getString(idIng4);
        String ing5 = cursor.getString(idIng5);
        int hearts = cursor.getInt(idHeal);
        int buff = cursor.getInt(idBuff);
        int level = cursor.getInt(idLevel);
        int time  = cursor.getInt(idTime);


        return new DbCrunchR(id,recipe,favo,ing1,ing2,ing3,ing4,ing5,buff,favo,level,time);
    }

    // Alle Datensätze auslesen
    public List<DbCrunchR> getAllRecipes() {
        List<DbCrunchR> list_recipe = new ArrayList<>();

        Cursor cursor = database.query(DbCrunchRHelper.TABLE_RECIPE_LIST,
                columns, null, null, null, null, null);

        cursor.moveToFirst();

        if(cursor.getCount()==0) return list_recipe;
        DbCrunchR recipes;

        while(cursor.isAfterLast() == false) {
            recipes = cursorToDbMemo(cursor);
            list_recipe.add(recipes);
            Log.d(LOG_TAG, "ID: " + recipes.getId() + ", Inhalt: " + recipes.toString());
            cursor.moveToNext();
        }

        cursor.close();

        return list_recipe;
    }


    // Datensätze einfügen Ingredients
    public DbCrunchR createDbItem(String item, String location) {
        ContentValues values = new ContentValues();
        values.put(DbCrunchRHelper.COLUMN_ITEM, item);
        values.put(DbCrunchRHelper.COLUMN_LOCATION, location);

        long insertId = database.insert(DbCrunchRHelper.TABLE_ITEM_LIST, null, values);

        Cursor cursor = database.query(DbCrunchRHelper.TABLE_ITEM_LIST,
                columns, DbCrunchRHelper.COLUMN_ID_RECIPE + "=" + insertId,
                null, null, null, null);

        cursor.moveToFirst();
        return cursorToDbMemo(cursor);
    }
}