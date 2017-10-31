package br.ufabc.context;

public class Message {

	private String type;
	private String resource;
	private String message;
	private int rep;
	private int exp;
	private String scope;
	private long p1;
	private long p2;

	public String getType() {
		return type;
	}

	public int getExp() {
		return exp;
	}

	public void setExp(int exp) {
		this.exp = exp;
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getResource() {
		return resource;
	}

	public void setResource(String resource) {
		this.resource = resource;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getRep() {
		return rep;
	}

	public void setRep(int num) {
		this.rep = num;
	}

	public long getP1() {
		return p1;
	}

	public void setP1(long p1) {
		this.p1 = p1;
	}

	public long getP2() {
		return p2;
	}

	public void setP2(long p2) {
		this.p2 = p2;
	}

}
