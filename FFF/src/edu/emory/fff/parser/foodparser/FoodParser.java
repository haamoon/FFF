package edu.emory.fff.parser.foodparser;

import edu.emory.fff.parser.Event;
import edu.emory.fff.parser.Parser;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;

public class FoodParser extends Parser {
	String [] freeKeyWords = {"free", "grab"};
	
	public String myJoin(String [] words, String delim) {
		int n = words.length;
		String str = "";
		for (int i=0; i < n-1; i++) {
			str += words[i] + delim;
		}
		str += words[n-1];
		return str;
	}
    

	@Override
	public ArrayList<Event> parse(Message[] msgs) {
		
		String foodPatternString = "\\b(" + myJoin(keyWords, "|") + ")\\b";
	    Pattern foodPattern = Pattern.compile(foodPatternString);

		String freePatternString = "\\b(" + myJoin(freeKeyWords, "|") + ")\\b";
	    Pattern freePattern = Pattern.compile(freePatternString);
	    
	    Pattern timePattern = Pattern.compile(timePatternString);
	    
		ArrayList<Event> events = new ArrayList<Event>();
		try {
	        for (Message msg:msgs) {
	            Address[] in = msg.getFrom();
	
	            String contentType = msg.getContentType();
	            String content = "content";
	            System.out.println(contentType);
	            if (contentType.substring(0, 9).compareTo("multipart") == 0) {
	                Multipart mp = (Multipart) msg.getContent();                	
	                BodyPart bp = mp.getBodyPart(0);
	                content = bp.getContent().toString();
	            } else if (contentType.substring(0, 4).compareTo("TEXT") == 0) {
	            	content = (String) msg.getContent();
	            }
	            else {
	            	throw new Exception("strainge content type!");
	            }
	            content = content.toLowerCase();
	            Matcher foodMatcher = foodPattern.matcher(content);
	            Matcher freeMatcher = freePattern.matcher(content);
	            int step = 30;
	            while (foodMatcher.find()) {
	            	int startInd = foodMatcher.start();
	            	int endInd = foodMatcher.end();
	
	            	
	            	System.out.println(content.substring(startInd,endInd));
	                for (Address address : in) {
	                    System.out.println("FROM:" + address.toString());
	                }
	                System.out.println("SENT DATE:" + msg.getSentDate());
	                System.out.println("SUBJECT:" + msg.getSubject());
	                System.out.println("CONTENT:" + content);
	            }
	        }
	    } catch (Exception mex) {
	        mex.printStackTrace();
	    }
    
		return events;
	}

}
