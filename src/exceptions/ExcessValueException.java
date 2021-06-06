package exceptions;

public class ExcessValueException extends Exception {

	private static final long serialVersionUID = 1;

	public ExcessValueException() {
		super("ERROR: excess of the value required for the receipt");
	}
}
