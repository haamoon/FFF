package edu.emory.fff;



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
		for (int i = 0; i < 6; i++)
		{
			Event e = new Event();
			e.setTitle("Event" + String.valueOf(i));
			e.setDate(new Date(20, 0, i));
			events.add(e);
		}
		
		ArrayAdapter<Event> adapter = new CustomAdapter(this, R.layout.activity_list_item, events);//<Event> (this, R.layout.activity_list_item, events);
		setListAdapter(adapter);
	}
}
