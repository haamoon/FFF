package edu.emory.fff.database;

import android.database.sqlite.SQLiteDatabase;

public class SettingTable {
	  public static final String TABLE_SETTINGS = "settings";
	  public static final String COLUMN_ID = "_id";
	  public static final String COLUMN_VALUE = "value";
	  
	// Database creation sql statement
	  private static final String DATABASE_CREATE = "create table "
	      + TABLE_SETTINGS + "(" + COLUMN_ID
	      + " text not null primary key, " + COLUMN_VALUE
	      + " text not null);";

	  public static void onCreate(SQLiteDatabase database) {
		  database.execSQL(DATABASE_CREATE);	
	  }
	  
	  public static void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		  db.execSQL("DROP TABLE IF EXISTS " + TABLE_SETTINGS);
		  onCreate(db);
	  }
}
