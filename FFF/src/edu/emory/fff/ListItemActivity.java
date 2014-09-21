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
import android.widget.TextView;

public class ListItemActivity extends Activity {
	Intent intent = getIntent();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list_item);
	}
	public void addToCal()
	{
		TextView eventtxt = (TextView)findViewById(R.id.eventtxt);
		Calendar cal = Calendar.getInstance();
    	Intent intent = new Intent(Intent.ACTION_EDIT);
    	intent.setType("vnd.android.cursor.item/event");
    	SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy hh:mm:ss"); // Month.Day.Year

    	Date d = null;
    	try {
    		d = formatter.parse(eventtxt.getText().toString());
    	} catch (ParseException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	}
		 	long timestamp = d.getTime();
		
		intent.putExtra("beginTime", timestamp);
//		System.out.println("Date:"+getIntent().getStringExtra("date")+"***"+cal.getTimeInMillis());
		// TODO Auto-generated catch block
    	intent.putExtra("allDay", false);
    	intent.putExtra("endTime",cal.getTimeInMillis() + 60 * 60 * 1000);
    	intent.putExtra("title","Free Food" );
    	intent.putExtra("eventLocation", (String)findViewById(R.id.eventtxt).getTag());
    	startActivity(intent);

	}
	public void dismissNotif()
	{
		System.out.println("Dismiss");
	}
}
