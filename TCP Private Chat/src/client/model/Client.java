package client.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import client.controller.RenameListener;
import client.controller.SendListener;
import client.gui.View;

/**
 * Client's class. Used for communication with other clients and server.
 * @author Strahinja
 *
 */
public class Client implements Comparable<Client> {

	private Socket socket;
	
	private int port = 2017 + 29 + 4;
	private String address = "127.0.0.1";
	
	private BufferedReader in_socket;
	private PrintWriter out_socket;
	
	private String clientMessage = "a";
	
	private String clientName;
	
	private View view;
	
	public Client() throws UnknownHostException, IOException {
		view = new View();
		socket = new Socket(address, port);
		
		/*
		 * Initialization of I/O connectors.  
		 */
		in_socket = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		out_socket = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
		
	
		//TODO: Client should type his name first.
		//TODO: Client reads server's welcome message.

		RenameListener renameListener = new RenameListener(view.getTextFieldName(), out_socket);
		view.getBtnRename().addActionListener(renameListener);
		
		SendListener sendListener = new SendListener(view, out_socket);
		view.getBtnSend().addActionListener(sendListener);
		

		ScheduledExecutorService exec2 = Executors.newSingleThreadScheduledExecutor();
		exec2.scheduleAtFixedRate(new Runnable() {
		  @Override
		  public void run() {
				out_socket.println("whoIsOnline§");
		  }
		}, 0, 1, TimeUnit.SECONDS);
		
		ScheduledExecutorService exec = Executors.newSingleThreadScheduledExecutor();
		exec.scheduleAtFixedRate(new Runnable() {
		  @Override
		  public void run() {
				try {
					String response = in_socket.readLine();
					while(response != null) {
						if(response.startsWith("message§")){
							String message = response.split("§")[1];
							message += ": ";
							message += response.split("§")[2];
							if(message != null) {
								view.refreshMessages(message);
							}
						} else if (response.startsWith("whoIsOnline§")){
							view.refreshList(new ArrayList<>(Arrays.asList(response.split("whoIsOnline§")[1].split(";"))));
						} else if (response.startsWith("help§")){
							String message = response.split("help§")[1];
							if(message != null) {
								view.refreshMessages(message);
							}
						}
						response = in_socket.readLine();
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		  }
		}, 0, 1, TimeUnit.SECONDS);
		
		while(true) {
						
			if(clientMessage.equalsIgnoreCase("!quit")) {
				break;
			}
			
		}
		
		
		
		closeConnections();
	}
	
	private void closeConnections() throws IOException {
		socket.close();
		in_socket.close();
		out_socket.close();
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	@Override
	public int compareTo(Client o) {
		// TODO Auto-generated method stub
		if(this.getClientName().equals(o.getClientName())) {
			return 1;
		}
		return 0;
	}
	
	
	public static void main(String[] args) throws UnknownHostException, IOException {
		new Client();
	}
	
}
