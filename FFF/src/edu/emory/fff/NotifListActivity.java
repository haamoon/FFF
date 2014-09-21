package edu.emory.fff;



import java.util.ArrayList;

import edu.emory.fff.parser.Event;
import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;

public class NotifListActivity extends ListActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_notif_list);
		
		ArrayList<Event> events = new ArrayList<Event>();
		for (int i = 0; i < 25; i++)
		{
			Event e = new Event();
			e.setBody("Event" + String.valueOf(i));
			events.add(e);
		}
		
		ArrayAdapter<Event> adapter = new ArrayAdapter<Event> (this, R.layout.activity_list_item, R.id.eventtxt, events);
		setListAdapter(adapter);
	}
}
