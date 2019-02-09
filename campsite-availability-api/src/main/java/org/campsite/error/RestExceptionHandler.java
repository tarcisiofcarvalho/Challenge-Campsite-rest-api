package org.campsite.error;

import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
     * This is a custom handler related to start and end date parameters parsing issues
     */
    @Override
	protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
    	ErrorMessage err = new ErrorMessage(status, ex.getRootCause().getMessage(), "TypeMismatch Exception");
		return handleExceptionInternal(ex, err, headers, status, request);
	}

    /**
     * This is a custom handler related to missing start or end date parameter
     */
    @ExceptionHandler(value = ParameterMissingException.class)
    public ResponseEntity<Object> handleParameterMissing(ParameterMissingException ex) {
    	ErrorMessage err = new ErrorMessage(HttpStatus.NOT_ACCEPTABLE, "Missing start or end date parameter", "Missing Parameter Exception");
    	return new ResponseEntity<>(err, HttpStatus.NOT_ACCEPTABLE);
    }    
}
