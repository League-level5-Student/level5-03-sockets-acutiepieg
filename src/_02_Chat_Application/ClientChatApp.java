package _02_Chat_Application;

import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.ScrollPaneConstants;

public class ClientChatApp implements KeyListener {
	private String ip;
	private int port;
	JPanel panel;
	JTextField field;
	JFrame frame;
	
	Socket connection;
	
	DataOutputStream os;
	DataInputStream is;
	
	
	
	public ClientChatApp (String ip, int port) {
		this.ip = ip;
		this.port = port;
		
		
	}
	
	public void start(JPanel clientpanel, JTextField textField, JFrame clientFrame) {
		try {
			panel = clientpanel;
			field = textField;
			frame = clientFrame;
			field.addKeyListener(this);
			connection = new Socket(ip, port);
			os = new DataOutputStream(connection.getOutputStream());
			is = new DataInputStream(connection.getInputStream());
			
		} catch (Exception e) {
			newL("Connection Lost");
		}
		
		while(connection.isConnected()) {
			try {
				newL((String)is.readUTF());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				newL("Connection Lost");
			}
		}
		System.out.println("after while loop");
	}
	
	public JLabel newL(String s) {
		System.out.println(s);
		JLabel j = new JLabel(s);
		panel.add(j);
		frame.pack();
		return j;
	}


	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		System.out.println("key pressed");
		try {
			os.writeUTF(e.getKeyChar() + "");
		} catch (IOException e1) {
			newL("Connection Lost");
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
