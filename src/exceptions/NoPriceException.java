package exceptions;

public class NoPriceException extends Exception{

	private static final long serialVersionUID = 1;

	public NoPriceException(double p) {
		super("El precio del producto no puede ser cero. Usted ingreso "+p+" en el campo de texto de dicho atributo dentro del formulario, intentelo nuevamente.");
	}
}
