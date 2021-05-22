package model;

import java.io.IOException;

import exceptions.EmailException;
import exceptions.SpaceException;

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
	
	
	
	public boolean createUser(String id, String name, String lastName,String userName, String password) throws IOException, SpaceException {

		boolean created=false;
		userName=userName.trim();
		String parts[]=userName.split(" ");
		if(parts.length>1) {
			throw new SpaceException();
		}
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
	
	
	public boolean createUserAdmin(String id, String name, String lastName,String userName, String password,String email) throws IOException, EmailException, SpaceException {

		boolean created=false;
		email=email.trim();
		String parts[]=email.split("@");
		if(parts.length>2 ||parts.length<=1) {
			throw new EmailException();
		}
		
		parts=email.split(" ");
		if(parts.length>1) {
			throw new SpaceException();
		}
		
		userName=userName.trim();
		parts=userName.split(" ");
		if(parts.length>1) {
			throw new SpaceException();
		}

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
