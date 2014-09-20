package edu.emory.fff.parser;

import java.util.ArrayList;
import java.util.Date;

public class Event {
	private String coordinater;
	private Date notification_date;
	
	private String title;
	private String host;
	private Date date;
	private String location;
	private String body;
	private ArrayList<Highlight> highlights;
	
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
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
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

class Highlight {
	private int startIndex;
	private int endIndex;
	
	public int getStartIndex() {
		return startIndex;
	}
	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}
	public int getEndIndex() {
		return endIndex;
	}
	public void setEndIndex(int endIndex) {
		this.endIndex = endIndex;
	}
	
}
