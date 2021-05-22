package model;

public class User extends Person{
	private String userName;
	private String password;
	private int numberOrders;
	private double sumTotalOrders;
	private int cont;
	
	private User next;
	private User prev;
	
	public User(String name, String lastName, String id, String userName, String password) {
		super(name, lastName, id);
		this.userName=userName;
		this.password=password;
		
		numberOrders=0;
		sumTotalOrders=0;
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

	public int getNumberOrders() {
		return numberOrders;
	}

	public void setNumberOrders(int numberOrders) {
		this.numberOrders = numberOrders;
	}

	public double getSumTotalOrders() {
		return sumTotalOrders;
	}

	public void setSumTotalOrders(double sumTotalOrders) {
		this.sumTotalOrders = sumTotalOrders;
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
