package edu.emory.fff.database;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import edu.emory.fff.parser.Event;
import edu.emory.fff.parser.Highlight;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class DataSource {
	  // Database fields
	  private SQLiteDatabase database;
	  private MySQLiteHelper dbHelper;

	  private static Context context;
	  private static DataSource instance;
	  public static DataSource getInstance(Context context) 
	  {
		  if(DataSource.instance == null)
		  {
			  if(context == null) {
				  throw new IllegalArgumentException("context and instance can not be null at the same time!");
			  }
			  DataSource.context = context;
			  DataSource.instance = new DataSource(DataSource.context);
		  }
		  return DataSource.instance;
	  }
	  
	  
	  
	  public DataSource(Context context) {
	    dbHelper = new MySQLiteHelper(context);
	  }

	  public void open() throws SQLException {
	    database = dbHelper.getWritableDatabase();
	  }

	  public void close() {
	    dbHelper.close();
	  }

	  public void updateSetting(String key, String value) {
		  System.out.println("Amirreza: updating key = " + key + " with value = " + value);
		  ContentValues contentValues = new ContentValues();
		  contentValues.put(SettingTable.COLUMN_ID, key);
		  if(database.update(SettingTable.TABLE_SETTINGS, contentValues, SettingTable.COLUMN_ID + " = ? ", new String[] { key } ) == 0)
		  {
			  contentValues.put(SettingTable.COLUMN_VALUE, value);
			  database.insert(SettingTable.TABLE_SETTINGS, null, contentValues);
		  }
	  }
	  
	  public String getSetting(String key) {
		  Cursor cursor = database.rawQuery( "select * from " + SettingTable.TABLE_SETTINGS + " where " + SettingTable.COLUMN_ID + "= ?", new String[] {key} );
		  if(cursor.getCount() > 0) {
			  cursor.moveToFirst();
			  return cursor.getString(1);
		  }
		  return null;
	  }
	  
	  
	  public List<Event> getEvents()
	  {
		  List<Event> events = new ArrayList<Event>();

		  Cursor cursor = database.query(EventTable.TABLE_EVENTS,
				  EventTable.ALL_COLUMNS, null, null, null, null, null);

		    cursor.moveToFirst();
		    while (!cursor.isAfterLast()) {
		      Event event = cursorToEvent(cursor);
		      events.add(event);
		      cursor.moveToNext();
		    }
		    // make sure to close the cursor
		    cursor.close();
		    return events;
	  }
	  	  
	  private Event cursorToEvent(Cursor cursor) {
		  Event event = new Event();
		  int id = cursor.getInt(0);
		  event.setID(id);
		  event.setTitle(cursor.getString(1));
		  event.setHost(cursor.getString(2)); 
		  //event.setDate(date);
		  event.setLocation(cursor.getString(4));
		  event.setBody(cursor.getString(5));
		  event.setCoordinater(cursor.getString(6));
		  //event.setNotification_date(cursor.getString(7));
		  
		  ArrayList<Highlight> highlights = getHighlights(id);
		  event.setHighlights(highlights);
		  
		  return event;
	  }
	  
	  private ArrayList<Highlight> getHighlights(int id)
	  {
		  return null;
	  }
	  
	  public void deleteEvent(Event e)
	  {
		  
	  }
	  
	  public void addEvent(Event[] events)
	  {
		  
	  }
	  
	  
}
