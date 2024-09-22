package com.embarkx.firstjobapp.exception;

public class ReviewNotFoundException extends RuntimeException{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	public ReviewNotFoundException(String message) {
		super(message);
	}
	public ReviewNotFoundException(Throwable cause) {
		super(cause);
	}
	public ReviewNotFoundException(String message, Throwable cause) {
		super(message,cause);
	}
}
