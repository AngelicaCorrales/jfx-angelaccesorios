package model;

import java.util.ArrayList;

public class ElectronicEquipment extends TypeOfProduct{

	private static final long serialVersionUID = 1;
	private String code;
	private ArrayList<Supplier> suppliers;

	public ElectronicEquipment(String n) {
		super(n);
		code = "EQE";
		suppliers = new ArrayList<Supplier>();
	}

	public String getCode() {
		return code;
	}

	public ArrayList<Supplier> getSuppliers() {
		return suppliers;
	}

	public void setSuppliers(ArrayList<Supplier> suppliers) {
		this.suppliers = suppliers;
	}
	
	public boolean findSupplier(String name) {
		boolean found=false;
		for(int i=0; i<suppliers.size() && !found;i++ ) {
			if(suppliers.get(i).getName().equals(name)) {
				found=true;						
			}
		}		
		return found;
	}
	
}
