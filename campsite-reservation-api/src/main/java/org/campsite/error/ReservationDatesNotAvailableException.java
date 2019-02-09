package org.campsite.error;

/**
 * Custom Parameter Missing Exception for start or end date parameter
 * @author TARCISIOFRANCODECARV
 * @version 1.0
 */
public class ReservationDatesNotAvailableException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private final String message;

	public ReservationDatesNotAvailableException(String message) {
		super(message);
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
	   
	   
}