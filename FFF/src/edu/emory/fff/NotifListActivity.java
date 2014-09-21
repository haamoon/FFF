package edu.emory.fff;



import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


import edu.emory.fff.parser.Event;
import edu.emory.fff.util.CustomAdapter;
import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;

public class NotifListActivity extends ListActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_notif_list);
		
		ArrayList<Event> events = new ArrayList<Event>();
		for (int i = 21; i < 26; i++)
		{
			Event e = new Event();
			e.setTitle("Event" + String.valueOf(i));
			//MM-dd-yyyy hh:mm:ss
			e.setDate(te(ate("11-" + String.valueOf(i) + "-2014"));
			events.add(e);
		}
		
		ArrayAdapter<Event> adapter = new CustomAdapter(this, R.layout.activity_list_item, events);//<Event> (this, R.layout.activity_list_item, events);
		setListAdapter(adapter);
	}
}
