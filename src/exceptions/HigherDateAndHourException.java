package exceptions;

public class HigherDateAndHourException extends Exception{

	private static final long serialVersionUID = 1;
	
	public HigherDateAndHourException() {
		super("La fecha y hora inicial escogidas son mayores a la fecha y hora final dada para generar el reporte, intentelo nuevamente");
	}

}
