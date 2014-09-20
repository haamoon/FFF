package edu.emory.fff.parser;

import java.util.ArrayList;

import javax.mail.*;

public abstract class Parser {
	public abstract ArrayList<Event> parse(Message [] msgs);
	
}
