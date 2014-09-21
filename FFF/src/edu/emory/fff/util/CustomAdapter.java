package edu.emory.fff.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import edu.emory.fff.EditEvent;
import edu.emory.fff.MainActivity;
import edu.emory.fff.R;
import edu.emory.fff.parser.Event;

public class CustomAdapter extends ArrayAdapter<Event> implements OnClickListener {
    private final Context context;
    private final ArrayList<Event> events;
    private final int layoutResourceId;

    public CustomAdapter(Context context, int layoutResourceId, ArrayList<Event> events) {
        super(context, layoutResourceId, events);
        this.context = context;
        this.events = events;
        this.layoutResourceId = layoutResourceId;
    }

    @Override
    public View getView (int position, View convertView, ViewGroup parent) 
    {
        View row = convertView;
        ViewHolder holder = null;
        Event e = events.get(position);
        
        if(row == null)
        {
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);

            holder = new ViewHolder();
            holder.textView1 = (TextView)row.findViewById(R.id.eventtxt);
            holder.textView2 = (TextView)row.findViewById(R.id.date);
            holder.addToCal = (Button)row.findViewById(R.id.addToCal);
            holder.e = e;
            
            row.setOnClickListener(this);
            holder.addToCal.setOnClickListener(this);
             
            //holder.textView3 = (TextView)row.findViewById(R.id.text3);
            row.setTag(holder);
        }
        else
        {
            holder = (ViewHolder)row.getTag();
        }

        holder.textView1.setText(e.getTitle());
        holder.textView2.setText(e.getDate().toString());
        holder.e = e;

        holder.addToCal.setTag(new ButtonHolder(e.getDate(), e.getLocation(), e.getTitle()));
       
        return row;
    }

    static class ViewHolder
    {
        TextView textView1;
        TextView textView2;
        Button addToCal;
        Event e;
    }

    static class ButtonHolder
    {
    	public ButtonHolder(Calendar date, String location, String dectription)
    	{
    		this.date = date;
    		this.location = location;
    		this.description = dectription;
    	}
    	
    	public Calendar date;
    	public String location;
    	public String description;
    }
    
	@Override
	public void onClick(View v) 
	{
		System.out.println("Safoora: " + v);
		if(v instanceof Button)
		{
			System.out.println("Safoora: Salam");
			ButtonHolder bh = (ButtonHolder) v.getTag();
	    	Calendar cal = Calendar.getInstance();
	    	Intent intent = new Intent(Intent.ACTION_EDIT);
	    	intent.setType("vnd.android.cursor.item/event");
	    	//SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy hh:mm:ss"); // Month.Day.Year
	
			 	long timestamp = bh.date.getTimeInMillis();
			
			intent.putExtra("beginTime", timestamp);
	//		System.out.println("Date:"+getIntent().getStringExtra("date")+"***"+cal.getTimeInMillis());
			// TODO Auto-generated catch block
	    	intent.putExtra("allDay", false);
	    	intent.putExtra("endTime",cal.getTimeInMillis() + 60 * 60 * 1000);
	    	intent.putExtra("title","Free Food" );
	    	intent.putExtra("eventLocation",bh.location);
	    	this.context.startActivity(intent);
		}
		else
		{
			Event e = ((ViewHolder)v.getTag()).e;
			Intent intent = new Intent(this.context, EditEvent.class);
			intent.putExtra("date", e.getDate().toString());
			intent.putExtra("location", e.getLocation());
			intent.putExtra("body", e.getTitle());
			this.context.startActivity(intent);
		}
	}
}
