package edu.emory.fff.mail.imap;

public class ImapSettings {
	
	public ImapSettings(String server, String user, String password, int port) {
		//Safoora test :)
		this.port = port;
		this.server = server;
		this.user = user;
		this.password = password;
	}
	public int port;
	public String server;
	public String user;
	public String password;
}
