package edu.emory.fff;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

public class EditEvent extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_event);
		
		Intent intent = getIntent();
		
		 EditText date = (EditText) findViewById(R.id.date);
		 date.setText(intent.getStringExtra("date"));
		 
		 EditText location = (EditText) findViewById(R.id.location);
		 location.setText(intent.getStringExtra("location"));
		 
		 EditText body = (EditText) findViewById(R.id.body);
		 body.setText(intent.getStringExtra("body"));
		
	}
	public boolean onCreateOptionsMenu(Menu menu) {
        
		
		new MenuInflater(this).inflate(R.menu.menu, menu);

//	    return(super.onCreateOptionsMenu(menu));
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.menu, menu);
	    return true;
	}
}
