package br.ufabc.context;

import java.util.ArrayList;
import java.util.List;

public class Rule {
	private String type;
	private List message = new ArrayList();
	private List operation = new ArrayList();
	private Action action; 

	public Action getAction() {
		return action;
	}
	public void setAction(Action action) {
		this.action = action;
	}
	public String getType() {
		return type;
	}
	public List getOperation() {
		return operation;
	}
	public void setOperation(List operation) {
		this.operation = operation;
	}
	public void setType(String type) {
		this.type = type;
	}
	public List getMessage() {
		return message;
	}
	public void setMessage(List message) {
		this.message = message;
	}

	
	
}
