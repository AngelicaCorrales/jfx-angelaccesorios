package model;

import java.io.Serializable;

public class Product implements Serializable, taxIVA, Comparable<Product>{
	
	private static final long serialVersionUID = 1;
	private String code;
	private TypeOfProduct type;
	private Brand brand;
	private int units;
	private boolean guarantee;
	private String model;
	private double price;
	private boolean enabled;
	private int cont;
	private int numTimesAddedOrders;
	private double totalPriceAddedOrders;
	
	/**
	*This is the constructor of the class. <br>
	* <b>name</b>: Product <br>
	* <b>pre</b>: the variables t, b, u, g, m, p and c are already initialized. <br>
	* <b>post</b>: All the attributes of the class were initialized. <br>
	* @param t Is a TypeOfProduct object that contains the type of product related with a product. t!=null.<br>
	* @param b Is a Brand object that contains the brand of a product. b!=null.<br>
	* @param u Is an integer variable that contains the number of available units of a product. u!=0.<br>
	* @param g Is a boolean variable that indicates if a product has guarantee or not.<br>
	* @param m Is a String variable that contains the model of a product. m!=null and m!="".<br>
	* @param p Is a double variable that contains the price of a product. p is greater than 0.<br>
	* @param c Is a String variable that contains the code of a product. c!=null, c!="".<br>
	*/
	
	public Product(TypeOfProduct t, Brand b, int u, boolean g, String m, double p, String c) {
		type = t;
		brand = b;
		units = u;
		guarantee = g;
		model = m;
		price = p;
		enabled = true;
		code = c;
		cont = 0;
		setNumTimesAddedOrders(0);
		setTotalPriceAddedOrders(0);
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public TypeOfProduct getType() {
		return type;
	}

	public void setType(TypeOfProduct type) {
		this.type = type;
	}
	
	public String getTypeName() {
		String typeN = type.getName();
		return typeN;
	}

	public Brand getBrand() {
		return brand;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
	}
	
	public String getBrandName() {
		String BrandN= brand.getName();
		return BrandN;
	}

	public int getUnits() {
		return units;
	}

	public void setUnits(int units) {
		this.units = units;
	}

	/**
	* This method obtains a String with the guarantee of a product. <br>
	* <b>name</b>: getGuarantee.<br>
 	* <b>post</b>: the guarantee of a product has been obtained. <br>
 	* @return a <code> String </code> specifying g, the guarantee of a product.
 	*/
	public String getGuarantee() {
		String g = "";
		if(guarantee==true) {
			g = "Si";
		}else {
			g = "No";
		}
		return g;
	}
	
	public boolean hasGuarantee() {
		return guarantee;
	}

	public void setGuarantee(boolean guarantee) {
		this.guarantee = guarantee;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	/**
	* This method obtains a String with the state of a product. <br>
	* <b>name</b>: getState.<br>
 	* <b>post</b>: the state of a product has been obtained. <br>
 	* @return a <code> String </code> specifying state, the state of a product.
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

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	
	public String getInfo() {
		return type+" "+brand+" "+model;
	}
	
	/**
	* This method obtains a String with the type of product, brand, model and price of a product. <br>
	* <b>name</b>: toString.<br>
 	* <b>post</b>: the type of product, brand, model and price of a product has been obtained. <br>
 	* @return a <code> String </code> with the type of product, brand, model and price of a product.
 	*/
	public String toString() {
		return type+" "+brand+" "+model+" "+price+"c/u";
	}

	public int getCont() {
		return cont;
	}

	public void setCont(int cont) {
		this.cont = cont;
	}

	public int getNumTimesAddedOrders() {
		return numTimesAddedOrders;
	}

	public void setNumTimesAddedOrders(int numTimesAddedOrders) {
		this.numTimesAddedOrders = numTimesAddedOrders;
	}

	public double getTotalPriceAddedOrders() {
		return totalPriceAddedOrders;
	}

	public void setTotalPriceAddedOrders(double totalPriceAddedOrders) {
		this.totalPriceAddedOrders = totalPriceAddedOrders;
	}

	/**
	* This method returns the IVA tax of a product if its type is ElectronicEquipment and its price is greater than 783000. <br>
	* <b>name</b>: calculateIVA <br>
	* <b>post</b>: the IVA tax of a product of an ElectronicEquipment type has been gotten. <br>
	* @return an <code> double </code> specifying iva, the IVA tax of a product of an ElectronicEquipment type.
	*/
	@Override
	public double calculateIVA() {
		double iva=0;
		if(type instanceof ElectronicEquipment && price>783000) {
			iva=price*0.19;
		}
		return iva;
	}

	@Override
	public int compareTo(Product p) {
		int comparing= type.compareTo(p.getType());
		if(comparing==0) {
			comparing=brand.compareTo(p.getBrand());
		}
		return comparing;
	}
	
}
