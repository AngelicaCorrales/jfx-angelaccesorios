package model;

import java.io.Serializable;

public abstract class TypeOfProduct implements Serializable{

	private static final long serialVersionUID = 1;
	private String name;
	private boolean enabled;
	
	private TypeOfProduct left;
	private TypeOfProduct right;
	private TypeOfProduct parent;
	
	/**
	*This is the constructor of the class. <br>
	*<b>name:</b> TypeOfProduct.<br>
	*<b>pre</b>: the variable n is already initialized. <br>
	*<b>post:</b> the attributes of the class have been initialized.<br>
	*@param n Is a String variable that contains the name of a type of product. n!=null and n!="".<br>
	*/		
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

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	/**
	* This method obtains a String with the state of a type of product. <br>
	* <b>name</b>: getState.<br>
 	* <b>post</b>: the state of a type of product has been obtained. <br>
 	* @return a <code> String </code> specifying state, the state of a type of product.
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
	
	/**
	* This method obtains a String with the name of a type of product. <br>
	* <b>name</b>: toString.<br>
 	* <b>post</b>: the name of a type of product has been obtained. <br>
 	* @return a <code> String </code> specifying name, the name of a type of product.
 	*/
	public String toString() {
		return name;
	}

	public TypeOfProduct getLeft() {
		return left;
	}

	public void setLeft(TypeOfProduct left) {
		this.left = left;
	}

	public TypeOfProduct getRight() {
		return right;
	}

	public void setRight(TypeOfProduct right) {
		this.right = right;
	}

	public TypeOfProduct getParent() {
		return parent;
	}

	public void setParent(TypeOfProduct parent) {
		this.parent = parent;
	}
}
