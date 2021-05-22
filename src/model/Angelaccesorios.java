package model;

import java.io.IOException;

public class Angelaccesorios {
	private User firstUser;
	private User lastUser;
	private User loggedUser;
	
	public Angelaccesorios() {
		
	}

	public User getLoggedUser() {
		return loggedUser;
	}

	public void setLoggedUser(User loggedUser) {
		this.loggedUser = loggedUser;
	}
	
	public User getFirstUser() {
		return firstUser;
	}

	public User getLastUser() {
		return lastUser;
	}
	
	
	
	public boolean createUser(String id, String name, String lastName,String userName, String password) throws IOException {

		boolean created=false;

		User user= searchUser(id);
		
		if(user==null) {
						
			user= new User(name,lastName,id, userName,password);

			lastUser.setNext(user);
			user.setPrev(lastUser);
			lastUser=user;

			created=true;
			//saveDataUsers();
			
		}
		return created;
	}
	
	
	public boolean createUserAdmin(String id, String name, String lastName,String userName, String password,String email) throws IOException {

		boolean created=false;
		//emailexception
		//spaceexception

		Admin admin= new Admin(name,lastName,id, userName,password, email);

		firstUser=admin;

		lastUser=admin;

		created=true;
		//saveDataUsers();


		return created;
	}
	
	public User searchUser(String id) {

		return searchUser( firstUser, id);
	}
	
	private User searchUser(User current, String id) {
		User u=null;
		if(current!=null && u==null) {
			if(current.getId().equals(id)) {
				 u=current;
			}else {
				current=current.getNext();
				u=searchUser(current, id);
			}
		}
		return u;
	}

}
