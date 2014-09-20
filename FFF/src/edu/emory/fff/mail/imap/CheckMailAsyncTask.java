package edu.emory.fff.mail.imap;

import java.util.Properties;

import javax.mail.*;

import android.os.AsyncTask;


public class CheckMailAsyncTask extends AsyncTask<ImapSettings, Void, Message[]> {
    
	protected Message[] doInBackground(ImapSettings... imapProperties) {
		ImapSettings loginCredintial = imapProperties[0];
		System.out.println("Amirreza: background task started!");
		Store store = null;
		Message[] messages = null;
		try {
			Properties props = new Properties();
			props.setProperty("mail.store.protocol", "imaps");
			Session session = Session.getInstance(props, null);
	        store = session.getStore();
	        store.connect(loginCredintial.server, loginCredintial.user, loginCredintial.password);
	        Folder inbox = store.getFolder("INBOX");
	        inbox.open(Folder.READ_ONLY);
	        int newMessageCount = inbox.getNewMessageCount();
	        int messageCount = inbox.getMessageCount();
	        messages = inbox.getMessages(messageCount - Math.min(20, newMessageCount) + 1, messageCount);
	        System.out.println("Amirreza: " + newMessageCount);  
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
		return messages;
    }

    protected void onProgressUpdate() {
    }

    protected void onPostExecute(Long result) {
        
    }
}
