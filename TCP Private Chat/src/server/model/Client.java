package server.model;

import java.io.PrintWriter;
import java.net.Socket;


public class Client {
	private PrintWriter outSocket;
	private String name;
	
	public Client(int id, PrintWriter outSocket) {
		this.outSocket = outSocket;
		this.name = "unnamed"+id;
	}

	public PrintWriter getOutSocket() {
		return outSocket;
	}
	public void setOutSocket(PrintWriter outSocket) {
		this.outSocket = outSocket;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Client))
			return false;
		Client other = (Client) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Client [ name=" + name + "]";
	}
}
