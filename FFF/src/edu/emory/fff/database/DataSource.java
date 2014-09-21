package edu.emory.fff.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class DataSource {
	  // Database fields
	  private SQLiteDatabase database;
	  private MySQLiteHelper dbHelper;

	  
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
}
