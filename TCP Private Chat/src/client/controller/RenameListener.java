package client.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintWriter;

import javax.swing.JTextField;

public class RenameListener implements ActionListener {

	private String name;
	private JTextField jtf;
	private PrintWriter pw;
	
	public RenameListener(JTextField jtf, PrintWriter pw) {
		this.jtf = jtf;
		this.name = jtf.getText();
		this.pw = pw;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		setName(this.getJtf().getText());
		pw.println("rename§"+this.getName());
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public JTextField getJtf() {
		return jtf;
	}

	public void setJtf(JTextField jtf) {
		this.jtf = jtf;
	}

	public PrintWriter getPw() {
		return pw;
	}

	public void setPw(PrintWriter pw) {
		this.pw = pw;
	}

}
