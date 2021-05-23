package model;

import java.io.Serializable;

public class TypeOfProduct implements Serializable{

	private static final long serialVersionUID = 1;
	private String name;
	private boolean enabled;
	
	public TypeOfProduct(String n) {
		name = n;
		enabled = true;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public String getState() {
		String state = "";
		if(enabled==true) {
			state = "Habilitado";
		}else {
			state = "Deshabilitado";
		}
		return state;
	}
	
	public String toString() {
		return name;
	}
}