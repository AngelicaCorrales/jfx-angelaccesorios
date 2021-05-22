package exceptions;

public class EmailException extends Exception{

	private static final long serialVersionUID = 1;
	
	public EmailException() {
		super("ERROR: invalid email");
	}

}
