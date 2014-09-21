package edu.emory.fff.parser;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.mail.*;

public abstract class Parser {
	protected ArrayList<String> majorKeyWords = new ArrayList<String>();
	protected ArrayList<String> minorKeyWords = new ArrayList<String>();
	
    protected String timePatternString = "\\d{1,2}\\s{0,2}(am|pm|([-|:|h| ]" +
    		"\\s{0,2}\\d{1,2}\\s{0,2})?)\\s{0,2}(-|to|till|,|untill)\\s{0,2}" +
    		"\\d{1,2}\\s{0,2}(am|pm|([-|:|h| ]\\s{0,2}\\d{1,2}\\s{0,2})?)";
    
	public String myJoin(ArrayList<String> words, String delim) {
		int n = words.size();
		String str = "";
		for (int i=0; i < n-1; i++) {
			str += words.get(i) + delim;
		}
		str += words.get(n-1);
		return str;
	}
	
	public void addMajorKeyWord(String keyWord) {
		majorKeyWords.add(keyWord);
	}
	
	public void addMinorKeyWord(String keyWord) {
		minorKeyWords.add(keyWord);
	}
	
	public ArrayList<Event> parse(Message [] msgs) {
		String foodPatternString = "\\b(" + myJoin(majorKeyWords, "|") + ")\\b";
	    Pattern foodPattern = Pattern.compile(foodPatternString);

		String freePatternString = "\\b(" + myJoin(minorKeyWords, "|") + ")\\b";
	    Pattern freePattern = Pattern.compile(freePatternString);
	    
	    Pattern timePattern = Pattern.compile(timePatternString);
	    
		ArrayList<Event> events = new ArrayList<Event>();
		try {
	        for (Message msg:msgs) {
	            Address[] in = msg.getFrom();
                for (Address address : in) {
                    System.out.println("FROM:" + address.toString());
                }
                System.out.println("SENT DATE:" + msg.getSentDate());
                System.out.println("SUBJECT:" + msg.getSubject());
                
	            String contentType = msg.getContentType();
	            String content = "content";
	            System.out.println(contentType);
	            System.out.println(msg.getContent().getClass().getName());
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

	                System.out.println("CONTENT:" + content);
	            }
	        }
	    } catch (Exception mex) {
	        mex.printStackTrace();
	    }
    
		return events;
	}
	
}
