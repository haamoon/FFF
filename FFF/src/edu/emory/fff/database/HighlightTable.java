package edu.emory.fff.database;

import android.database.sqlite.SQLiteDatabase;

public class HighlightTable {
	  public static final String TABLE_HIGHLIGHT = "settings";
	  public static final String COLUMN_ID = "id";
	  public static final String COLUMN_START = "start";
	  public static final String COLUMN_END = "end";
	  
	// Database creation sql statement
	  private static final String DATABASE_CREATE = "create table "
	      + TABLE_HIGHLIGHT + "(" + COLUMN_ID + " integer, " + 
			  COLUMN_START + " integer, " +
			  COLUMN_END + " text not null);";

	  public static void onCreate(SQLiteDatabase database) {
		  database.execSQL(DATABASE_CREATE);	
	  }
	  
	  public static void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		  db.execSQL("DROP TABLE IF EXISTS " + TABLE_HIGHLIGHT);
		  onCreate(db);
	  }

}
