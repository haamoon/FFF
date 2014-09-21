package edu.emory.fff;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import edu.emory.fff.parser.Highlight;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class EditEvent extends Activity {
	
	EditText date;
	EditText location;
	
	
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_event);
		
		Intent intent = getIntent();
		
		 this.date = (EditText) findViewById(R.id.date);
		 this.date.setText(intent.getStringExtra("date"));
		 
		 
		 this.location = (EditText) findViewById(R.id.location);
		 this.location.setText(intent.getStringExtra("location"));
		 
		 TextView body = (TextView) findViewById(R.id.body);
		 body.setText(intent.getStringExtra("body"));
		 
//		 Spannable WordtoSpan = new SpannableString(intent.getStringExtra("body"));   
//		 WordtoSpan.setSpan(new BackgroundColorSpan(Color.YELLOW), 2, 6, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
//		 WordtoSpan.setSpan(new BackgroundColorSpan(Color.YELLOW), 10, 18, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
//		 body.setText(WordtoSpan);
		
	}
	public boolean onCreateOptionsMenu(Menu menu) {
        
		
//		new MenuInflater(this).inflate(R.menu.menu, menu);

//	    return(super.onCreateOptionsMenu(menu));
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.menu, menu);
	    return true;
	}
	
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle item selection
	    switch (item.getItemId()) {
	        case R.id.yes_add:
	        	Calendar cal = Calendar.getInstance();
	        	Intent intent = new Intent(Intent.ACTION_EDIT);
	        	intent.setType("vnd.android.cursor.item/event");
	        	SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy hh:mm:ss"); // Month.Day.Year
	        	Date d = null;
	        	try {
	        		d = formatter.parse(date.getText().toString());
	        	} catch (ParseException e) {
	        		// TODO Auto-generated catch block
	        		e.printStackTrace();
	        	}
	   		 	long timestamp = d.getTime();
	   		
				intent.putExtra("beginTime", timestamp);
	        	intent.putExtra("allDay", false);
	        	intent.putExtra("endTime",cal.getTimeInMillis() + 60 * 60 * 1000);
	        	intent.putExtra("title","Free Food" );
	        	intent.putExtra("eventLocation",location.getText().toString());
	        	startActivity(intent);
	        	
	        	Intent i = new Intent(this,NotifListActivity.class);
	        	startActivity(i);
	            
	            return true;
	        case R.id.no_forget:
	        	Intent j = new Intent(this,NotifListActivity.class);
	        	startActivity(j);
	            return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}
	
}
