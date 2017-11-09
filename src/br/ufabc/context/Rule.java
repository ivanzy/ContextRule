package br.ufabc.context;



public class Rule {
	private String type;
	//private List operation = new ArrayList();
	private String operation;
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
	public String getOperation() {
		return operation;
	}
	public void setOperation(String operation) {
		this.operation = operation;
	}
	public void setType(String type) {
		this.type = type;
	}


	
	
}
