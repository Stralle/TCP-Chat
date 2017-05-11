package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

import server.controller.RequestHandler;
import server.model.Request;

/**
 * 
 * @author Strahinja
 *
 */
public class ClientThread extends Thread{
	private BufferedReader in_socket;
	private PrintWriter out_socket;
	private Socket socket;
	private String serverMessage;
	private String clientMessage;
	private String helpMessage =  "1. To quit from this chat type: \'!quit\'.\n"
								+ "2. To send a message to another client type \'!clientName: message\'.\n"
								+ "3. To check who is active type \'!active\'.\n";
	
	 // this Thread handle requests from this client
	public ClientThread(int id, Socket socket) throws IOException {
		this.setName("unnamed" + id);
		setOut_socket(new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true));
		this.socket = socket;
	}

	
	public void run() {
		// TODO Auto-generated method stub
		try {
			in_socket = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	
			//TODO: Server receives a client's name first.
			
			
			RequestHandler rh = new RequestHandler(this);
			boolean loop = true;
			while(loop) {
				
				clientMessage = in_socket.readLine();
				if(clientMessage == null ||  clientMessage.isEmpty())
					continue;
				
				System.out.println("Poruka klijenta " + clientMessage);
				
				if(clientMessage.equalsIgnoreCase("!quit")) {
					break;				
				}	
				
				
				/*
				 * Svaki request sadrzi § koja razdvaja potrebnu akciju od podataka
				 * primer: rename§Vuk govori serveru da klijentu koji salje request postavi ime vuk
				 * primer2: send§Vuk§Cao, sta ima? govori serveru da treba da posalje poruku Vuku od klijenta koji je poslao poruku
				 * primer3: help@ korisnik je zatrazio help, bez ikakvih podataka samo akcija help
				 */
				
				if(!clientMessage.contains("§")){
					rh.notImplemented();
					continue;
				}
				
				String[] split = clientMessage.split("§");
				
				Request request = new Request(split[0]);
				if(split.length-1>0){
					//request has data
					request.setData(split[1]);
				}
				
					//call handler for this request 
				switch (request.getAction()) {
					
					case "rename":
							rh.rename(request);
						break;
					case "help":
							rh.help(request);
						break;
					case "whoIsOnline":
							rh.whoIsOnline(request);
						break;
					case "sendMessage":
							rh.sendMessage(request);
						break;
					default:
							rh.notImplemented();
						break;
				}
				
			}
			
			rh.disconnect();

			closeConnections();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	

	
	private void closeConnections() throws IOException {
		in_socket.close();
		
		//treba zatvoriti RequestHandler, jos ne znam kako
	}


	public String getHelpMessage() {
		return helpMessage;
	}
 

	public void setHelpMessage(String helpMessage) {
		this.helpMessage = helpMessage;
	}


	public String getServerMessage() {
		return serverMessage;
	}


	public void setServerMessage(String serverMessage) {
		this.serverMessage = serverMessage;
	}


	public PrintWriter getOut_socket() {
		return out_socket;
	}


	public void setOut_socket(PrintWriter out_socket) {
		this.out_socket = out_socket;
	}
	
	@Override
	public String toString() {
		return this.getName();
	}

}
