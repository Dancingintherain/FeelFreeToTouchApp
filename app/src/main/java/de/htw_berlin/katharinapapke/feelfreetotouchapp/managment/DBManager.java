package de.htw_berlin.katharinapapke.feelfreetotouchapp.managment;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import de.htw_berlin.katharinapapke.feelfreetotouchapp.models.Comments;

/**
 * Created by Berlina on 08.05.17.
 */

public class DBManager {

    private static final String LOG_TAG = DBHandler.class.getSimpleName();

    private DBHandler dbHandler;
    private Context context;
    private SQLiteDatabase database;

    public DBManager(Context c) {
        context= c;
        Log.d(LOG_TAG, "Datenbank wird geöffnet/erstellt.");
        dbHandler = DBHandler.getInstance(context.getApplicationContext());
    }

    public DBManager open() throws SQLException {
        database = dbHandler.getWritableDatabase();
        Log.d(LOG_TAG, "Datenbank-Referenz erhalten. Pfad zur Datenbank: " + database.getPath());
        return this;
    }

    public void close() {
        dbHandler.close();
    }

    //CRUD database functions

    // Adding new comment
    public void insert(Comments comments) {
        ContentValues values = new ContentValues();
        // Comment
        values.put(DBHandler.KEY_COMMENT, comments.getComment());
        values.put(DBHandler.KEY_VISITOR_AGE, comments.getAge());
        // Inserting Row
        database.insert(DBHandler.TABLE_COMMENTS, null, values);
    }

    public Cursor fetch() {
        //gets all rows
        String[] columns = new String[] {DBHandler.ID, DBHandler.KEY_COMMENT, DBHandler.KEY_VISITOR_AGE };
        Cursor cursor = database.query(DBHandler.TABLE_COMMENTS, columns, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    //Updates comment
    public int update(Comments comment) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBHandler.KEY_COMMENT, comment.getComment());
        contentValues.put(DBHandler.KEY_VISITOR_AGE, comment.getAge());
        int i = database.update(DBHandler.TABLE_COMMENTS, contentValues, DBHandler.ID + " = " + comment.getId(), null);
        return i;
    }

    //Deletes comment
    public void delete(long _id) {
        database.delete(DBHandler.TABLE_COMMENTS, DBHandler.ID + "=" + _id, null);
    }

}

