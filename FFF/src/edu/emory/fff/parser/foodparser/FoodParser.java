package edu.emory.fff.parser.foodparser;

import edu.emory.fff.parser.Event;
import edu.emory.fff.parser.Parser;

import java.util.ArrayList;
import javax.mail.Message;

public class FoodParser extends Parser {
	
   	String [] foodKeyWords = {"food", "dinner", "lunch", "pizza", "coffee",
			"tea", "cookie", "cake", "beer", "drink", "refreshment"};

	String [] freeKeyWords = {"free"};
	
	public FoodParser () {
		for (String keyWord: foodKeyWords)
			addMajorKeyWord(keyWord);
		for (String keyWord: freeKeyWords)
			addMinorKeyWord(keyWord);
	}
	
	@Override
	public ArrayList<Event> parse(Message[] msgs) {
		return super.parse(msgs);
	}

}
