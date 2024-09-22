package com.embarkx.firstjobapp.exception;

public class CompanyNotFoundException extends RuntimeException{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	public CompanyNotFoundException(String message) {
		super(message);
	}
	public CompanyNotFoundException(Throwable cause) {
		super(cause);
	}
	public CompanyNotFoundException(String message, Throwable cause) {
		super(message,cause);
	}
}
