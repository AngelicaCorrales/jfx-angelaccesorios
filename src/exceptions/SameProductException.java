package exceptions;

public class SameProductException extends Exception{
	
	private static final long serialVersionUID = 1;
	public SameProductException() {
		super("Existe un producto con el mismo tipo, marca y modelo agregado al sistema, intentelo nuevamente.");
	}
}
