package exceptions;

public class SameUserNameException extends Exception {

	private static final long serialVersionUID = 1;
	public SameUserNameException() {
		super("ERROR: there is a user with the same username");
	}

}
