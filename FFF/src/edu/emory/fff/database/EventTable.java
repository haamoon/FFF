package edu.emory.fff.database;


import android.database.sqlite.SQLiteDatabase;

public class EventTable {
	  public static final String TABLE_EVENTS = "events";
	  
	  
	  public static final String COLUMN_ID = "_id";
	  public static final String COLUMN_TITLE = "title";
	  public static final String COLUMN_HOST = "host";
	  public static final String COLUMN_DATE = "date";
	  
	  public static final String COLUMN_LOC = "location";
	  public static final String COLUMN_BODY = "body";

	  public static final String COLUMN_COOR = "cordinator";
	  public static final String COLUMN_NOTE_DATE = "notificationdate";
	  
	  public static final String[] ALL_COLUMNS = new String[] {COLUMN_ID, COLUMN_TITLE, COLUMN_HOST,
		  COLUMN_DATE, COLUMN_LOC, COLUMN_BODY, COLUMN_COOR, COLUMN_NOTE_DATE
	  };
		
	// Database creation sql statement
	  private static final String DATABASE_CREATE = "create table "
	      + TABLE_EVENTS + "(" + COLUMN_ID + " integer primary key autoincrement, " + 
			  COLUMN_TITLE + " text, " + 
			  COLUMN_HOST + " text, " +
			  COLUMN_DATE + " text, " +
			  COLUMN_LOC + " text, " +
			  COLUMN_BODY + " text, " +
			  COLUMN_COOR + " text, " +
			  COLUMN_NOTE_DATE + " text);";

	  public static void onCreate(SQLiteDatabase database) {
		  database.execSQL(DATABASE_CREATE);	
	  }
	  
	  public static void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		  db.execSQL("DROP TABLE IF EXISTS " + TABLE_EVENTS);
		  onCreate(db);
	  }

}
