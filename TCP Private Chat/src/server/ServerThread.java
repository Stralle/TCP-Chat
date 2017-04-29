package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * 
 * @author Strahinja
 *
 */
public class ServerThread implements Runnable {

	private Socket socket;
	private ServerMain server;
	
	private BufferedReader in_socket;
	private PrintWriter out_socket;
	
	private String serverMessage;
	private String clientMessage;
	private String helpMessage =  "1. To quit from this chat type: \'!quit\'.\n"
								+ "2. To send a message to another client type \'!clientName: message\'.\n"
								+ "3. To check who is active type \'!active\'.\n";
	
	public ServerThread(Socket socket, ServerMain server) {
		// TODO Auto-generated constructor stub
		this.setSocket(socket);
		this.setServer(server);
	}


	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			in_socket = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out_socket = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
			
			//TODO: Server receives a client's name first.
			
			System.out.println("Client " + socket.getInetAddress().getHostAddress() + " is now connected.");
			
			clientMessage = in_socket.readLine();
			System.out.println("Client named \'" + clientMessage + "\' is now connected.");
			
			serverMessage = "Welcome " + clientMessage + " to our chat. For help type: \'!help\'";
			out_socket.println(serverMessage);
		
			
//			while(true) {
//				
//				
//				
//				if(clientMessage.equalsIgnoreCase("!quit")) {
//					break;
//				}
//				
//				
//				
//			}
			
			
			
			closeConnections();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public Socket getSocket() {
		return socket;
	}

	public void setSocket(Socket socket) {
		this.socket = socket;
	}


	public ServerMain getServer() {
		return server;
	}


	public void setServer(ServerMain server) {
		this.server = server;
	}

	
	private void closeConnections() throws IOException {
		System.out.println("Client " + socket.getInetAddress().getHostAddress() + " has disconnected.");
		socket.close();
		in_socket.close();
		out_socket.close();
	}


	public String getHelpMessage() {
		return helpMessage;
	}


	public void setHelpMessage(String helpMessage) {
		this.helpMessage = helpMessage;
	}

}
