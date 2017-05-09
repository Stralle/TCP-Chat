package server.repository;

import java.util.LinkedHashMap;
import java.util.Map;

import server.model.Client;

/**
 * singleton Client repository
 * @author Vuk Simic <vuk.simic@gmail.com>
 */
public class ClientDao {
	private static ClientDao instance;
	/**
	 * List of connected clients
	 */
	private Map<String,Client> clientList = new LinkedHashMap<String,Client>();
	
	public static ClientDao getInstance(){
		if(instance==null)
			instance=new ClientDao();
		
		return instance;
	}
	private ClientDao(){}
	

	public void addClient(Client client){
		if(client==null)
			return;
		
		getClientList().put(client.getName(),client);
	}
	 
	public Client findByName (String name){
		return getClientList().get(name);
	}
	public Map<String,Client> getClientList() {
		return clientList;
	}
	public void setClientList(Map<String,Client> clientList) {
		this.clientList = clientList;
	}
}
