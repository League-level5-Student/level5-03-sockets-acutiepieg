package _02_Chat_Application;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.ScrollPaneConstants;

public class Client {
	private String ip;
	private int port;
	
	Socket connection;
	
	ObjectOutputStream os;
	ObjectInputStream is;
	
	
	
	public Client (String ip, int port) {
		this.ip = ip;
		this.port = port;
	}
	
	public void start() {
		try {
			connection = new Socket(ip, port);
			os = new ObjectOutputStream(connection.getOutputStream());
			is = new ObjectInputStream(connection.getInputStream());
		} catch (Exception e) {
			System.out.println("Connection Lost");
		}
		
		while(connection.isConnected()) {
			try {
			
			} catch (Exception e) {
				// TODO Auto-generated catch block
				System.out.println("Connection Lost");
			}
		}
	}
	
}
