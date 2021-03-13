package _02_Chat_Application;

import java.awt.Panel;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.ScrollPaneConstants;

public class ClientChatApp {
	private String ip;
	private int port;
	JPanel panel;
	
	Socket connection;
	
	ObjectOutputStream os;
	ObjectInputStream is;
	
	
	
	public ClientChatApp (String ip, int port) {
		this.ip = ip;
		this.port = port;
	}
	
	public void start(JPanel clientpanel) {
		try {
			panel = clientpanel;
			connection = new Socket(ip, port);
			os = new ObjectOutputStream(connection.getOutputStream());
			is = new ObjectInputStream(connection.getInputStream());
			
		} catch (Exception e) {
			newL("Connection Lost");
		}
		
		while(connection.isConnected()) {
			try {
				newL((String)is.readObject());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				newL("Connection Lost");
			}
		}
	}
	
	public JLabel newL(String s) {
		JLabel j = new JLabel(s);
		panel.add(j);
		return j;
	}
}
