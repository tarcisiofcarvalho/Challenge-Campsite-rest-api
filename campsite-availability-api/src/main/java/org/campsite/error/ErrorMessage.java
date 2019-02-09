package org.campsite.error;

import org.springframework.http.HttpStatus;

public class ErrorMessage {
	 
    private HttpStatus status;
    private String exceptionType;
    private String message;
 
    public ErrorMessage(HttpStatus status, String message, String exceptionType) {
        super();
        this.status = status;
        this.message = message;
        this.exceptionType = exceptionType;
        
    }


	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}


	public String getExceptionType() {
		return exceptionType;
	}


	public void setExceptionType(String exceptionType) {
		this.exceptionType = exceptionType;
	}

}