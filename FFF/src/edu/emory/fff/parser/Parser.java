package edu.emory.fff.parser;

import java.util.ArrayList;

import javax.mail.*;

public abstract class Parser {
    protected String timePatternString = "\\d{1,2}\\s{0,2}(am|pm|([-|:|h| ]" +
    		"\\s{0,2}\\d{1,2}\\s{0,2})?)\\s{0,2}(-|to|till|,|untill)\\s{0,2}" +
    		"\\d{1,2}\\s{0,2}(am|pm|([-|:|h| ]\\s{0,2}\\d{1,2}\\s{0,2})?)";
   	protected String [] keyWords = {"food", "dinner", "lunch", "pizza", "coffee",
			"tea", "cookie", "cake", "beer", "drink",};
	public abstract ArrayList<Event> parse(Message [] msgs);
	
}
