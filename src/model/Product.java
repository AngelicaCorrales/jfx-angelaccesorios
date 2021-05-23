package model;

public class Product {
	
	private String code;
	private TypeOfProduct type;
	private Brand brand;
	private int units;
	private boolean guarantee;
	private String model;
	private double price;
	private boolean enabled;
	
	public Product(TypeOfProduct t, Brand b, int u, boolean g, String m, double p) {
		type = t;
		brand = b;
		units = u;
		guarantee = g;
		model = m;
		price = p;
		enabled = true;
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

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	
}