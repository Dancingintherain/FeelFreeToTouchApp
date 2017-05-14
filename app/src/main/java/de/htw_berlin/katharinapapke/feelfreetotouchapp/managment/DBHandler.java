package de.htw_berlin.katharinapapke.feelfreetotouchapp.managment;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Berlina on 07.05.17.
 */

public class DBHandler extends SQLiteOpenHelper {

    private static final String LOG_TAG = DBHandler.class.getSimpleName();

    private static DBHandler sInstance = null;

    // Database Version
    private static final int DATABASE_VERSION = 3;
    // Database Name
    private static final String DATABASE_NAME = "feelfreetotouchDatabase";
    // Table name
    public static final String TABLE_COMMENTS = "comments";
    // Comments Table Columns names
    public static final String ID = "_id";
    public static final String KEY_COMMENT = "wholeComment";
    public static final String KEY_VISITOR_AGE = "age";
    //Create table query
    private static final String CREATE_TABLE_COMMENTS = "CREATE TABLE " + TABLE_COMMENTS
            + "("
            + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + KEY_COMMENT + " TEXT, "
            + KEY_VISITOR_AGE + " TEXT); ";

    //Singleton
    public static synchronized DBHandler getInstance(Context context) {

        if (sInstance == null) {
            sInstance = new DBHandler(context);
        }
        return sInstance;
    }

    private DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        Log.i(LOG_TAG, "DbHandler hat die Datenbank: " + getDatabaseName() + " erzeugt.");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            Log.d(LOG_TAG, "Die Tabelle wird angelegt: " + CREATE_TABLE_COMMENTS);
            db.execSQL(CREATE_TABLE_COMMENTS);
        }
        catch (Exception ex){
            Log.d(LOG_TAG, "Fehler beim Anlegen der Tabelle: " + ex.getMessage());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_COMMENTS);
        // Creating tables again
        onCreate(db);
    }
}
