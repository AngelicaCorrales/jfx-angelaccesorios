package model;

import java.io.Serializable;

public class Brand implements Serializable,  Comparable<Brand>{

	private static final long serialVersionUID = 1;
	private String name;
	private boolean enabled;
	
	/**
	*This is the constructor of the class. <br>
	*<b>name:</b> Brand.<br>
	*<b>pre</b>: the variable n is already initialized. <br>
	*<b>post:</b> the attributes of the class have been initialized.<br>
	*@param n Is a String variable that contains the name of a brand. n!=null and n!="".<br>
	*/
	public Brand(String n) {
		name = n;
		enabled = true;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	/**
	* This method obtains a String with the state of a brand. <br>
	* <b>name</b>: getState.<br>
 	* <b>post</b>: the state of a brand has been obtained. <br>
 	* @return a <code> String </code> specifying state, the state of a brand.
 	*/
	
	public String getState() {
		String state = "";
		if(enabled==true) {
			state = "Habilitado";
		}else {
			state = "Deshabilitado";
		}
		return state;
	}
	
	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	/**
	* This method obtains a String with the name of a brand. <br>
	* <b>name</b>: toString.<br>
 	* <b>post</b>: the name of a brand has been obtained. <br>
 	* @return a <code> String </code> specifying name, the name of a brand.
 	*/
	
	public String toString() {
		return name;
	}

	/**
	* This method compares the names of two brands.<br>
	* <b>name</b>: compareTo <br>
	* <b>post</b>: the brands have been compared. <br>
	*@param b Is a Brand object that references the brand that wants to be compared. b!=null<br>
	* @return an <code> integer </code> a variable that indicates if the name of the brand is greater than the other, equal, or less.
	*/
	@Override
	public int compareTo(Brand b) {
		
		return name.compareTo(b.getName());
	}
}
