package model;

public class ElectronicEquipment extends TypeOfProduct{

	private static final long serialVersionUID = 1;
	private String code;

	public ElectronicEquipment(String n) {
		super(n);
		code = "EQE";
	}

	public String getCode() {
		return code;
	}
	
}
