package model;

import java.util.ArrayList;

public class ElectronicEquipment extends TypeOfProduct{

	private static final long serialVersionUID = 1;
	private String code;
	private ArrayList<Supplier> suppliers;

	/**
	*This is the constructor of the class. <br>
	*<b>name:</b> ElectronicEquipment.<br>
	*<b>pre</b>: the variable n is already initialized. <br>
	*<b>post:</b> the attributes of the class have been initialized.<br>
	*@param n Is a String variable that contains the name of an ElectronicEquipment type of product. n!=null and n!="".<br>
	*/
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
	
	/**
	* This method searches for a supplier within the list of suppliers of an specific ElectronicEquipment type of product. <br>
	* <b>name</b>: findSupplier <br>
	* <b>pre</b>: the variable name is already initialized. <br>
	* <b>post</b>: True or false was returned depending of the result of the verification. <br>
	* @param name Is a String variable that contains the name of a supplier. name!='' name!=null.<br>
	* @return a <code> boolean </code> specifying found, a variable that indicates if a certain supplier is already in the list of suppliers for a specific ElectronicEquipment type of product. 
	*/
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
