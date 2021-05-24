package model;

public class ElectronicEquipment extends TypeOfProduct{

	private static final long serialVersionUID = 1;
	private String code;
	private Supplier supplier;

	public ElectronicEquipment(String n) {
		super(n);
		code = "EQE";
	}

	public String getCode() {
		return code;
	}

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}
	
}
