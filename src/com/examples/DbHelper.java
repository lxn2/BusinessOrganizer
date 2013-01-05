package com.examples;

import java.util.Date;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper{
	public static final int DATABASE_VERSION = 1;
	public static final String DATABASE_NAME = "Business.db";
	private static final String TEXT_TYPE = "TEXT";
	private static final String REAL_TYPE = "REAL";
	private static final String NUMERIC_TYPE = "NUMERIC";
	private static final String COMMA_SEP = ",";
	private Date date;

	//private static final String SQL_DELETE_ENTRIES =
	  //  "DROP TABLE IF EXISTS " + TABLE_NAME_ENTRIES;

	public DbHelper(Context context, Date date)
	{
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		this.date = date;
	}
	
	public void onCreate(SQLiteDatabase db) {
		String SQL_CREATE_ENTRIES =
			    "CREATE TABLE " + BusinessContract.Appointments.TABLE_NAME + date.toString() + " (" +
			    BusinessContract.Appointments._ID + " INTEGER PRIMARY KEY," +
			    BusinessContract.Appointments.COLUMN_NAME_FIRST + TEXT_TYPE + COMMA_SEP +
			    BusinessContract.Appointments.COLUMN_NAME_LAST + TEXT_TYPE + COMMA_SEP +
			    BusinessContract.Appointments.COLUMN_NAME_EMPLOYEE + TEXT_TYPE + COMMA_SEP +
			    BusinessContract.Appointments.COLUMN_NAME_SERVICES + TEXT_TYPE + COMMA_SEP +
			    BusinessContract.Appointments.COLUMN_NAME_CHARGE + REAL_TYPE + COMMA_SEP +
			    BusinessContract.Appointments.COLUMN_NAME_TIME + NUMERIC_TYPE + COMMA_SEP +
			    " )";
        db.execSQL(SQL_CREATE_ENTRIES);
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        //db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}
