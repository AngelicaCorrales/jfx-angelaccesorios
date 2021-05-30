package model;

public class User extends Person{
	private String userName;
	private String password;
	private int numberReceipts;
	private double sumTotalReceipts;
	private int cont;
	
	private User next;
	private User prev;
	
	public User(String name, String lastName, String id, String userName, String password) {
		super(name, lastName, id);
		this.userName=userName;
		this.password=password;
		
		numberReceipts=0;
		sumTotalReceipts=0;
		cont=0;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getNumberReceipts() {
		return numberReceipts;
	}

	public void setNumberReceipts(int numberReceipts) {
		this.numberReceipts = numberReceipts;
	}

	public double getSumTotalReceipts() {
		return sumTotalReceipts;
	}

	public void setSumTotalReceipts(double sumTotalReceipts) {
		this.sumTotalReceipts = sumTotalReceipts;
	}

	public int getCont() {
		return cont;
	}

	public void setCont(int cont) {
		this.cont = cont;
	}

	public User getNext() {
		return next;
	}

	public void setNext(User next) {
		this.next = next;
	}

	public User getPrev() {
		return prev;
	}

	public void setPrev(User prev) {
		this.prev = prev;
	}

}
