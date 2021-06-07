package model;

import java.io.Serializable;

public class Supplier implements Serializable{
	
	private static final long serialVersionUID = 1;
	private String name;
	private String phoneNumber;
	
	private Supplier left;
	private Supplier right;
	private Supplier parent;
	
	/**
	*This is the constructor of the class. <br>
	*<b>name:</b> Supplier.<br>
	*<b>pre</b>: the variables n and ph are already initialized. <br>
	*<b>post:</b> the attributes of the class have been initialized.<br>
	*@param n Is a String variable that contains the name of a supplier. n!=null and n!="".<br>
	*@param ph Is a String variable that contains the phone number of a supplier. ph!=null and ph!="".<br>
	*/
	public Supplier(String n, String ph) {
		name = n;
		phoneNumber = ph;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Supplier getLeft() {
		return left;
	}

	public void setLeft(Supplier left) {
		this.left = left;
	}

	public Supplier getRight() {
		return right;
	}

	public void setRight(Supplier right) {
		this.right = right;
	}

	public Supplier getParent() {
		return parent;
	}

	public void setParent(Supplier parent) {
		this.parent = parent;
	}
}
