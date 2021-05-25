package exceptions;

public class NegativePriceException extends Exception{

	private static final long serialVersionUID = 1;

	public NegativePriceException(double p) {
		super("El precio del producto no puede ser negativo. Usted ingreso "+p+" en el campo de texto de dicho atributo dentro del formulario, intentelo nuevamente.");
	}
}
