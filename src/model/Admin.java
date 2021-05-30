package model;

public class Admin extends User{
	
	private static final long serialVersionUID = 1;
	private String email;
	
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
