package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import client.Client;

/**
 * 
 * @author Strahinja
 *
 */
public class ServerMain {
	
	private ServerSocket server_socket;
	private Socket socket;
	private int port = 2017 + 29 + 4;
	
	private ArrayList<Client> listOfClients = new ArrayList<Client>();
	
	
	public ServerMain() throws IOException {
		server_socket = new ServerSocket(port);
		System.out.println("Server opened at port " + port);
		
		while(true) {
			socket = server_socket.accept();
			ServerThread server_thread = new ServerThread(socket, this);
			Thread thread = new Thread(server_thread);
			thread.run(); 										//TODO: Check if here should be start() instead of run().
		}
		
		
	}
	
	public static void main(String[] args) throws IOException {
		new ServerMain();
	}

	public ArrayList<Client> getListOfClients() {
		return listOfClients;
	}

	public void setListOfClients(ArrayList<Client> listOfClients) {
		this.listOfClients = listOfClients;
	}
}
