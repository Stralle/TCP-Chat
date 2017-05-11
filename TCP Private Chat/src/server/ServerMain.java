package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import server.repository.ClientDao;


/**
 * 
 * @author Strahinja
 *
 */
public class ServerMain {
	private int clientNextID = 1;
	private ServerSocket server_socket;
	
	private int port = 2017 + 29 + 4;
	private ClientDao clientRepository = ClientDao.getInstance();
	
	public ServerMain() throws IOException {
		server_socket = new ServerSocket(port);
		System.out.println("Server opened at port " + port);
		
		while(true) {
			Socket socket = server_socket.accept();
			ClientThread client_thread = new ClientThread(clientNextID++, socket);
			
			clientRepository.addClient(client_thread);
			client_thread.start();
			
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		new ServerMain();
	}


}
