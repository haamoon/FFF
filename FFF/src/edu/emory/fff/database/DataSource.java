package edu.emory.fff.database;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import edu.emory.fff.parser.Event;
import edu.emory.fff.parser.Highlight;
import edu.emory.fff.util.ConverDateFormat;

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
		  try {
			event.setDate(ConverDateFormat.convertToCalendar(cursor.getString(3)));
		  } catch (ParseException e) {
			  e.printStackTrace();
		  }
		  event.setLocation(cursor.getString(4));
		  event.setBody(cursor.getString(5));
		  event.setCoordinater(cursor.getString(6));
		  event.setNotification_date(null);
		  
		  ArrayList<Highlight> highlights = getHighlights(id);
		  event.setHighlights(highlights);
		  
		  return event;
	  }
	  
	  private ArrayList<Highlight> getHighlights(int id)
	  {
		  Cursor cursor = database.query(HighlightTable.TABLE_HIGHLIGHT, HighlightTable.ALL_COLUMNS, HighlightTable.COLUMN_ID + " = " + id, null, null, null, null);
		  ArrayList<Highlight> lights = new ArrayList<Highlight>();
		  cursor.moveToFirst();
		  int count = cursor.getCount();
		  for(int i = 0; i < count; i++) {
		      Highlight light = new Highlight();
		      light.setStartIndex(cursor.getInt(1));
		      light.setEndIndex(cursor.getInt(2));
		      cursor.moveToNext();
		  }
		  return lights;
	  }
	  
	  public void deleteEvent(Event e)
	  {
		  long id = e.getID(); 
		  database.delete(EventTable.TABLE_EVENTS, EventTable.COLUMN_ID
			        + " = " + id, null);
		  database.delete(HighlightTable.TABLE_HIGHLIGHT, HighlightTable.COLUMN_ID
			        + " = " + id, null);
	  }
	   
	  public void addEvent(Event[] events)
	  {
		  if(events == null)
		  {
			  return;
		  }
		  
		  for(Event event: events)
		  {
			  ContentValues values = new ContentValues();
			  values.put(EventTable.COLUMN_TITLE, event.getTitle());
			  values.put(EventTable.COLUMN_HOST, event.getHost());
			  values.put(EventTable.COLUMN_DATE, ConverDateFormat.convertToString(event.getDate()));
			  values.put(EventTable.COLUMN_LOC, event.getLocation());
			  values.put(EventTable.COLUMN_BODY, event.getBody());
			  values.put(EventTable.COLUMN_COOR, event.getCoordinater());
			  values.put(EventTable.COLUMN_NOTE_DATE, (String)null);
			  
			  long insertId = database.insert(EventTable.TABLE_EVENTS, null, values);
			  event.setID(insertId);
//			  Cursor cursor = database.query(EventTable.TABLE_EVENTS,
//					  EventTable.ALL_COLUMNS, EventTable.COLUMN_ID + " = " + insertId, null,
//					  null, null, null);
//			  cursor.moveToFirst();
//			  Event newEvent = cursorToEvent(cursor);
//			  cursor.close();
			  
			  this.addHighlights(event.getHighlights(), insertId);
		  }
	  }
	  
	  
	  private void addHighlights(ArrayList<Highlight> highLights, long id) {
		  if(highLights == null) {
			  return;
		  }
		  for(Highlight light: highLights) {
			  ContentValues values = new ContentValues();
			  values.put(HighlightTable.COLUMN_ID, id);
			  values.put(HighlightTable.COLUMN_START, light.getStartIndex());
			  values.put(HighlightTable.COLUMN_END, light.getEndIndex());
		  }
	  }
	  
}
