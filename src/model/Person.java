package model;

import java.io.Serializable;

public abstract class Person implements Serializable {
	
	private static final long serialVersionUID = 1;
	private String name;
	private String lastName;
	private String id;
	private boolean enabled;
	
	/**
	*This is the constructor of the class. <br>
	*<b>name:</b> Person. <br>
	* <b>pre</b>: The variable id, name, lastName, are already initialized. <br>
	*<b>post:</b> the attributes and relationships of the class have been initialized.<br>
	* @param id Is a String variable that contains the id number of the person. id!="" and id!=null.<br>
	* @param name Is a String variable that contains the name of the person. name!="" and name!=null.<br>
	* @param lastName Is a String variable that contains the lastName of the person. lastName!="" and lastName!=null.<br>
	*/
	public Person(String name, String lastName, String id) {
		this.name = name;
		this.lastName = lastName;
		this.id = id;
		this.enabled = true;
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	
	public String getStatus() {
		String status = "";
		if(enabled) {
			status = "Habilitado";
		}else {
			status = "Deshabilitado";
		}
		return status;
	}
	
	public String toString() {
		return name+" "+lastName+"|"+id;
	}
}
