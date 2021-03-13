package _02_Chat_Application;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class ServerChatApp {
	private int port;
	
	private ServerSocket serverSocket;
	private Socket connection;

	
	ObjectOutputStream os;
	ObjectInputStream is;
	
	JPanel panel;

	public ServerChatApp(int port) {
		this.port = port;
	}
	
	public void start(JPanel sPanel) {
		try {
			panel = sPanel;
			
			serverSocket = new ServerSocket(port);
			
			connection = serverSocket.accept();
			newL("Client connected");
		
			os = new ObjectOutputStream(connection.getOutputStream());
			is = new ObjectInputStream(connection.getInputStream());
			
			os.flush();
			
			while(connection.isConnected()) {
				try {
					newL((String)is.readObject());
				}catch(Exception e) {
					newL("Connection Lost");
				}
			}
			
		}catch(Exception e){
			newL("Connection Lost");
			System.exit(0);
		}
	}
	
	public JLabel newL(String s) {
		JLabel j = new JLabel(s);
		panel.add(j);
		return j;
	}
	
	
}