package _02_Chat_Application;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerChatApp {
	private int port;
	
	private ServerSocket serverSocket;
	private Socket connection;
	
	ObjectOutputStream os;
	ObjectInputStream is;

	public ServerChatApp(int port) {
		this.port = port;
	}
	
	public void start() {
		try {
			serverSocket = new ServerSocket(port);
			
			connection = serverSocket.accept();
			System.out.println("Client connected");
		
			os = new ObjectOutputStream(connection.getOutputStream());
			is = new ObjectInputStream(connection.getInputStream());
			
			os.flush();
			
			while(connection.isConnected()) {
				try {
					System.out.println(is.readObject());
				}catch(Exception e) {
					System.out.println("Connection Lost");
				}
			}
			
		}catch(Exception e){
			System.out.println("Connection Lost");
			System.exit(0);
		}
	}
	
}