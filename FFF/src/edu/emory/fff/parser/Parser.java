package edu.emory.fff.parser;

import java.util.ArrayList;

import javax.mail.*;

public abstract class Parser {
	public abstract ArrayList<Event> parse(Message [] msgs);
	
	public Event parse(Message msg) {return null;}
	
}
