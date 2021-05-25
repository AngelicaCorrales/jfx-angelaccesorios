package exceptions;

public class NegativeQuantityException extends Exception{

	private static final long serialVersionUID = 1;
	
	public NegativeQuantityException(int q) {
		super("La cantidad no puede ser negativa. Usted digito "+q+" en el campo de texto correspondiente a dicho atributo dentro del formulario, intentelo nuevamente.");
	}

}
