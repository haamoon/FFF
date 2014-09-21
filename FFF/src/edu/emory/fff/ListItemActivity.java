package edu.emory.fff;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class ListItemActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list_item);
	}
	public void addToCal()
	{
		System.out.println("Add to Cal");
	}
	public void dismissNotif()
	{
		System.out.println("Dismiss");
	}
}
