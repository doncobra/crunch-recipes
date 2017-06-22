package com.example.kenny.crunchrecipes;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DbCrunchRHelper extends SQLiteOpenHelper{
    private static final String LOG_TAG = DbCrunchRHelper.class.getSimpleName();
    public static final String DB_NAME = "crunch.db";
    public static final int DB_VERSION = 1;

    public static final String TABLE_RECIPE_LIST = "Recipes";
    public static final String TABLE_ITEM_LIST = "Items";

    public static final String COLUMN_ID_RECIPE = "_id";
    public static final String COLUMN_RECIPE = "RecipeName";
    public static final String COLUMN_FAVO = "Favorite";
    public static final String COLUMN_ING_1 = "Ingredient_1";
    public static final String COLUMN_ING_2 = "Ingredient_2";
    public static final String COLUMN_ING_3 = "Ingredient_3";
    public static final String COLUMN_ING_4 = "Ingredient_4";
    public static final String COLUMN_ING_5 = "Ingredient_5";
    public static final String COLUMN_HEAL = "Hearts";
    public static final String COLUMN_BUFF = "Buff";
    public static final String COLUMN_LEVEL = "Level";
    public static final String COLUMN_TIME = "Time";

    public static final String COLUMN_ID_ITEM = "Id";
    public static final String COLUMN_ITEM = "ItemName";
    public static final String COLUMN_LOCATION = "Location";


    public static final String SQL_CREATE_RECIPE =
            "CREATE TABLE " + TABLE_RECIPE_LIST +
                    "(" + COLUMN_ID_RECIPE + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_RECIPE + " TEXT NOT NULL, " +
                    COLUMN_FAVO + " INTEGER, " +
                    COLUMN_ING_1 + " TEXT, " +
                    COLUMN_ING_2 + " TEXT, " +
                    COLUMN_ING_3 + " TEXT, " +
                    COLUMN_ING_4 + " TEXT, " +
                    COLUMN_ING_5 + " TEXT, " +
                    COLUMN_HEAL + " INTEGER, " +
                    COLUMN_BUFF + " INTEGER, " +
                    COLUMN_LEVEL + " INTEGER, " +
                    COLUMN_TIME + " INTEGER);";

    public static final String SQL_CREATE_ITEMS =
            "CREATE TABLE " + TABLE_ITEM_LIST +
                    "(" + COLUMN_ID_ITEM + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_ITEM + " TEXT NOT NULL, " +
                    COLUMN_LOCATION + " TEXT NOT NULL);";



    public DbCrunchRHelper(Context context) {
        //super(context, "PLATZHALTER_DATENBANKNAME", null, 1);
        super(context, DB_NAME, null, DB_VERSION);
        Log.d(LOG_TAG, "DbHelper hat die Datenbank: " + getDatabaseName() + " erzeugt.");
        Log.d(LOG_TAG, "DbHelper hat die Datenbanktabelle: " + TABLE_RECIPE_LIST + " erzeugt.");
        Log.d(LOG_TAG, "DbHelper hat die Datenbanktabelle: " + TABLE_ITEM_LIST + " erzeugt.");
    }

    // Die onCreate-Methode wird nur aufgerufen, falls die Datenbank noch nicht existiert
    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            Log.d(LOG_TAG, "Die Tabelle wird mit SQL-Befehl: " + SQL_CREATE_RECIPE + " angelegt.");
            Log.d(LOG_TAG, "Die Tabelle wird mit SQL-Befehl: " + SQL_CREATE_ITEMS + " angelegt.");
            db.execSQL(SQL_CREATE_RECIPE);
            db.execSQL(SQL_CREATE_ITEMS);

            db.execSQL("INSERT INTO " + TABLE_ITEM_LIST+ "(ItemName, Location ) VALUES ('Birne', 'Hyrule')");
            db.execSQL("INSERT INTO " + TABLE_ITEM_LIST+ "(ItemName, Location ) VALUES ('Apfel', 'Hyrule')");
            db.execSQL("INSERT INTO " + TABLE_ITEM_LIST+ "(ItemName, Location ) VALUES ('Pfote', 'Entenhausen')");
            db.execSQL("INSERT INTO " + TABLE_ITEM_LIST+ "(ItemName, Location ) VALUES ('Banana', 'Klappsmühle')");
            db.execSQL("INSERT INTO " + TABLE_ITEM_LIST+ "(ItemName, Location ) VALUES ('Wooot', 'Somewhere')");
        }
        catch (Exception ex) {
            Log.e(LOG_TAG, "Fehler beim Anlegen der Tabelle: " + ex.getMessage());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}