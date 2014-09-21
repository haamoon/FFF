package edu.emory.fff.util;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import edu.emory.fff.R;
import edu.emory.fff.parser.Event;

public class CustomAdapter extends ArrayAdapter<Event> {
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

        if(row == null)
        {
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);

            holder = new ViewHolder();
            holder.textView1 = (TextView)row.findViewById(R.id.eventtxt);
            holder.textView2 = (TextView)row.findViewById(R.id.date);
            holder.addToCal = (Button)row.findViewById(R.id.addToCal);
            //holder.textView3 = (TextView)row.findViewById(R.id.text3);
            row.setTag(holder);
        }
        else
        {
            holder = (ViewHolder)row.getTag();
        }

        Event e = events.get(position);
        holder.textView1.setText(e.getTitle());
        holder.textView1.setTag(e.getLocation());
        holder.textView2.setText(e.getDate());
       
        return row;
    }

    static class ViewHolder
    {
        TextView textView1;
        TextView textView2;
        Button addToCal;
    }
}
