package br.ufabc.context;

public class Action {
	private String topic;
	private String address;
	private String message;
	
	public Action() {
		
	}
	
	public Action(String topic, String address, String message) {
		this.topic = topic;
		this.address = address;
		this.message = message;
	}
	
	public String getTopic() {
		return topic;
	}
	public void setTopic(String topic) {
		this.topic = topic;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}
