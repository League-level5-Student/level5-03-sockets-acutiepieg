package _02_Chat_Application;

import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.ScrollPaneConstants;

import _00_Click_Chat.networking.Client;
import _00_Click_Chat.networking.Server;

/*
 * Using the Click_Chat example, write an application that allows a server computer to chat with a client computer.
 */

public class ChatApp extends JFrame {
	Server server;
	Client client;

	public static void main(String[] args) {
		new ChatApp();
	}

	public ChatApp() {
		int response = JOptionPane.showConfirmDialog(null, "Would you like to host a connection?", "Buttons!",
				JOptionPane.YES_NO_OPTION);
		if (response == JOptionPane.YES_OPTION) {
			server = new Server(8081);
			setTitle("Server");
			System.out.println("Server started");
			setVisible(true);
			setSize(300, 400);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
			this.server.start();

		}
		else {
			setTitle("Client");
			String ipString = null;
			try {
				ipString = (InetAddress.getLocalHost().getHostAddress());
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String portString = JOptionPane.showInputDialog("Enter Port Number");
			int portNum = Integer.parseInt(portString);
			client = new Client(ipString, portNum);	
			
			setVisible(true);
			setSize(300, 400);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
//			JTextPane pane = new JTextPane();
//			JScrollPane scroll = new JScrollPane(pane, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
//					ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED );
//		   add(pane);
//		   add(scroll);
			
			this.client.start();
		}
		setVisible(true);
		setSize(500,400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
