package com.hackhalo2.lib.tweek.exceptions;

public class InvalidOAuthTokenException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1683930006075815710L;
	
	public InvalidOAuthTokenException(String message) {
		super(message);
	}
	
	public InvalidOAuthTokenException(String message, Throwable cause) {
		super(message, cause);
	}

}
