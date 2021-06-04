package model;

import java.io.Serializable;

public class Product implements Serializable{
	
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
	
	public String toString() {
		return type+" "+brand+" "+model+" "+price+"c/u";
	}

	public int getCont() {
		return cont;
	}

	public void setCont(int cont) {
		this.cont = cont;
	}
	
}
