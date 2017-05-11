package server.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import server.ClientThread;
import server.model.Request;
import server.repository.ClientDao;

public class RequestHandler {
	private ClientThread client;
	private String helpMessage =  "1. To quit from this chat type: \'!quit\'."
								+ "2. To send a message to another client select it from the list."
								+ "3. For help type \'!help\'.";;
	
	public RequestHandler(ClientThread client) throws IOException{
		this.setClient(client);
	}
	
	public void rename(Request request) {
		if(request == null || request.getData() == null)
			return;
		ClientDao.getInstance().getClientList().remove(getClient().getName());
		getClient().setName(request.getData().toString());
		ClientDao.getInstance().getClientList().put(getClient().getName(), getClient());
		
	}
	
	public void notImplemented() {
		this.getClient().getOut_socket().println("This request is not implemented yet.");
	}
	
	public void help(Request request){
		this.getClient().getOut_socket().println("help§"+helpMessage);
		
	}

	public void disconnect() throws IOException {
		this.getClient().getSocket().close();
	}
	
	@SuppressWarnings("deprecation")
	public void quit(Request request) throws IOException {
		ClientDao.getInstance().getClientList().remove(getClient().getName());
		this.getClient().stop();
		disconnect();
		
	}
	
	public void sendMessage(Request request) throws IOException {
		String[] split = request.getData().split("->");
		
		if(split.length<2){
			notImplemented();
			return;
		}
		
		String imeKlijenta = split[0];
		String poruka = split[1];
		
		ClientThread destinacioniKlijent = ClientDao.getInstance().findByName(imeKlijenta);
		
		if(destinacioniKlijent == null){
			this.getClient().getOut_socket().println("Klijent sa tim imenom nije online!");
			return;
		}
		
		destinacioniKlijent.getOut_socket().println("message§" + this.getClient().getName() + "§" + poruka);
	}
	
	public void whoIsOnline(Request request) {
		ClientDao c = ClientDao.getInstance();
		
		List<ClientThread> lista = new ArrayList<>(c.getClientList().values());
		
		String response = "";
		if(lista!=null && !lista.isEmpty()){
			for (ClientThread client : lista) {
				response+=client.getName()+";";
			}
		}
		
		this.getClient().getOut_socket().println("whoIsOnline§"+response);
	}

	public ClientThread getClient() {
		return client;
	}

	public void setClient(ClientThread client) {
		this.client = client;
	}
	
	
	
}
