package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

/**
 * 
 * @author Strahinja
 *
 */
@SuppressWarnings("serial")
public class View extends JFrame {
	
	private JTextArea textAreaMessages = new JTextArea();
	private JTextField textFieldMessage = new JTextField();
	private JTextField textFieldName = new JTextField();
	
	private JButton btnSend = new JButton("Send");
	private JButton btnRename = new JButton("Rename");
	
	private JScrollPane scrollPaneClients = new JScrollPane();
	private JScrollPane scrollPaneMessages = new JScrollPane();
	
	private JList<String> listClients;
	private DefaultListModel<String> listModel = new DefaultListModel<String>();
	
	private JPanel topPanel = new JPanel();
	private JPanel leftPanel = new JPanel();
	private JPanel rightPanel = new JPanel();
	private JPanel bottomPanel = new JPanel();
	
	public View() {
		
//		initialiseLookAndFeel();
		
		/**
		 * Core initialization
		 */
		this.setTitle("Private Chat");
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		int screenWidth = screenSize.width;
		int screenHeight = screenSize.height;
		
		this.setMinimumSize(new Dimension(screenWidth/5, screenHeight/5));
		this.setMaximumSize(new Dimension(screenWidth/2, screenHeight/2));
		this.setSize(screenWidth/3, screenHeight/3);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		/**
		 * Loading data
		 */
		
		listClients = new JList<>(listModel);
        
		
		/**
		 * Left panel setting up.
		 */
		scrollPaneMessages.add(textAreaMessages);
		leftPanel.setLayout(new BorderLayout());
		leftPanel.add(scrollPaneMessages, BorderLayout.CENTER);
		
		bottomPanel.setLayout(new BorderLayout());
		textFieldMessage.setSize(200, bottomPanel.getHeight());
		textFieldMessage.setToolTipText("Enter your message here");
		bottomPanel.add(textFieldMessage, BorderLayout.CENTER);
		bottomPanel.add(btnSend, BorderLayout.EAST);
		
		leftPanel.add(bottomPanel, BorderLayout.SOUTH);
		
		
		/**
		 * Right panel setting up.
		 */
		rightPanel.setLayout(new BorderLayout());
		topPanel.setLayout(new BorderLayout());
		textFieldMessage.setSize(topPanel.getWidth(), 50);
		textFieldName.setToolTipText("Change your name here");
		topPanel.add(textFieldName, BorderLayout.NORTH);
		topPanel.add(btnRename, BorderLayout.SOUTH);
		rightPanel.add(topPanel, BorderLayout.NORTH);
		/**
		 * Need to set listClients.
		 * 
		 */
		scrollPaneClients = new JScrollPane(listClients);
		rightPanel.add(scrollPaneClients, BorderLayout.CENTER);
		
		
		
		add(leftPanel, BorderLayout.CENTER);
		add(rightPanel, BorderLayout.EAST);
		
		this.setVisible(true);
	}
	
	public void refreshList(List<String> onlineClients){
		int[] selektovani = listClients.getSelectedIndices();
		listModel.removeAllElements();
		
		if(onlineClients==null || onlineClients.isEmpty())
			return;
		
		for (String string : onlineClients) {
			listModel.addElement(string);
		}
		
		
		if(selektovani!=null){
			listClients.setSelectedIndices(selektovani);
		}
	}
	
	public void refreshMessages(String message) {
		System.out.println("message");
		this.getTextAreaMessages().append(message + "\n");
	}
	
	public void initialiseLookAndFeel() {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
//			this.setDefaultLookAndFeelDecorated(true);
			SwingUtilities.updateComponentTreeUI(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	/*
	 *	***** GETTERS AND SETTERS ***** 
	 */

	public JTextArea getTextAreaMessages() {
		return textAreaMessages;
	}

	public void setTextAreaMessages(JTextArea textAreaMessages) {
		this.textAreaMessages = textAreaMessages;
	}

	public JTextField getTextFieldMessage() {
		return textFieldMessage;
	}

	public void setTextFieldMessage(JTextField textFieldMessage) {
		this.textFieldMessage = textFieldMessage;
	}

	public JTextField getTextFieldName() {
		return textFieldName;
	}

	public void setTextFieldName(JTextField textFieldName) {
		this.textFieldName = textFieldName;
	}

	public JButton getBtnSend() {
		return btnSend;
	}

	public void setBtnSend(JButton btnSend) {
		this.btnSend = btnSend;
	}

	public JButton getBtnRename() {
		return btnRename;
	}

	public void setBtnRename(JButton btnRename) {
		this.btnRename = btnRename;
	}

	public JScrollPane getScrollPaneClients() {
		return scrollPaneClients;
	}

	public void setScrollPaneClients(JScrollPane scrollPaneClients) {
		this.scrollPaneClients = scrollPaneClients;
	}

	public JScrollPane getScrollPaneMessages() {
		return scrollPaneMessages;
	}

	public void setScrollPaneMessages(JScrollPane scrollPaneMessages) {
		this.scrollPaneMessages = scrollPaneMessages;
	}

	public JList<String> getListClients() {
		return listClients;
	}

	public void setListClients(JList<String> listClients) {
		this.listClients = listClients;
	}

	public JPanel getTopPanel() {
		return topPanel;
	}

	public void setTopPanel(JPanel topPanel) {
		this.topPanel = topPanel;
	}

	public JPanel getLeftPanel() {
		return leftPanel;
	}

	public void setLeftPanel(JPanel leftPanel) {
		this.leftPanel = leftPanel;
	}

	public JPanel getRightPanel() {
		return rightPanel;
	}

	public void setRightPanel(JPanel rightPanel) {
		this.rightPanel = rightPanel;
	}

	public JPanel getBottomPanel() {
		return bottomPanel;
	}

	public void setBottomPanel(JPanel bottomPanel) {
		this.bottomPanel = bottomPanel;
	}

	public ListModel<String> getListModel() {
		return listModel;
	}

	public void setListModel(DefaultListModel<String> listModel) {
		this.listModel = listModel;
	}

	
}
