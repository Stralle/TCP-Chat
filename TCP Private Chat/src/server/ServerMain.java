package server;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
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
			PrintWriter out_socket = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()),true);
			
				//on connection create new client 
			Client client = new Client((clientNextID++), out_socket);
				//add client to client repository
			clientRepository.addClient(client);
			
			ServerThread server_thread = new ServerThread(client, socket);
			server_thread.start();
			
		}
		
		
	}
	
	public static void main(String[] args) throws IOException {
		new ServerMain();
	}


}
