package client.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.swing.JList;
import javax.swing.JTextField;

public class SendListener implements ActionListener {

	private PrintWriter pw;
	
	private JList<String> listClients;
	private JTextField textFieldMessage;
	
	public SendListener(JTextField jtf, PrintWriter pw, JList<String> lc) {
		this.setListClients(lc);
		this.setTextFieldMessage(jtf);
		this.setPw(pw);
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		for (String string : listClients.getSelectedValuesList()) {
			pw.println("sendMessage§"+string+"->"+textFieldMessage.getText());
		}
		
	}
	

	public PrintWriter getPw() {
		return pw;
	}

	public void setPw(PrintWriter pw) {
		this.pw = pw;
	}

	public JList<String> getListClients() {
		return listClients;
	}

	public void setListClients(JList<String> listClients) {
		this.listClients = listClients;
	}

	public JTextField getTextFieldMessage() {
		return textFieldMessage;
	}

	public void setTextFieldMessage(JTextField textFieldMessage) {
		this.textFieldMessage = textFieldMessage;
	}

	
}
