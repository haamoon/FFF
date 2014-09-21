package edu.emory.fff.mail.imap;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.util.Vector;

import javax.mail.*;

import edu.emory.fff.database.DataSource;
import edu.emory.fff.parser.Event;
import edu.emory.fff.parser.Parser;

import android.os.AsyncTask;


public class CheckMailAsyncTask extends AsyncTask<Object, Void, Message[]> {
    
	private DataSource dataSource;
	
	public CheckMailAsyncTask(DataSource dataSource)
	{
		this.dataSource = dataSource;
	}
	
	private static final int MAX_EMAIL_NUMBER = 20;
	private static final String LAST_READ_TIME = "LastReadTime";
	
	protected Message[] doInBackground(Object... args) {
		
		ImapSettings loginCredintial = (ImapSettings)args[0];	
		Vector<Parser> parsers = (Vector<Parser>) args[1];
		Store store = null;
		Vector<Message> messages = new Vector<Message>();
		Vector<Event> events = new Vector<Event>();
		try {
			Properties props = new Properties();
			props.setProperty("mail.store.protocol", "imaps");
			Session session = Session.getInstance(props, null);
	        store = session.getStore();
	        store.connect(loginCredintial.server, loginCredintial.user, loginCredintial.password);
	        Folder inbox = store.getFolder("INBOX");
	        inbox.open(Folder.READ_ONLY);
	        
	        int messageCount = inbox.getMessageCount();
	        Calendar cal = Calendar.getInstance();
	       
	        long lastMilis = 0;
	        try {
	        	String lastMiliesSt = dataSource.getSetting(LAST_READ_TIME);
	        	lastMilis = Long.parseLong(lastMiliesSt);
	        }
	        catch(Exception e) {}
	        
	        long newLastMilis = lastMilis;
	        for(int i = 0; i < messageCount && i < MAX_EMAIL_NUMBER; i++)
	        {
	        	Message message = inbox.getMessage(messageCount);
	        	Date receivedDate = message.getReceivedDate();
	        	cal.setTime(receivedDate);
	        	long currMilis = cal.getTimeInMillis();
	        	if(newLastMilis < currMilis)
	        	{
	        		newLastMilis = currMilis;
	        	}
	        	
	        	if(cal.getTimeInMillis() >= lastMilis)
	        	{
	        		messages.add(message);
	        	}
	        	else
	        	{
	        		break;
	        	}
	        }
	        dataSource.updateSetting(LAST_READ_TIME, Long.toString(newLastMilis)); 
	        
	        //generate events
	        Message[] messageArray = messages.toArray(new Message[messages.size()]);
	        for(Parser parser : parsers)
	        {
	        	ArrayList<Event> newEvents = parser.parse(messageArray);
	        	events.addAll(newEvents);
	        }
	        inbox.close(false); 
		} catch (NoSuchProviderException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		if(store != null) {
		      try {
		        store.close();
		      } catch (MessagingException e) {}
		} 
		
		return null;
    }

    protected void onProgressUpdate() {
    }

    protected void onPostExecute(Long result) {
        
    }
}
