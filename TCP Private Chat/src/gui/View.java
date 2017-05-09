package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import server.repository.ClientDao;

public class View extends JFrame {
	
	private JTextArea textAreaMessages = new JTextArea();
	private JTextField textFieldMessage = new JTextField();
	private JTextField textFieldName = new JTextField();
	
	private JButton btnSend = new JButton("Send");
	private JButton btnRename = new JButton("Rename");
	
	private JScrollPane scrollPaneClients = new JScrollPane();
	private JScrollPane scrollPaneMessages = new JScrollPane();
	
	private JList listClients;
	
	private JPanel topPanel = new JPanel();
	private JPanel leftPanel = new JPanel();
	private JPanel rightPanel = new JPanel();
	private JPanel bottomPanel = new JPanel();
	
	public View() {
		
		this.setTitle("Private Chat");
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		int screenWidth = screenSize.width;
		int screenHeight = screenSize.height;
		
		this.setSize(2*screenWidth/3, 2*screenHeight/3);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		/**
		 * Left panel setting up.
		 */
		scrollPaneMessages.add(textAreaMessages);
		leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
		leftPanel.add(scrollPaneMessages);
		
		bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.X_AXIS));
		textFieldMessage.setText("Enter your message here");
		bottomPanel.add(textFieldMessage);
		bottomPanel.add(btnSend);
		
		leftPanel.add(bottomPanel);
		
		
		/**
		 * Right panel setting up.
		 */
		rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
		topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.X_AXIS));
		topPanel.add(textFieldName);
		topPanel.add(btnRename);
		rightPanel.add(topPanel);
		/**
		 * Need to set listClients.
		 */
		listClients = new JList<>();
		scrollPaneClients.add(listClients);
		rightPanel.add(scrollPaneClients);
		
		
		
		add(leftPanel, BorderLayout.WEST);
		add(rightPanel, BorderLayout.EAST);
		
		this.setVisible(true);
	}
	
	
}
