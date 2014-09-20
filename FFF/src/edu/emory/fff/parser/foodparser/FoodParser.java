package edu.emory.fff.parser.foodparser;

import edu.emory.fff.parser.Event;
import edu.emory.fff.parser.Parser;

import java.util.ArrayList;

import javax.mail.Message;

public class FoodParser extends Parser {

	@Override
	public ArrayList<Event> parse(Message[] msgs) {
		Event evt = new FoodEvent();
		evt.setCoordinater("Bar o Bachz");
		ArrayList<Event> events = new ArrayList<Event>();
		events.add(evt);
		return events;
	}

}
