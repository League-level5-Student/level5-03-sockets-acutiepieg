package _02_Chat_Application;

import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.ScrollPaneConstants;

import _00_Click_Chat.networking.Client;
import _00_Click_Chat.networking.Server;

/*
 * Using the Click_Chat example, write an application that allows a server computer to chat with a client computer.
 */

public class ChatApp extends JFrame {
	ServerChatApp server;
	ClientChatApp client;

	public static void main(String[] args) {
		new ChatApp();
	}

	public ChatApp() {
		int response = JOptionPane.showConfirmDialog(null, "Would you like to host a connection?", null,
				JOptionPane.YES_NO_OPTION);
		if (response == JOptionPane.YES_OPTION) {
			server = new ServerChatApp(8080);
			setTitle("Server");
			System.out.println("Server started");
			setVisible(true);
			setSize(300, 400);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

			JPanel serverPanel = new JPanel();
			add(serverPanel);

			JLabel sInitial = new JLabel("client window");
			serverPanel.add(sInitial);

			this.server.start(serverPanel);

		} else if (response == JOptionPane.NO_OPTION) {
			setTitle("Client");
			String ipString = null;
			try {
				ipString = (InetAddress.getLocalHost().getHostAddress());
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String portString = JOptionPane.showInputDialog("Enter Port Number");
			if (portString != null) {
				int portNum = Integer.parseInt(portString);
				client = new ClientChatApp(ipString, portNum);
			} else {
				System.exit(0);
			}

			JPanel clientPanel = new JPanel();
			add(clientPanel);

			JLabel initial = new JLabel("client window");
			clientPanel.add(initial);

			setVisible(true);
			setSize(300, 400);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

			JTextPane pane = new JTextPane();
			JScrollPane scroll = new JScrollPane(pane, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
					ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			add(pane);
			add(scroll);

			this.client.start(clientPanel);
		} else {
			System.exit(0);
		}
		setVisible(true);
		setSize(500, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
