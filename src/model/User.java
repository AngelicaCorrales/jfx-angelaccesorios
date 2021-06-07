package model;

public class User extends Person{
	private static final long serialVersionUID = 1;
	private String userName;
	private String password;
	private int numberReceipts;
	private double sumTotalReceipts;
	private int cont;
	
	private User next;
	private User prev;
	
	/**
	*This is the constructor of the class. <br>
	*<b>name:</b> User. <br>
	* <b>pre</b>: The variable id, name, lastName, userName, password, are already initialized. <br>
	*<b>post:</b> the attributes and relationships of the class have been initialized.<br>
	* @param id Is a String variable that contains the id number of the user. id!="" and id!=null.<br>
	* @param name Is a String variable that contains the name of the user. name!="" and name!=null.<br>
	* @param lastName Is a String variable that contains the lastName of the user. lastName!="" and lastName!=null.<br>
	* @param userName Is a String variable that contains the user name of the user. userName!="" and userName!=null.<br>
	* @param password Is a String variable that contains the password of the user. password!="" and password!=null.<br>
	*/
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
