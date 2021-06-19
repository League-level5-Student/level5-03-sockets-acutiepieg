package _02_Chat_Application;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ServerChatApp {
	private int port;

	private ServerSocket serverSocket;
	private Socket connection;

	DataOutputStream os;
	DataInputStream is;

	JPanel panel;
	JFrame frame;

	public ServerChatApp(int port) {
		this.port = port;
	}

	public void start(JPanel sPanel, JFrame serverFrame) {
		try {
			panel = sPanel;
			frame = serverFrame;

			serverSocket = new ServerSocket(port);

			connection = serverSocket.accept();
			newL("Client connected");

			os = new DataOutputStream(connection.getOutputStream());
			is = new DataInputStream(connection.getInputStream());

			while (connection.isConnected()) {
				System.out.println("while loop");
				try {
					String read = is.readUTF();
					newL(read);
				} catch (Exception e) {
					newL("Connection Lost");
				}
			}
			
			System.out.println("after while loop");
			os.flush();

		} catch (Exception e) {
			newL("Connection Lost");
			System.exit(0);
		}
	}

	public JLabel newL(String s) {
		System.out.println(s);
		JLabel j = new JLabel(s);
		panel.add(j);
		frame.pack();
		return j;
	}

}