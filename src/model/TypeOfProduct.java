package model;

import java.io.Serializable;

public abstract class TypeOfProduct implements Serializable{

	private static final long serialVersionUID = 1;
	private String name;
	private boolean enabled;
	
	private TypeOfProduct left;
	private TypeOfProduct right;
	private TypeOfProduct parent;
	
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
