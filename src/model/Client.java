package model;

public class Client extends Person{

	private static final long serialVersionUID = 1;
	private TypeId typeId;
	private String address;
	private String phone;
	
	
	public Client(String name, String lastName, String id, TypeId typeId, String address, String phone) {
		super(name, lastName, id);
		this.typeId=typeId;
		this.address = address;
		this.phone = phone;
	}
	
	public TypeId getTypeId() {
		return typeId;
	}

	public void setTypeId(TypeId typeId) {
		this.typeId = typeId;
	}
	
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public String getNameAndLastName() {
		return super.getName()+" "+super.getLastName();
	}

	


}
