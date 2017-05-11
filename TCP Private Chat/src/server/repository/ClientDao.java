package server.repository;

import java.util.LinkedHashMap;
import java.util.Map;

import server.ClientThread;

/**
 * singleton Client repository
 * @author Vuk Simic <vuk.simic@gmail.com>
 */
public class ClientDao {
	private static ClientDao instance;
	/**
	 * List of connected clients
	 */
	private Map<String,ClientThread> clientList = new LinkedHashMap<String,ClientThread>();
	
	public static ClientDao getInstance(){
		if(instance == null)
			instance = new ClientDao();
		
		return instance;
	}
	private ClientDao(){}
	

	public void addClient(ClientThread client){
		if(client == null)
			return;
		getClientList().put(client.getName(),client);
	}
	 
	public ClientThread findByName (String name){
		return getClientList().get(name);
	}
	
	public Map<String,ClientThread> getClientList() {
		return clientList;
	}
	public void setClientList(Map<String,ClientThread> clientList) {
		this.clientList = clientList;
	}
}
