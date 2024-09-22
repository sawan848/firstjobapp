package com.embarkx.firstjobapp.exception;

public class JobNotFoundException extends RuntimeException{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	public JobNotFoundException(String message) {
		super(message);
	}
	public JobNotFoundException(Throwable cause) {
		super(cause);
	}
	public JobNotFoundException(String message, Throwable cause) {
		super(message,cause);
	}
}
