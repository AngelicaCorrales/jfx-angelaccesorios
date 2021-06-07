package model;

public class Client extends Person{

	private static final long serialVersionUID = 1;
	private TypeId typeId;
	private String address;
	private String phone;
	
	/**
	*This is the constructor of the class. <br>
	*<b>name:</b> Client. <br>
	* <b>pre</b>: The variable id, typeId, name, lastName, address, phone, are already initialized. <br>
	*<b>post:</b> the attributes and relationships of the class have been initialized.<br>
	* @param id Is a String variable that contains the id number of the client. id!="" and id!=null.<br>
	* @param typeId Is a TypeId variable that contains the id type of the client. typeId==TI, typeId==CC,typeId==PP, or typeId==CE<br>
	* @param name Is a String variable that contains the name of the client. name!="" and name!=null.<br>
	* @param lastName Is a String variable that contains the lastName of the client. lastName!="" and lastName!=null.<br>
	* @param address Is a String variable that contains the address of the client. address!="" and address!=null.<br>
	* @param phone Is a String variable that contains the phone of the client. phone!="" and phone!=null.<br>
	*/
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
