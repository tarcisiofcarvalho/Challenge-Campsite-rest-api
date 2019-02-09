package org.campsite.error;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler{
    public RestExceptionHandler() {
        super();
    }

    
    /**
     * This is a custom handler related to max reservation days exception
     */
    @ExceptionHandler(value = MaxReservationDaysException.class)
    public ResponseEntity<Object> handleMaxReservationDays(MaxReservationDaysException ex) {
    	ErrorMessage err = new ErrorMessage(HttpStatus.NOT_ACCEPTABLE, "You are request exceed the limit max 3 days of reservation. Please adjust your request", "Max Reservation Days Exception");
    	return new ResponseEntity<>(err, HttpStatus.NOT_ACCEPTABLE);
    }     
    
    /**
     * This is a custom handler related to when the request is out of time limit permitted 
     */
    @ExceptionHandler(value = RequestReservationTimeException.class)
    public ResponseEntity<Object> handleRequestReservationTime(RequestReservationTimeException ex) {
    	ErrorMessage err = new ErrorMessage(HttpStatus.NOT_ACCEPTABLE, "The campsite can be reserved minimum 1 day(s) ahead of arrival and up to 1 month in advance. Please adjust your request", "Reservation Request Time Limited Exception");
    	return new ResponseEntity<>(err, HttpStatus.NOT_ACCEPTABLE);
    }  

    /**
    * This is a custom handler related to when the request is out of time limit permitted 
    */
    @ExceptionHandler(value = ReservationDatesNotAvailableException.class)
    public ResponseEntity<Object> handleReservationDaysNotAvailable(ReservationDatesNotAvailableException ex) {
    	ErrorMessage err = new ErrorMessage(HttpStatus.NOT_ACCEPTABLE, "The date(s) : " + ex.getMessage() + " are not available for your reservation. Please adjust your request", "Reservation Dates not Available Exception");
    	return new ResponseEntity<>(err, HttpStatus.NOT_ACCEPTABLE);
    }      
    
    /**
    * This is a custom handler related to when was passed a non valid booking identifier 
    */
   @ExceptionHandler(value = BookingIdentifierInvalidException.class)
   public ResponseEntity<Object> handleBookingIdentifierInvalid(BookingIdentifierInvalidException ex) {
   	 ErrorMessage err = new ErrorMessage(HttpStatus.BAD_REQUEST, "The booking identifier: " + ex.getMessage() + " is not valid, please verify.", "Invalid Booking Identifier Exception");
     return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
   }

   @ExceptionHandler({ DataIntegrityViolationException.class })
   public ResponseEntity<Object> handleBadRequest(final DataIntegrityViolationException ex, final WebRequest request) {
 	   ErrorMessage err = new ErrorMessage(HttpStatus.BAD_REQUEST, "Data Integrity Error. Please check if all JSON field were filled correctly", ex.getCause().getMessage());
       return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
   }
     

   @Override
   protected ResponseEntity<Object> handleHttpMessageNotReadable(final HttpMessageNotReadableException ex, final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
 	   ErrorMessage err = new ErrorMessage(HttpStatus.BAD_REQUEST, "There is something wrong with the structure of JSON passed, please review it.", ex.getCause().getMessage());
       return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
   }

   @Override
   protected ResponseEntity<Object> handleMethodArgumentNotValid(final MethodArgumentNotValidException ex, final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
 	   ErrorMessage err = new ErrorMessage(HttpStatus.BAD_REQUEST, "It is missing some json field or the field name is incorrect", ex.getCause().getMessage());
       return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);       
   }

}
