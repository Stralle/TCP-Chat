package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import server.model.Client;
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
			System.out.println("stigao soket");
				//on connection create new client 
			Client client = new Client((clientNextID++), socket);
				//add client to client repository
			clientRepository.addClient(client);
			
			ServerThread server_thread = new ServerThread(client);
			server_thread.start();
			//Thread thread = new Thread(server_thread);
			//thread.run(); 										//TODO: Check if here should be start() instead of run().
			
		}
		
		
	}
	
	public static void main(String[] args) throws IOException {
		new ServerMain();
	}


}
