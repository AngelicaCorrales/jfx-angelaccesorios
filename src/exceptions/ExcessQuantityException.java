package exceptions;

public class ExcessQuantityException extends Exception {

	private static final long serialVersionUID = 1;
	public ExcessQuantityException() {
		super("ERROR: excess of the quantity required for the product");
	}
}
