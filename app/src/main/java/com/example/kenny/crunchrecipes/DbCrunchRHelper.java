package com.example.kenny.crunchrecipes;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DbCrunchRHelper extends SQLiteOpenHelper{
    private static final String LOG_TAG = DbCrunchRHelper.class.getSimpleName();
    public static final String DB_NAME = "crunch.db";
    public static final int DB_VERSION = 1;

    public static final String TABLE_RECIPE_LIST = "Recipes";

    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_RECIPE = "Name";
    public static final String COLUMN_HEARTS = "Hearts";
    public static final String COLUMN_BONUS_HEARTS = "BonusHearts";


    public static final String SQL_CREATE =
            "CREATE TABLE " + TABLE_RECIPE_LIST +
                    "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_RECIPE + " TEXT NOT NULL, " +
                    COLUMN_HEARTS + " INTEGER, " +
                    COLUMN_BONUS_HEARTS + " INTEGER);";



    public DbCrunchRHelper(Context context) {
        //super(context, "PLATZHALTER_DATENBANKNAME", null, 1);
        super(context, DB_NAME, null, DB_VERSION);
        Log.d(LOG_TAG, "DbHelper hat die Datenbank: " + getDatabaseName() + " erzeugt.");
        Log.d(LOG_TAG, "DbHelper hat die Datenbanktabelle: " + TABLE_RECIPE_LIST + " erzeugt.");
    }

    // Die onCreate-Methode wird nur aufgerufen, falls die Datenbank noch nicht existiert
    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            Log.d(LOG_TAG, "Die Tabelle wird mit SQL-Befehl: " + SQL_CREATE + " angelegt.");
            db.execSQL(SQL_CREATE);
        }
        catch (Exception ex) {
            Log.e(LOG_TAG, "Fehler beim Anlegen der Tabelle: " + ex.getMessage());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}