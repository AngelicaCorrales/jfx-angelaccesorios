package exceptions;

public class SameIDException extends Exception {

	private static final long serialVersionUID = 1;
	
	public SameIDException() {
		super("ERROR: there is a person with the same ID");
	}

}
