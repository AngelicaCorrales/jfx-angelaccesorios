package model;

public class Admin extends User{
	
	private static final long serialVersionUID = 1;
	private String email;
	
	/**
	*This is the constructor of the class. <br>
	*<b>name:</b> Admin. <br>
	* <b>pre</b>: The variable id, name, lastName, userName, password, email are already initialized. <br>
	*<b>post:</b> the attributes and relationships of the class have been initialized.<br>
	* @param id Is a String variable that contains the id number of the user. id!="" and id!=null.<br>
	* @param name Is a String variable that contains the name of the user. name!="" and name!=null.<br>
	* @param lastName Is a String variable that contains the lastName of the user. lastName!="" and lastName!=null.<br>
	* @param userName Is a String variable that contains the user name of the user. userName!="" and userName!=null.<br>
	* @param password Is a String variable that contains the password of the user. password!="" and password!=null.<br>
	* @param email Is a String variable that contains the email of the user. email!="" and email!=null.<br>
	*/
	public Admin(String name, String lastName, String id, String userName, String password, String email) {
		super(name, lastName, id, userName, password);
		this.email=email;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
