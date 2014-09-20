package edu.emory.fff.database;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MySQLiteHelper extends SQLiteOpenHelper {

  private static final String DATABASE_NAME = "commments.db";
  private static final int DATABASE_VERSION = 1;

  public MySQLiteHelper(Context context) {
    super(context, DATABASE_NAME, null, DATABASE_VERSION);
  }

  @Override
  public void onCreate(SQLiteDatabase database) {
	  SettingTable.onCreate(database);
  }

  @Override
  public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	  SettingTable.onUpgrade(db, oldVersion, newVersion);
	  onCreate(db);
  }

} 
