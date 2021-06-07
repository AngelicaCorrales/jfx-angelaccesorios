package model;

public class Accessory extends TypeOfProduct{

	private static final long serialVersionUID = 1;
	private String code;
	
	/**
	*This is the constructor of the class. <br>
	*<b>name:</b> Accessory.<br>
	*<b>pre</b>: the variable n is already initialized. <br>
	*<b>post:</b> the attributes of the class have been initialized.<br>
	*@param n Is a String variable that contains the name of an Accessory type of product. n!=null and n!="".<br>
	*/
	public Accessory(String n) {
		super(n);
		code = "ACC";
	}
	
	public String getCode() {
		return code;
	}
}
