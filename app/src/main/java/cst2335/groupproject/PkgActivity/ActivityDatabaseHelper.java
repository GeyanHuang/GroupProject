package cst2335.groupproject.PkgActivity;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Geyan Huang on 2017-11-12.
 */

public class ActivityDatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "Activity.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_NAME = "Activity";
    public static final String COLUMN_ID = "ActivityID";
    public static final String COLUMN_MINUTE = "Minute";
    public static final String COLUMN_TYPE = "Type";
    public static final String COLUMN_DATE = "Date";
    public static final String COLUMN_TIME = "Time";
    public static final String COLUMN_COMMENT = "Comment";


    private SQLiteDatabase database;

    private static final String TABLE_CREATE = "CREATE TABLE " + TABLE_NAME + " (" +
            COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COLUMN_MINUTE + " TEXT, " +
            COLUMN_TYPE + " TEXT, " +
            COLUMN_DATE + " TEXT, " +
            COLUMN_TIME + " TEXT, " +
            COLUMN_COMMENT + " TEXT" +
            ")";

    public ActivityDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public void openDatabase() {
        database = getWritableDatabase();
    }

    public void closeDatabase() {
        if (database != null && database.isOpen()) {
            database.close();
        }
    }

    public long insert(String minute, String type, String date, String time, String comment) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_MINUTE, minute);
        values.put(COLUMN_TYPE, type);
        values.put(COLUMN_DATE, date);
        values.put(COLUMN_TIME, time);
        values.put(COLUMN_COMMENT, comment);


        return database.insert(TABLE_NAME, null, values);

    }

    public void deleteItem(String id) {
        getWritableDatabase().execSQL("DELETE FROM " + TABLE_NAME + " WHERE " + COLUMN_ID + " = " + id);
    }

    public long update(String id, String minute, String type, String date, String time, String comment) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_MINUTE, minute);
        values.put(COLUMN_TYPE, type);
        values.put(COLUMN_DATE, date);
        values.put(COLUMN_TIME, time);
        values.put(COLUMN_COMMENT, comment);


        return database.update(TABLE_NAME, values, COLUMN_ID + " = " + id, null);
    }

    public Cursor getAllRecords() {
        return database.query(TABLE_NAME, null, null, null, null, null, null);
    }
}
