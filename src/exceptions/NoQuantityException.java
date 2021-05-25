package exceptions;

public class NoQuantityException extends Exception{
	
	private static final long serialVersionUID = 1;

	public NoQuantityException(int q) {
		super("La cantidad debe ser distinta de cero. Usted digito "+q+" en el campo de texto correspondiente a dicho atributo dentro del formulario, intentelo nuevamente.");
	}
}
