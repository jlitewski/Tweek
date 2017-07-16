package com.hackhalo2.lib.tweek.exceptions;

public class InvalidNullException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3138608398460088382L;
	
	public InvalidNullException(String message) {
		super(message);
	}
	
	public InvalidNullException(String message, Throwable cause) {
		super(message, cause);
	}

}
