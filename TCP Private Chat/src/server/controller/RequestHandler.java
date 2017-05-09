package server.controller;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import server.model.Client;
import server.model.Request;
import server.repository.ClientDao;

public class RequestHandler {
	private PrintWriter out_socket;
	private Client client;
	private String helpMessage =  "1. To quit from this chat type: \'!quit\'.\n"
			+ "2. To send a message to another client type \'!clientName: message\'.\n"
			+ "3. To check who is active type \'!active\'.\n";
	
	public RequestHandler(Client client) throws IOException{
		this.client=client;
		out_socket = new PrintWriter(new OutputStreamWriter(client.getSocket().getOutputStream()), true);
	}
	
	public void register(Request request) {
		client.setName(request.getData().toString());
		out_socket.println("Welcome " +request.getData().toString() + " to our chat. For help type: \'!help\'");
	}
	
	public void notImplemented() {
		out_socket.println("This request is not implemented yet.");
	}
	
	public void help(Request request){
		//TODO: to be implemented
		out_socket.println(helpMessage);
		
	}

	public void disconnect(){
		out_socket.close();
	}
	
	public void sendMessage(Request request) throws IOException {
		String[] split = request.getData().split("->");
		
		if(split.length<2){
			notImplemented();
			return;
		}
		
		String imeKlijenta = split[0];
		String poruka = split[1];
		
		Client destinacioniKlijent = ClientDao.getInstance().findByName(imeKlijenta);
		
		if(destinacioniKlijent==null){
			out_socket.println("Klijent sa tim imenom nije online!");
			return;
		}
		
		PrintWriter pw  = new PrintWriter(new OutputStreamWriter(client.getSocket().getOutputStream()), true);
		pw.println(poruka);
	}
	
	public void whoIsOnline(Request request) {
		ClientDao c = ClientDao.getInstance();
		
		List<Client> lista = new ArrayList<>(c.getClientList().values());
		
		String response = "";
		if(lista!=null && !lista.isEmpty()){
			for (Client client : lista) {
				response+=client.getName()+";";
			}
		}
		
		out_socket.println(response);
	}
	
	
	
}
