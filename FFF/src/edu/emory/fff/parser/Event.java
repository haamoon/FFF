package edu.emory.fff.parser;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Event {
	private int id;
	private String coordinater;
	private Date notification_date;
	
	private String title;
	private String host;
	private Calendar date;
	private String location;
	private String body;
	private ArrayList<Highlight> highlights;
	
	public int getID() {
		return id;
	}
	
	public void setID(int id) {
		this.id = id;
	}
	
	public String getCoordinater() {
		return coordinater;
	}
	public void setCoordinater(String coordinater) {
		this.coordinater = coordinater;
	}
	public Date getNotification_date() {
		return notification_date;
	}
	public void setNotification_date(Date notification_date) {
		this.notification_date = notification_date;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public Calendar getDate() {
		return date;
	}
	public void setDate(Calendar date) {
		this.date = date;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public ArrayList<Highlight> getHighlights() {
		return highlights;
	}
	public void setHighlights(ArrayList<Highlight> highlights) {
		this.highlights = highlights;
	}
	
}
