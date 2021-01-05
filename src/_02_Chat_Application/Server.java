package _02_Chat_Application;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;

public class Server {
	private int port;
	
	private ServerSocket serverSocket;
	private Socket connection;
	
	ObjectOutputStream os;
	ObjectInputStream is;

	public void start() {
		try {
			serverSocket = new ServerSocket(port, 100);
			
			connection = serverSocket.accept();
		
			os = new ObjectOutputStream(connection.getOutputStream());
			is = new ObjectInputStream(connection.getInputStream());
			
			os.flush();
			
			while(connection.isConnected()) {
				try {
					
				}catch(Exception e) {
					
				}
			}
			
		}catch(Exception e){
			System.out.println("Connection Lost");
			System.exit(0);
		}
	}
	
}