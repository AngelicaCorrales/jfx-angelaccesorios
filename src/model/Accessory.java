package model;

public class Accessory extends TypeOfProduct{

	private static final long serialVersionUID = 1;
	private String code;
	
	public Accessory(String n) {
		super(n);
		code = "ACC";
	}
	
	public String getCode() {
		return code;
	}
}
