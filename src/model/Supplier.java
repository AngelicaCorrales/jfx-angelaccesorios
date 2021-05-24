package model;

public class Supplier {
	
	private String name;
	private String phoneNumber;
	
	private Supplier left;
	private Supplier right;
	private Supplier parent;
	
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
