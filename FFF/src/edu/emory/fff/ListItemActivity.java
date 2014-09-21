package edu.emory.fff;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class ListItemActivity extends Activity {
	Intent intent = getIntent();

	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		System.out.println("Safoora: ListItem" + this);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list_item);
		Button addToCalBtn = (Button) findViewById(R.id.addToCal);
}
}