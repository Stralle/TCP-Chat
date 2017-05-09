package server.controller;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import server.model.Client;
import server.model.Request;

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
	
	public void sendMessage(Request request) {
		//TODO: to be implemented
	}
	
	public void whoIsOnline(Request request) {
		//TODO: to be implemented
	}
	
	
	
}
