package client.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintWriter;

import javax.swing.JList;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import gui.View;

public class SendListener implements ActionListener {

	private PrintWriter pw;
	
	private JList<String> listClients;
	private JTextField textFieldMessage;
	private JTextArea textAreaMessages;
	
	public SendListener(View view, PrintWriter pw) {
		this.setListClients(view.getListClients());
		this.setTextFieldMessage(view.getTextFieldMessage());
		this.setTextAreaMessages(view.getTextAreaMessages());
		this.setPw(pw);
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if(getListClients().getSelectedValuesList().isEmpty()) {
			this.getTextAreaMessages().append("Morate da izaberete primaoca." + "\n");
		}
		else {
			this.getTextAreaMessages().append("Ja: " + this.getTextFieldMessage().getText() + "\n");
			
			for (String string : listClients.getSelectedValuesList()) {
				pw.println("sendMessage§"+string+"->"+textFieldMessage.getText());
			}
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


	public JTextArea getTextAreaMessages() {
		return textAreaMessages;
	}


	public void setTextAreaMessages(JTextArea textAreaMessages) {
		this.textAreaMessages = textAreaMessages;
	}

	
}
