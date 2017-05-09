package server.model;

public class Request {
	private String action;
	private String data = null;
	
	
	public Request(String action) {
		this.action = action;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	@Override
	public String toString() {
		return "Request [action=" + action + ", data=" + data + "]";
	}
	
	
}
