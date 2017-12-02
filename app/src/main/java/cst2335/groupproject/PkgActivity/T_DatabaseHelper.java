package cst2335.groupproject.PkgActivity;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * This class is used for creating and using activity tracker database
 *
 * @author Geyan Huang
 *
 */
public class T_DatabaseHelper extends SQLiteOpenHelper {

    /**
     * Database name
     */
    private static final String DATABASE_NAME = "Activity.db";

    /**
     * Database version
     */
    private static final int DATABASE_VERSION = 1;

    /**
     * Table Name
     */
    public static final String TABLE_NAME = "Activity";

    /**
     * Column ID
     */
    public static final String COLUMN_ID = "ActivityID";

    /**
     * Column minute
     */
    public static final String COLUMN_MINUTE = "Minute";

    /**
     * Column type
     */
    public static final String COLUMN_TYPE = "Type";

    /**
     * Column date
     */
    public static final String COLUMN_DATE = "Date";

    /**
     * Column time
     */
    public static final String COLUMN_TIME = "Time";

    /**
     * Column comment
     */
    public static final String COLUMN_COMMENT = "Comment";

    /**
     * Database
     */
    public SQLiteDatabase database;

    /**
     * Query for create database
     */
    private static final String TABLE_CREATE = "CREATE TABLE " + TABLE_NAME + " (" +
            COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COLUMN_MINUTE + " INTEGER, " +
            COLUMN_TYPE + " TEXT, " +
            COLUMN_DATE + " TEXT, " +
            COLUMN_TIME + " TEXT, " +
            COLUMN_COMMENT + " TEXT" +
            ")";

    /**
     * Constructor of database helper
     * @param context The context of current activity
     */
    public T_DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * Function for creating database
     * @param sqLiteDatabase The database
     */
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(TABLE_CREATE);
    }

    /**
     * Function when upgrade version of database, it will drop the table and recreate it (Clear the date)
     * @param sqLiteDatabase The database
     * @param i The old version number
     * @param i1 The new version number
     */
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    /**
     * Function for opening database
     */
    public void openDatabase() {
        database = getWritableDatabase();
    }

    /**
     * Function for close database
     */
    public void closeDatabase() {
        if (database != null && database.isOpen()) {
            database.close();
        }
    }

    /**
     * Function for inserting to database
     * @param minute The activity minute
     * @param type  The activity type
     * @param date The date
     * @param time The time
     * @param comment The comment
     */
    public void insert(String minute, String type, String date, String time, String comment) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_MINUTE, Integer.parseInt(minute));
        values.put(COLUMN_TYPE, type);
        values.put(COLUMN_DATE, date);
        values.put(COLUMN_TIME, time);
        values.put(COLUMN_COMMENT, comment);

        database.insert(TABLE_NAME, null, values);

    }

    /**
     * Function for deleting a row by ID
     * @param id The ID
     */
    public void delete(String id) {
        getWritableDatabase().execSQL("DELETE FROM " + TABLE_NAME + " WHERE " + COLUMN_ID + " = " + id);
    }

    /**
     * Function for updating database
     * @param id The id
     * @param minute The activity minute
     * @param type The activity type
     * @param date The date
     * @param time The time
     * @param comment The comment
     */
    public void update(String id, String minute, String type, String date, String time, String comment) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_MINUTE, Integer.parseInt(minute));
        values.put(COLUMN_TYPE, type);
        values.put(COLUMN_DATE, date);
        values.put(COLUMN_TIME, time);
        values.put(COLUMN_COMMENT, comment);

        database.update(TABLE_NAME, values, COLUMN_ID + " = " + id, null);
    }

    /**
     * Function for read data from database
     * @return The data for the table
     */
    public Cursor read() {
        return database.query(TABLE_NAME, null, null, null, null, null, null);
    }
}
