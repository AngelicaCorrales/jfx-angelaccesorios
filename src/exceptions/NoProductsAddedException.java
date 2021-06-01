package exceptions;

public class NoProductsAddedException extends Exception{
	
	private static final long serialVersionUID = 1;
	public NoProductsAddedException() {
		super("ERROR: no products added to the receipt");
	}


}
