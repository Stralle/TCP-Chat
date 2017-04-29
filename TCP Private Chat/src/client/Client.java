package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

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
	private Scanner scanner;
	
	private String serverMessage = "You succesfully connected to the server.";
	private String clientMessage;
	
	private String clientName;
	
	public Client() throws UnknownHostException, IOException {
		socket = new Socket(address, port);
		System.out.println(serverMessage);
		
		/*
		 * Initialization of I/O connectors.  
		 */
		in_socket = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		out_socket = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
		scanner = new Scanner(System.in);
		
	
		//TODO: Client should type his name first.
		serverMessage = "[S]: Please type your name:";
		System.out.println(serverMessage);
		clientMessage = scanner.nextLine();
		this.setClientName(clientMessage);
			//register client with selected name
		out_socket.println("register§"+clientMessage);
		
		//TODO: Client reads server's welcome message.
		
		serverMessage = in_socket.readLine();
		System.out.println("[S]: " + serverMessage);

		
//		while(true) {
//			
//			
//			
//			if(clientMessage.equalsIgnoreCase("!quit")) {
//				break;
//			}
//			
//			
//			
//		}
		
		
		
		closeConnections();
	}
	
	private void closeConnections() throws IOException {
		System.out.println("You have successfully disconnected from the server. Bye!");
		socket.close();
		in_socket.close();
		out_socket.close();
		scanner.close();
	}
	
	public static void main(String[] args) throws UnknownHostException, IOException {
		new Client();
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
}
