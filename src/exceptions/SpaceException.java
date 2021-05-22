package exceptions;

public class SpaceException extends Exception{

	private static final long serialVersionUID = 1;
	public SpaceException() {
		super("ERROR: spaces cannot be used");
	}

}
